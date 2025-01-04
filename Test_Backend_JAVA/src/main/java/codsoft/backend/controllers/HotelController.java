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
					import org.springframework.web.bind.annotation.RestController;

import codsoft.backend.ResourceNotFoundException;
import codsoft.backend.dtos.FlightFilter;
					import codsoft.backend.dtos.HotelDTO;
					import codsoft.backend.dtos.HotelFilter;
					import codsoft.backend.models.Flight;
					import codsoft.backend.models.Hotel;
					import codsoft.backend.repositories.HotelRepository;
					import codsoft.backend.services.FlightService;
					import codsoft.backend.services.HotelService;
					
					@RestController
					@CrossOrigin(origins = "http://localhost:4200")
					@RequestMapping("/api2")
					public class HotelController {
						
						@Autowired
						private HotelService hotelService;
						@Autowired
						private HotelRepository repo;
						
						@PostMapping("/registerhotel")
					    public ResponseEntity<?> addHotel(@RequestBody HotelDTO hotel) {
					       Hotel createdHotel = hotelService.createHotel(hotel);
					       if (createdHotel == null){
					           return new ResponseEntity<>("Hotel not created, come again later!", HttpStatus.BAD_REQUEST);
					       }
					       return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);}
					
						@GetMapping("/getallhotels")
						public ResponseEntity<List<Hotel>> getAllHotels(){
							return ResponseEntity.ok(repo.findAll());
						}
						
						  
						  @PostMapping("/filterhotels")
						  public ResponseEntity<List<Hotel>> filterHotels(@RequestBody HotelFilter filterRequest) {
						      String dest = filterRequest.getDestination();
						      System.out.println("Hotel Destination: " + dest);
						      
						      List<Hotel> filteredHotels = repo.findByDestination(dest);
						      System.out.println("Filtered flights count: " + filteredHotels.size());
					
						      return ResponseEntity.ok(filteredHotels);
						  }
						  
						  @GetMapping("/hotels/{id}")
							 public ResponseEntity<Hotel> getHotelById(@PathVariable(value = "id") Long hotelid)
							            throws codsoft.backend.ResourceNotFoundException {
						    Hotel hotel=repo.findById(hotelid)
						    		.orElseThrow(() -> new ResourceNotFoundException("Hotel not found for this id :: " + hotelid));
						    return ResponseEntity.ok().body(hotel);}
							 
					
					}
