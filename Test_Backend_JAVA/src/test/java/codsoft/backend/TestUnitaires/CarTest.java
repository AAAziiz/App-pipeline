package codsoft.backend.TestUnitaires;

import codsoft.backend.dtos.CarDTO;
import codsoft.backend.models.Car;
import codsoft.backend.services.CarServiceImpl;
import codsoft.backend.repositories.CarRepository;
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
public class CarTest {
        @Mock
        private CarRepository carRepository;

        @InjectMocks
        private CarServiceImpl carService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void createCar_ShouldSaveAndReturnCar() {

            CarDTO carDTO = new CarDTO();
            carDTO.setId(1L);
            carDTO.setLocation_return(true);
            carDTO.setCartype("SUV");
            carDTO.setAwd("AWD");
            carDTO.setCarclass("Luxury");
            carDTO.setPick_date("2023-10-22");
            carDTO.setDrop_date("2023-10-25");
            carDTO.setPassengers("4");

            Car expectedCar = new Car(1L, true, "SUV", "AWD", "Luxury", "2023-10-22", "2023-10-25", "4");

            when(carRepository.save(expectedCar)).thenReturn(expectedCar);

            Car savedCar = carService.createCar(carDTO);
            assertEquals(expectedCar, savedCar);
            verify(carRepository).save(expectedCar);
        }
    @Test
    void testCreateCar_NullFields() {

        CarDTO carDTO = new CarDTO();
        when(carRepository.save(any(Car.class))).thenReturn(null);

        Car createdcar = carService.createCar(carDTO);

        assertNull(createdcar);
        verify(carRepository, times(1)).save(any(Car.class));
    }
        @Test
        void updatePick_date_ShouldUpdatePickDate_WhenCarExists() {

            long carId = 1L;
            String newPickDate = "2024-10-30";
            Car existingCar = new Car();
            existingCar.setId(carId);
            existingCar.setPick_date("2024-10-03");

            when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));
            carService.updatePick_date(carId, newPickDate);
            assertEquals(newPickDate, existingCar.getPick_date());
            verify(carRepository).save(existingCar);
        }

        @Test
        void updatePick_date_ShouldNotUpdatePickDate_WhenCarDoesNotExist() {

            long carId = 1L;
            String newPickDate = "2024-10-30";

            when(carRepository.findById(carId)).thenReturn(Optional.empty());

            carService.updatePick_date(carId, newPickDate);

            verify(carRepository, never()).save(any(Car.class));
        }

        @Test
        void updateDrop_date_ShouldUpdateDropDate_WhenCarExists() {
            long carId = 1L;
            String newDropDate = "2024-11-05";
            Car existingCar = new Car();
            existingCar.setId(carId);
            existingCar.setDrop_date("2024-11-22");

            when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));
            carService.updateDrop_date(carId, newDropDate);
            assertEquals(newDropDate, existingCar.getDrop_date());
            verify(carRepository).save(existingCar);
        }

        @Test
        void updateDrop_date_ShouldNotUpdateDropDate_WhenCarDoesNotExist() {
            long carId = 1L;
            String newDropDate = "2024-11-05";
            when(carRepository.findById(carId)).thenReturn(Optional.empty());
            carService.updateDrop_date(carId, newDropDate);
            verify(carRepository, never()).save(any(Car.class));
        }
        @Test
        void ActiveLocation_return_ShouldActiveLocation_return_WhenCarExists() {
            long carId = 3L;
            Car Expectedcar=new Car();
            Expectedcar.setId(carId);
            when(carRepository.findById(carId)).thenReturn(Optional.of(Expectedcar));
            carService.ActiveLocation_return(carId);
            assertTrue( Expectedcar.isLocation_return());
            verify(carRepository).save(Expectedcar);
        }
        @Test
        void ActiveLocation_return_ShouldNotActiveLocation_return_WhenCarDoesNotExist() {
            long carId = 3L;
            Car Expectedcar=new Car();
            Expectedcar.setId(carId);
            when(carRepository.findById(carId)).thenReturn(Optional.empty());
            carService.ActiveLocation_return(carId);
            verify(carRepository, never()).save(any(Car.class));
    }
}




