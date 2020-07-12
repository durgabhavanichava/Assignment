package com.test.SpringBootRestAPIOnetomany.rest;

import java.util.List;
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

import com.test.SpringBootRestAPIOnetomany.jpa.AddressRepository;
import com.test.SpringBootRestAPIOnetomany.model.Address;

@RestController
@RequestMapping("/api")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
    @GetMapping("/persons/{personId}/addresses")
    public List<Address> getContactByPersonId(@PathVariable Long personId) {
    	
        if(!personRepository.existsById(personId)) {
            throw new NotFoundException("Person not found!");
        }
    	
    	return addressRepository.findByPersonId(personId);
    }
    
    @PostMapping("/persons/{personId}/addresses")
    public Address addAddress(@PathVariable Long personId,
                                 @Valid @RequestBody Address address) {
        return personRepository.findById(personId)
                .map(person -> {
                    address.setPerson(person);
                    return addressRepository.save(address);
                }).orElseThrow(() -> new NotFoundException("Person not found!"));
    }
    
    @PutMapping("/persons/{personId}/addresses/{addressId}")
    public Address updateAddress(@PathVariable Long personId,
                                    @PathVariable Long addressId,
                                    @Valid @RequestBody Address addressUpdated) {
    	
    	if(!personRepository.existsById(personId)) {
    		throw new NotFoundException("Person not found!");
    	}
    	
        return addressRepository.findById(addressId)
                .map(address -> {

                    address.setStreet(addressUpdated.getStreet());
                    address.setCity(addressUpdated.getCity());
                    address.setState(addressUpdated.getStreet());
                    address.setPostalCode(addressUpdated.getPostalCode());
                    return addressRepository.save(address);
                }).orElseThrow(() -> new NotFoundException("Assignment not found!"));
    }
    
    @DeleteMapping("/persons/{personId}/addresses/{adddressId}")
    public String deleteAddress(@PathVariable Long personId,
    							   @PathVariable Long adddressId) {
    	
    	if(!personRepository.existsById(personId)) {
    		throw new NotFoundException("Student not found!");
    	}
    	
        return addressRepository.findById(adddressId)
                .map(address -> {
                    addressRepository.delete(address);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }
}
