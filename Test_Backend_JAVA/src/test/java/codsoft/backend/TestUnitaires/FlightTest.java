package codsoft.backend.TestUnitaires;
import codsoft.backend.dtos.FlightDTO;
import codsoft.backend.models.Flight;
import codsoft.backend.repositories.FlightRepository;
import codsoft.backend.services.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FlightTest {


    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    @Test
    void testCreateFlight_Success() {

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(1L);
        flightDTO.setDirect_flight(true);
        flightDTO.setFlightType("Economy");
        flightDTO.setFromcity("New York");
        flightDTO.setTocity("Paris");
        flightDTO.setAdults(2);
        flightDTO.setChildren(1);

        Flight flight = new Flight();
        flight.setId(1L);
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);


        Flight createdFlight = flightService.createFlight(flightDTO);


        assertNotNull(createdFlight);
        assertEquals(1L, createdFlight.getId());
        verify(flightRepository, times(1)).save(any(Flight.class));
    }
    @Test
    void testCreateFlight_NullFields() {

        FlightDTO flightDTO = new FlightDTO();
        when(flightRepository.save(any(Flight.class))).thenReturn(null);

        Flight createdFlight = flightService.createFlight(flightDTO);

        assertNull(createdFlight);
        verify(flightRepository, times(1)).save(any(Flight.class));
    }
    @Test
    void updateDepartureDate_ShouldUpdateDepartureDate_WhenFlightExists(){
        long id=4L;
        String NewDepartureDate="2024-12-03";
        Flight  Existedflight=new Flight();
        Existedflight.setId(id);
        Existedflight.setDepartureDate("2024-12-12");
        when(flightRepository.findById(id)).thenReturn(Optional.of(Existedflight));
        flightService.updateDepartureDate(id,NewDepartureDate);
        assertEquals(NewDepartureDate, Existedflight.getDepartureDate());
        verify(flightRepository).save(Existedflight);

    }
    @Test
    void updateReturnDate_ShouldUpdateReturnDate_WhenFlightExists(){
        long id=1L;
        String NewReturnDate="2024-12-03";
        Flight  Existedflight=new Flight();
        Existedflight.setId(id);
        Existedflight.setReturnDate("2024-12-04");
        when(flightRepository.findById(id)).thenReturn(Optional.of(Existedflight));
        flightService.updateReturnDate(id,NewReturnDate);
        assertEquals(NewReturnDate, Existedflight.getReturnDate());
        verify(flightRepository).save(Existedflight);
    }
    @Test
    void Addchildren_ShouldAddchildren_WhenFlightExists(){
        long id=1L;
        Integer Addchildren=12;
        Flight  Existedflight=new Flight();
        Existedflight.setId(id);
        Existedflight.setChildren(50);
        when(flightRepository.findById(id)).thenReturn(Optional.of(Existedflight));
        flightService.Addchildren(id,Addchildren);
        assertEquals(Addchildren, Existedflight.getChildren()-50);
        verify(flightRepository).save(Existedflight);

    }

    @Test
    void AddAdults_ShouldAddAdults_WhenFlightExists(){
        long id=5L;
        Integer Addadults=2;
        Flight  Existedflight=new Flight();
        Existedflight.setId(id);
        Existedflight.setAdults(3);
        when(flightRepository.findById(id)).thenReturn(Optional.of(Existedflight));
        flightService.AddAdults(id,Addadults);
        assertEquals(5, Existedflight.getAdults());
        verify(flightRepository).save(Existedflight);
    }

}
