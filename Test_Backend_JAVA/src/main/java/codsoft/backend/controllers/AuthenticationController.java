package codsoft.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codsoft.backend.dtos.AuthenticationRequest;
import codsoft.backend.dtos.AuthenticationResponse;
import codsoft.backend.services.jwt.UserDetailsServiceImpl;
import codsoft.backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AuthenticationController {

	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/authentication")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws BadCredentialsException,DisabledException,UsernameNotFoundException, java.io.IOException {
		
		 try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException("Incorrect username or password!");
	        } catch (DisabledException disabledException) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
	            return null;
	        }

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
	        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
	        return new AuthenticationResponse(jwt);



		
	}
	}
