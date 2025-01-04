package codsoft.backend.dtos;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class HotelDTO {
		private Long id;
	    private String hotel_Type;
	    private String destination;
	    private int rooms;
	    private int star_rating;
	    private String arrival_date;
	    private String leave_date;
	    private int adults;
	    private int children;

	    

}
