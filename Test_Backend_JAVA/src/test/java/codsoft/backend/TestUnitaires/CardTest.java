package codsoft.backend.TestUnitaires;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import codsoft.backend.dtos.CardDTO;
import codsoft.backend.models.Card;
import codsoft.backend.repositories.CardRepository;
import codsoft.backend.services.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CardTest{

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository repo;

    private Card card;
    private CardDTO cardDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        card = new Card();
        card.setId(1L);
        card.setCardNumber("1234567890123456");
        card.setMonthExpir("12");
        card.setYearExpir("2024");
        card.setCardCvc("123");

        cardDTO = new CardDTO();
        cardDTO.setId(1L);
        cardDTO.setCardNumber("1234567890123456");
        cardDTO.setMonthExpir("12");
        cardDTO.setYearExpir("2024");
        cardDTO.setCardCvc("123");
    }

    @Test
    void testCreateCard() {
        when(repo.save(any(Card.class))).thenReturn(card);

        Card createdCard = cardService.createCard(cardDTO);

        assertNotNull(createdCard);
        assertEquals("1234567890123456", createdCard.getCardNumber());
        assertEquals("12", createdCard.getMonthExpir());
        assertEquals("2024", createdCard.getYearExpir());
        assertEquals("123", createdCard.getCardCvc());
    }

    @Test
    void testGetCardById() {
        when(repo.findById(1L)).thenReturn(Optional.of(card));

        Optional<Card> foundCard = repo.findById(1L);

        assertTrue(foundCard.isPresent());
        assertEquals("1234567890123456", foundCard.get().getCardNumber());
    }

    @Test
    void testUpdateCard() {
        when(repo.findById(1L)).thenReturn(Optional.of(card));
        when(repo.save(any(Card.class))).thenReturn(card);

        cardDTO.setCardNumber("6543210987654321");
        Card updatedCard = cardService.updateCard(1L, cardDTO);

        assertEquals("6543210987654321", updatedCard.getCardNumber());
    }

    @Test
    void testDeleteCard() {
        doNothing().when(repo).deleteById(1L);

        cardService.deleteCard(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}

