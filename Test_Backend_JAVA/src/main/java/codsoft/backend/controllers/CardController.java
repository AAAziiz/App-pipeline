package codsoft.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codsoft.backend.dtos.CardDTO;
import codsoft.backend.models.Card;
import codsoft.backend.services.CardService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api2")
public class CardController {
	
	@Autowired
	private CardService service;
	
	@PostMapping("/registercard")
	 public ResponseEntity<?> addCard(@RequestBody CardDTO carddto) {
	       Card createdCard = service.createCard(carddto);
	       if (createdCard == null){
	           return new ResponseEntity<>("Card not created, come again later!", HttpStatus.BAD_REQUEST);
	       }
	       return new ResponseEntity<>(createdCard, HttpStatus.CREATED);}
}
