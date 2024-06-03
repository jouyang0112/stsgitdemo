package com.example.demo.controller;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.service.ContactService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/retrieve/{id}")
	public ResponseEntity<Person> retrievePerson(@PathVariable("id") long id) {
		Optional<Person> personData = contactService.retrievePersonById(id);
		if(personData.isPresent()) {
			return new ResponseEntity<>(personData.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}	
	}
	
	@PostMapping("/save")
	public ResponseEntity<Person> savePerson(@RequestBody Person person) {
		try {
			Person _person = contactService.savePerson(new Person(person.getName(), person.getMobileNumber()));
			return new ResponseEntity<>(_person, HttpStatus.CREATED);			
		}catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
