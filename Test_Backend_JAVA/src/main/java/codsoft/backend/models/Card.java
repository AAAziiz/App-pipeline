package codsoft.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="cards")
@AllArgsConstructor
@NoArgsConstructor
public class Card {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String cardNumber;
    @Column
    private String  monthExpir;
    @Column
    private String  yearExpir;
    @Column
    private String  cardCvc;

    @OneToOne(mappedBy = "card")
    private User user;
    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", monthExpir='" + monthExpir + '\'' +
                ", yearExpir='" + yearExpir + '\'' +
                ", cardCvc='" + cardCvc + '\'' +
                '}';
    }


}
