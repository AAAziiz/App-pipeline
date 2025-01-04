package codsoft.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import codsoft.backend.models.Car;
import codsoft.backend.models.Flight;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByCartype(String type);

}
