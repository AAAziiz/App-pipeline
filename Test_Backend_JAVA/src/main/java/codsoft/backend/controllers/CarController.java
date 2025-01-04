package codsoft.backend.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import codsoft.backend.ResourceNotFoundException;
import codsoft.backend.dtos.CarDTO;
import codsoft.backend.dtos.CarFilter;
import codsoft.backend.models.Car;
import codsoft.backend.repositories.CarRepository;
import codsoft.backend.services.CarService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/*")
@RequestMapping("/api2")
public class CarController {
	@Autowired
	private CarService carService;
	@Autowired
	private CarRepository repo;

	@PostMapping("/registercar")
	public ResponseEntity<?> addFlight(@RequestBody CarDTO car) {
		Car createdCar = carService.createCar(car);
		if (createdCar == null) {
			return new ResponseEntity<>("Car not created, come again later!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
	}

	@PutMapping("/{id}/pick-date")
	public ResponseEntity<String> updatePickDate(@PathVariable long id, @RequestParam String newPick_date) {
		try {
			carService.updatePick_date(id, newPick_date);
			return ResponseEntity.ok("Pick date updated successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
		}
	}

	@PutMapping("/{id}/drop-date")
	public ResponseEntity<String> updateDropDate(@PathVariable long id, @RequestParam String newDrop_date) {
		try {
			carService.updateDrop_date(id, newDrop_date);
			return ResponseEntity.ok("Drop date updated successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
		}
	}

	@GetMapping("/getallcars")
	public ResponseEntity<List<Car>> getAllCars() {
		return ResponseEntity.ok(repo.findAll());
	}

	@PostMapping("/filtercars")
	public ResponseEntity<List<Car>> filterCars(@RequestBody CarFilter filterRequest) {
		String cartype = filterRequest.getType();
		List<Car> filteredCars = repo.findByCartype(cartype);
		System.out.println("Filtered cars count: " + filteredCars.size());
		return ResponseEntity.ok(filteredCars);
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carid) throws ResourceNotFoundException {
		Car car = repo.findById(carid)
				.orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carid));
		return ResponseEntity.ok().body(car);
	}
	@PutMapping("/{id}/Location_return_acivated")
	public ResponseEntity<String> ActiveLocationreturn(@PathVariable long id) {
		try {
			carService.ActiveLocation_return(id);
			return ResponseEntity.ok("Location_return has been acivated successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
		}
	}
}
