package codsoft.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codsoft.backend.dtos.FlightDTO;
import codsoft.backend.models.Flight;
import codsoft.backend.repositories.FlightRepository;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private FlightRepository repo;
	

	 @Override
	public Flight createFlight(FlightDTO flightDTO) {
			Flight flight =new Flight();
			flight.setId(flightDTO.getId());
			flight.setDirect_flight(flightDTO.isDirect_flight());
			flight.setFlightType(flightDTO.getFlightType());
			flight.setFromcity(flightDTO.getFromcity());
			flight.setTocity(flightDTO.getTocity());
			flight.setAddNearbyAirportsFrom(flightDTO.isAddNearbyAirportsFrom());
			flight.setAddNearbyAirportsTo(flightDTO.isAddNearbyAirportsTo());
			flight.setTravelClass(flightDTO.getTravelClass());
			flight.setDepartureDate(flightDTO.getDepartureDate());
			flight.setReturnDate(flightDTO.getReturnDate());
			flight.setAdults(flightDTO.getAdults());
			flight.setChildren(flightDTO.getChildren());
			return repo.save(flight);
			
					
		
	}

	@Override
	public  void updateDepartureDate(long id,String NewDepartureDate){
		Optional<Flight> flight=repo.findById(id);
		if(flight.isPresent()){
			Flight flightexist =flight.get();
			flightexist.setDepartureDate(NewDepartureDate);
			repo.save(flightexist);
		}
	}
	@Override
	public  void updateReturnDate(long id,String NewReturnDate){
		Optional<Flight> flight=repo.findById(id);
		if(flight.isPresent()){
			Flight flightexist =flight.get();
			flightexist.setReturnDate(NewReturnDate);
			repo.save(flightexist);
		}
	}


	@Override
	public void Addchildren(Long flightId, int additionalChildren) {
		Flight flight = repo.findById(flightId)
				.orElseThrow(() -> new IllegalArgumentException("Flight not found with ID: " + flightId));
		flight.setChildren(flight.getChildren() + additionalChildren);
		repo.save(flight);
	}

	@Override
		public void AddAdults ( long id, Integer NewAdults){
			Optional<Flight> flight = repo.findById(id);
			Flight flightexist = flight.get();
			Integer NbrAdults=flightexist.getAdults();
			NbrAdults+=NewAdults;
			flightexist.setAdults(NbrAdults);
			repo.save(flightexist);

		}

	}