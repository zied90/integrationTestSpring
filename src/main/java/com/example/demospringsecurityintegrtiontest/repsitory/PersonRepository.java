package com.example.demospringsecurityintegrtiontest.repsitory;

import com.example.demospringsecurityintegrtiontest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PersonRepository extends JpaRepository<Person, Integer>{

}
