package codsoft.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import codsoft.backend.models.Flight;
import codsoft.backend.models.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByDestination(String dest);
    Hotel findOneByDestination(String dest);


}
