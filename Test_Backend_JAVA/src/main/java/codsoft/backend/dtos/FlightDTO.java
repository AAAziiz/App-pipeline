package codsoft.backend.dtos;

import lombok.Data;

@Data
public class FlightDTO {
	    private Long id;
	    private boolean direct_flight;
	    private String flightType;
	    private String fromcity;
	    private String tocity;
	    private boolean addNearbyAirportsFrom;
	    private boolean addNearbyAirportsTo;
	    private String travelClass;
	    private String departureDate;
	    private String returnDate;
	    private Integer	adults;
	    private Integer children;
	    

		
}
