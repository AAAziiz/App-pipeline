package codsoft.backend.services;

import codsoft.backend.dtos.HotelDTO;
import codsoft.backend.models.Hotel;

public interface HotelService  {
	Hotel createHotel(HotelDTO hotelDTO);
	Hotel updateHotel(Long id, HotelDTO hotelDTO);

	void deleteHotel(Long id);
}
