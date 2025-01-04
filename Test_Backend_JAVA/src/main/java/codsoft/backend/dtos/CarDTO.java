package codsoft.backend.dtos;
import lombok.Data;


@Data
public class CarDTO {
		private Long id;
		
	    private boolean location_return;
	    private String cartype;
	    private String awd;
	    private String carclass;
	    private String pick_date;
	    private String drop_date;
	    private String passengers;

}
