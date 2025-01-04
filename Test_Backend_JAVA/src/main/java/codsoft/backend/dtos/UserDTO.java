package codsoft.backend.dtos;
import codsoft.backend.models.Card;
import lombok.Data;


@Data


public class UserDTO {
	
        private Long id;
	
	    private String name;

	    private String email;

	    private String password;
	    private Card card;


}
