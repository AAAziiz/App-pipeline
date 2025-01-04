package codsoft.backend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codsoft.backend.dtos.HotelDTO;
import codsoft.backend.models.Hotel;
import codsoft.backend.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository repo;
	@Override
	public Hotel createHotel(HotelDTO hotelDTO) {
		Hotel hotel=new Hotel();
			hotel.setId(hotelDTO.getId());
			hotel.setHotel_Type(hotelDTO.getHotel_Type());
			hotel.setDestination(hotelDTO.getDestination());
			hotel.setRooms(hotelDTO.getRooms());
			hotel.setStar_rating(hotelDTO.getStar_rating());
			hotel.setArrival_date(hotelDTO.getArrival_date());
			hotel.setLeave_date(hotelDTO.getLeave_date());
			hotel.setAdults(hotelDTO.getAdults());
			hotel.setChildren(hotelDTO.getChildren());
			return repo.save(hotel);
	}
	public Hotel updateHotel(Long id, HotelDTO hotelDTO) {
		return repo.findById(id).map(hotel -> {
			hotel.setHotel_Type(hotelDTO.getHotel_Type());
			hotel.setDestination(hotelDTO.getDestination());
			hotel.setRooms(hotelDTO.getRooms());
			hotel.setStar_rating(hotelDTO.getStar_rating());
			hotel.setArrival_date(hotelDTO.getArrival_date());
			hotel.setLeave_date(hotelDTO.getLeave_date());
			hotel.setAdults(hotelDTO.getAdults());
			hotel.setChildren(hotelDTO.getChildren());
			return repo.save(hotel);
		}).orElseThrow(() -> new EntityNotFoundException("Hotel not found with id " + id));
	}

	public void deleteHotel(Long id) {
		repo.deleteById(id);
	}
}
