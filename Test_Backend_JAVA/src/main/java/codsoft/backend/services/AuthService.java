package codsoft.backend.services;

import codsoft.backend.dtos.SignupRequest;
import codsoft.backend.dtos.UserDTO;

public interface AuthService {
	
	
	UserDTO createUser(SignupRequest signuprequest);

}
