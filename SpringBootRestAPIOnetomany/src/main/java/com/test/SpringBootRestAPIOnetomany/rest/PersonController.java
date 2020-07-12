package com.test.SpringBootRestAPIOnetomany.rest;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.test.SpringBootRestAPIOnetomany.exception.NotFoundException;
import com.test.SpringBootRestAPIOnetomany.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.SpringBootRestAPIOnetomany.model.Person;


@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
    @GetMapping("/persons")
    public List<Person> getAllPersons() {
    	return personRepository.findAll();
    }

    @GetMapping("/getPersonCount")
    public List<Long> getPersonCount() {
        return personRepository.countPerson();
    }

    @GetMapping("/persons/{id}")
    public Person getPersonByID(@PathVariable Long id) {
    	Optional<Person> optStudent = personRepository.findById(id);
    	if(optStudent.isPresent()) {
    		return optStudent.get();
    	}else {
    		throw new NotFoundException("Person not found with id " + id);
    	}
    }
    
    @PostMapping("/persons")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }
    
    @PutMapping("/persons/{id}")
    public Person updatePerson(@PathVariable Long id,
                                @Valid @RequestBody Person personUpdated) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setFname(personUpdated.getFname());
                    person.setLname(personUpdated.getLname());
                    return personRepository.save(person);
                }).orElseThrow(() -> new NotFoundException("Person not found with id " + id));
    }
    
    @DeleteMapping("/persons/{id}")
    public String deletePerson(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Person not found with id " + id));
    }
}