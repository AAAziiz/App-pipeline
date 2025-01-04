package codsoft.backend.dtos;

import lombok.Data;

@Data
public class AuthenticationResponse {
	private String jwt;
   public AuthenticationResponse(String jwt) {
	   super();
	   this.jwt = jwt;
   }
}
