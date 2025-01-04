package codsoft.backend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codsoft.backend.dtos.CarDTO;
import codsoft.backend.models.Car;
import codsoft.backend.repositories.CarRepository;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarRepository repo;

	@Override
	public Car createCar(CarDTO carDTO) {
			Car car=new Car();
			car.setId(carDTO.getId());
			car.setLocation_return(carDTO.isLocation_return());
			car.setCartype(carDTO.getCartype());
			car.setAwd(carDTO.getAwd());
			car.setCarclass(carDTO.getCarclass());
			car.setPick_date(carDTO.getPick_date());
			car.setDrop_date(carDTO.getDrop_date());
			car.setPassengers(carDTO.getPassengers());
			return repo.save(car);
	}
	public void updatePick_date(long id,String newPick_date){
		Optional<Car> car=repo.findById(id);
		if (car.isPresent()){
			Car Existingcar= car.get();
			Existingcar.setPick_date(newPick_date);
			repo.save(Existingcar);
		}

	}
	public void updateDrop_date(long id,String newDrop_date){
		Optional<Car> car=repo.findById(id);
		if (car.isPresent()){
			Car Existingcar= car.get();
			Existingcar.setDrop_date(newDrop_date);
			repo.save(Existingcar);
		}

	}
	public void ActiveLocation_return(long id) {
		Optional<Car> car = repo.findById(id);
		if (car.isPresent()) {
			Car existingCar = car.get();
			existingCar.setLocation_return(true);
			repo.save(existingCar);
		}
	}

}
