package codsoft.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codsoft.backend.ResourceNotFoundException;
import codsoft.backend.dtos.FlightDTO;
import codsoft.backend.dtos.FlightFilter;
import codsoft.backend.models.Flight;
import codsoft.backend.repositories.FlightRepository;
import codsoft.backend.services.FlightService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api2")
public class FlightController {
	@Autowired
	private FlightService flightService;
	@Autowired
	private FlightRepository repo;
	
	 @PostMapping("/registerflight")
	    public ResponseEntity<?> addFlight(@RequestBody FlightDTO flight) {
	       Flight createdFlight = flightService.createFlight(flight);
	       if (createdFlight == null){
	           return new ResponseEntity<>("Flight not created, come again later!", HttpStatus.BAD_REQUEST);
	       }
	       return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);}

	  @GetMapping("/getallflights")
	    public ResponseEntity<List<Flight>> getAllFlights() {
	        return ResponseEntity.ok(repo.findAll());
	    }
	  
	  
	  @PostMapping("/filterflights")
	  public ResponseEntity<List<Flight>> filterFlights(@RequestBody FlightFilter filterRequest) {
	      String fromCity = filterRequest.getFromwhere();
	      String toCity = filterRequest.getTowhere();
	      System.out.println("Received fromCity: " + fromCity);
	      System.out.println("Received toCity: " + toCity);
	      
	      List<Flight> filteredFlights = repo.findByFromcityAndTocity(fromCity, toCity);
	      System.out.println("Filtered flights count: " + filteredFlights.size());

	      return ResponseEntity.ok(filteredFlights);
	  }
	  @GetMapping("/flights/{id}")
		 public ResponseEntity<Flight> getFlightById(@PathVariable(value = "id") Long flightid)
		            throws codsoft.backend.ResourceNotFoundException {
	    Flight flight=repo.findById(flightid)
	    		.orElseThrow(() -> new ResourceNotFoundException("Flight not found for this id :: " + flightid));
	    return ResponseEntity.ok().body(flight);}
		 
}
	
	



