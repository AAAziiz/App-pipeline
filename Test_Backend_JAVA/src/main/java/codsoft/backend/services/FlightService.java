package codsoft.backend.services;
import codsoft.backend.dtos.FlightDTO;
import codsoft.backend.models.Flight;
import org.springframework.stereotype.Service;



public interface FlightService {
	Flight createFlight(FlightDTO flightDTO);
	void updateDepartureDate(long id,String NewDepartureDate);
	void updateReturnDate(long id,String NewReturnDate);

	void Addchildren(Long flightId, int additionalChildren);

	void AddAdults(long id, Integer NewAdults);
}
