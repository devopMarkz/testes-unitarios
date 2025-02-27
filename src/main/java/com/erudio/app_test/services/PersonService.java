package com.erudio.app_test.services;

import com.erudio.app_test.entities.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("find one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Marcos");
        person.setLastName("André");
        person.setAddress("Coroado, Rua do Cobre, nº 10");
        person.setGender("Masculino");
        return person;
    }

    public List<Person> findAll(){
        logger.info("Finding all people!");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person createPerson(Person person) {
        logger.info("Creating one person!");
        person.setId(counter.incrementAndGet());
        return person;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("Some address in Brasil " + i);
        person.setGender("Masculino");
        return person;
    }

}
