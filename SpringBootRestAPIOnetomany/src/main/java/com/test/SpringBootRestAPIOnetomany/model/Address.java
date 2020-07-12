package com.test.SpringBootRestAPIOnetomany.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "assignments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "street")
	private String street;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "postalCode")
	private String postalCode;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    @JsonIgnore
    private Person person;

	public Address() {}
	public Address(String name, String street,String city,String state,String postalCode) {

		this.street = street;
		this.city=city;
		this.state = state;
		this.postalCode = postalCode;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return this.person;
	}
}
