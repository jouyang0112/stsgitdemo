package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	//get
	public Optional<Person> retrievePersonById(long id) {
		return contactRepository.findById(Long.valueOf(id));
	}
	
	public Person savePerson(Person person) {
		return contactRepository.save(person);
	}

}
