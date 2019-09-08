package com.example.demospringsecurityintegrtiontest.service;

import com.example.demospringsecurityintegrtiontest.entity.Person;
import com.example.demospringsecurityintegrtiontest.repsitory.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person savePerson(Person person) {
        System.out.println("Service savePerson() method Called...");
        return repository.save(person);
    }

    public List<Person> findAllPersons() {
        System.out.println("Service findAllPersons() method Called...");
        return repository.findAll();
    }

}
