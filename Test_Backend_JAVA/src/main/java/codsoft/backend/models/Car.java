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
@Table(name="cars")
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Long id;
	@Column
    private boolean location_return;
	@Column
    private String cartype;
	@Column
    private String awd;
	@Column
    private String carclass;
	@Column
    private String pick_date;
	@Column
    private String drop_date;
	@Column
    private String passengers;


}
