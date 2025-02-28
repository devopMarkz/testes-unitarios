package com.erudio.app_test.services;

import com.erudio.app_test.entities.Person;
import com.erudio.app_test.repositories.PersonRepository;
import com.erudio.app_test.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public Person findById(Long id){
        logger.info("find one person!");
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public List<Person> findAll(){
        logger.info("Finding all people!");
        return personRepository.findAll();
    }

    @Transactional
    public Person createPerson(Person person) {
        logger.info("Creating one person!");
        return personRepository.save(person);
    }

    @Transactional
    public Person updatePerson(Person personUpdate) {
        logger.info("Updating one person!");
        Person person = personRepository.findById(personUpdate.getId()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        person.setFirstName(personUpdate.getFirstName() == null? person.getFirstName() : personUpdate.getFirstName());
        person.setLastName(personUpdate.getLastName() == null? person.getLastName() : personUpdate.getLastName());
        person.setAddress(personUpdate.getAddress() == null? person.getAddress() : personUpdate.getAddress());
        person.setGender(personUpdate.getGender() == null? person.getGender() : personUpdate.getGender());
        personRepository.save(person);
        return person;
    }

    @Transactional
    public void deletePerson(Long id) {
        logger.info("Deleting one person!");
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        personRepository.delete(person);
    }

}
