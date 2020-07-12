package com.test.SpringBootRestAPIOnetomany.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "persons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fname")
	private String fname;
	@Column(name = "lname")
	private String lname;
	
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> addresses;
	
	public Person() {}
	
	public Person(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getFname() {
		return this.fname;
	}
	
	public void setLname(String  lname) {
		this.lname = lname;
	}
	
	public String getLname() {
		return this.lname;
	}
	
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public Set<Address> getAddresses(){
		return this.addresses;
	}

}
