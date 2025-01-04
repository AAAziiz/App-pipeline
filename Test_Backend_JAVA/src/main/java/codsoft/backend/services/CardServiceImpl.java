package codsoft.backend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codsoft.backend.dtos.CardDTO;
import codsoft.backend.models.Card;
import codsoft.backend.repositories.CardRepository;

@Service
public class CardServiceImpl implements CardService{
	
	@Autowired
	private CardRepository repo;

	@Override
	public Card createCard(CardDTO carddto) {
		
		Card card =new Card();
		card.setId(carddto.getId());
		card.setCardNumber(carddto.getCardNumber());
		card.setMonthExpir(carddto.getMonthExpir());
		card.setYearExpir(carddto.getYearExpir());
		card.setCardCvc(carddto.getCardCvc());
		return(repo.save(card)) ;
	}

	public Card updateCard(Long id, CardDTO carddto) {
		return repo.findById(id).map(card -> {
			card.setCardNumber(carddto.getCardNumber());
			card.setMonthExpir(carddto.getMonthExpir());
			card.setYearExpir(carddto.getYearExpir());
			card.setCardCvc(carddto.getCardCvc());
			return repo.save(card);
		}).orElseThrow(() -> new EntityNotFoundException("Card not found with id " + id));
	}

	public void deleteCard(Long id) {
		repo.deleteById(id);
	}
	

}
