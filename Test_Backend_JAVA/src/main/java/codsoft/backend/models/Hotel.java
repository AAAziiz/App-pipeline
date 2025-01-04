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
@Table(name="hotels")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Long id;
	@Column
    private String hotel_Type;
	@Column
    private String destination;
	@Column
    private int rooms;
	@Column
    private int star_rating;
	@Column
    private String arrival_date;
	@Column
    private String leave_date;
	@Column
    private int adults;
	@Column
    private int children;

}
