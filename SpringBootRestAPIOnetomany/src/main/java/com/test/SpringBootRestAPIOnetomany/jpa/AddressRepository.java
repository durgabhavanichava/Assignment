package com.test.SpringBootRestAPIOnetomany.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.SpringBootRestAPIOnetomany.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByPersonId(Long studentId);
}
