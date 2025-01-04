package codsoft.backend.dtos;

import lombok.Data;

@Data
public class CardDTO {
	   private Long id;
	  
	    private String name;
	    
	    private String email;
	    
	    private String cardNumber;
	    
	    private String  monthExpir;
	    
	    private String  yearExpir;
	    
	    private String  cardCvc;

}
