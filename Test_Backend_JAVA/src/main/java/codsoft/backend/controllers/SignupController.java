package codsoft.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codsoft.backend.dtos.SignupRequest;
import codsoft.backend.dtos.UserDTO;
import codsoft.backend.services.AuthService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class SignupController {
	  @Autowired
	    private AuthService authService;

	    @PostMapping("/registeruser")
	    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
	       UserDTO createdUser = authService.createUser(signupRequest);
	       if (createdUser == null){
	           return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
	       }
	       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	    }
	
	
	
}
