package com.test.SpringBootRestAPIOnetomany.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.SpringBootRestAPIOnetomany.model.Person;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT COUNT(p.id) FROM  Person AS p ")
    List<Long> countPerson();


}