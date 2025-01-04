package codsoft.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import codsoft.backend.models.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{
    List<Flight> findByFromcityAndTocity(String fromCity, String toCity);
		}




