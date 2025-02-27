package com.erudio.app_test.services;

import com.erudio.app_test.entities.Person;
import com.erudio.app_test.repositories.PersonRepository;
import com.erudio.app_test.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public Person findById(Long id){
        logger.info("find one person!");
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
    }

    public List<Person> findAll(){
        logger.info("Finding all people!");
        return personRepository.findAll();
    }

    public Person createPerson(Person person) {
        logger.info("Creating one person!");
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person personUpdate) {
        logger.info("Updating one person!");
        if(!personRepository.existsById(id)) throw new ResourceNotFoundException("Usuário não encontrado.");
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        person.setFirstName(personUpdate.getFirstName() == null? person.getFirstName() : personUpdate.getFirstName());
        person.setLastName(personUpdate.getLastName() == null? person.getLastName() : personUpdate.getLastName());
        person.setAddress(personUpdate.getAddress() == null? person.getAddress() : personUpdate.getAddress());
        person.setGender(personUpdate.getGender() == null? person.getGender() : personUpdate.getGender());
        personRepository.save(person);
        return person;
    }

    public void deletePerson(Long id) {
        logger.info("Deleting one person!");
        if(!personRepository.existsById(id)) throw new ResourceNotFoundException("Usuário não encontrado.");
        personRepository.deleteById(id);
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
