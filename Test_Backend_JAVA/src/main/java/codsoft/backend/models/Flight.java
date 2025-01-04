package codsoft.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="flights")
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Long id;
	@Column
    private Boolean direct_flight;
	@Column
    private String flightType;
	@Column
    private String fromcity;
	@Column
    private String tocity;
	@Column
    private boolean addNearbyAirportsFrom;
	@Column
    private boolean addNearbyAirportsTo;
	@Column
    private String travelClass;
	@Column
    private String departureDate;
	@Column
    private String returnDate;
	@Column
    private Integer	adults;
	@Column
    private Integer children;
	

}
