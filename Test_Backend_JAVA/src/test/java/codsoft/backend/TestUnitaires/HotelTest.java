package codsoft.backend.TestUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import codsoft.backend.dtos.HotelDTO;
import codsoft.backend.models.Hotel;
import codsoft.backend.repositories.HotelRepository;
import codsoft.backend.services.HotelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class HotelTest {

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Mock
    private HotelRepository repo;

    private Hotel hotel;
    private HotelDTO hotelDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setHotel_Type("Luxury");
        hotel.setDestination("Paris");
        hotel.setRooms(2);
        hotel.setStar_rating(5);
        hotel.setArrival_date("2024-12-20");
        hotel.setLeave_date("2024-12-25");
        hotel.setAdults(2);
        hotel.setChildren(0);

        hotelDTO = new HotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setHotel_Type("Luxury");
        hotelDTO.setDestination("Paris");
        hotelDTO.setRooms(2);
        hotelDTO.setStar_rating(5);
        hotelDTO.setArrival_date("2024-12-20");
        hotelDTO.setLeave_date("2024-12-25");
        hotelDTO.setAdults(2);
        hotelDTO.setChildren(0);
    }

    @Test
    void testCreateHotel() {
        when(repo.save(any(Hotel.class))).thenReturn(hotel);

        Hotel createdHotel = hotelService.createHotel(hotelDTO);

        assertNotNull(createdHotel);
        assertEquals("Luxury", createdHotel.getHotel_Type());
        assertEquals("Paris", createdHotel.getDestination());
        assertEquals(2, createdHotel.getRooms());
        assertEquals(5, createdHotel.getStar_rating());
        assertEquals("2024-12-20", createdHotel.getArrival_date());
        assertEquals("2024-12-25", createdHotel.getLeave_date());
    }

    @Test
    void testGetHotelById() {
        when(repo.findById(1L)).thenReturn(Optional.of(hotel));

        Optional<Hotel> foundHotel = repo.findById(1L);

        assertTrue(foundHotel.isPresent());
        assertEquals("Luxury", foundHotel.get().getHotel_Type());
    }

    @Test
    void testUpdateHotel() {
        when(repo.findById(1L)).thenReturn(Optional.of(hotel));
        when(repo.save(any(Hotel.class))).thenReturn(hotel);

        hotelDTO.setDestination("London");
        Hotel updatedHotel = hotelService.updateHotel(1L, hotelDTO);

        assertEquals("London", updatedHotel.getDestination());
    }

    @Test
    void testDeleteHotel() {
        doNothing().when(repo).deleteById(1L);

        hotelService.deleteHotel(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}
