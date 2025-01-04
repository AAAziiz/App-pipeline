package codsoft.backend.services;

import codsoft.backend.dtos.CarDTO;
import codsoft.backend.models.Car;

public interface CarService {
	Car createCar(CarDTO carDTO);
	void updatePick_date(long id,String NewPick_date);
	void updateDrop_date(long id,String NewDrop_date);
	void ActiveLocation_return(long id);
}
