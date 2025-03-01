package com.erudio.app_test.repositories;

import com.erudio.app_test.entities.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @DisplayName("Given Person Object when Save then Return Saved Person")
    @Test
    void testGivenPersonObject_whenSave_thenReturnSavedPerson(){
        // Arrange
        Person person = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");

        // Act
        Person savedPerson = personRepository.save(person);

        // Assert
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0, () -> "O Id não foi gerado.");
    }

    @DisplayName("Given Person List when FindAll then Return Person List")
    @Test
    void testGivenPersonList_whenFindAll_thenReturnPersonList(){
        // Arrange
        Person person1 = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");
        Person person2 = new Person("Vitor", "Nascimento", "Maracanã", "Female", "vitor@gmail.com");
        personRepository.save(person1);
        personRepository.save(person2);

        // Act
        List<Person> personList = personRepository.findAll();

        // Assert
        assertNotNull(personList, () -> "A lista não contém elementos.");
        assertEquals(2, personList.size());
    }

    @DisplayName("Given Person Object when FindById then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindById_thenReturnPersonObject(){
        // Arrange
        Person person = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");
        Person savedPerson = personRepository.save(person);

        // Act
        Person personFound = personRepository.findById(savedPerson.getId()).get();

        // Assert
        assertNotNull(personFound, () -> "Objeto Pessoa não foi encontrado por id.");
        assertEquals(personFound.getId(), savedPerson.getId());
    }

    @DisplayName("Given Person Object when FindByEmail then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject(){
        // Arrange
        Person person = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");
        Person savedPerson = personRepository.save(person);

        // Act
        Person personFound = personRepository.findByEmail(savedPerson.getEmail()).get();

        // Assert
        assertNotNull(personFound, () -> "Objeto Pessoa não foi encontrado por email.");
        assertEquals(personFound.getEmail(), savedPerson.getEmail());
    }

    @DisplayName("Given Person Object when Update Person then Return Updated Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject(){
        // Arrange
        Person person = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");
        Person savedPerson = personRepository.save(person);

        // Act
        Person personFound = personRepository.findById(savedPerson.getId()).get();
        personFound.setFirstName("Vitor");
        personFound.setLastName("Nascimento");
        personFound.setGender("Female");
        personFound.setAddress("Maracanã");
        personFound.setEmail("vitor@gmail.com");

        Person updatedPerson = personRepository.save(personFound);

        // Assert
        assertNotNull(updatedPerson);
        assertEquals("Vitor", updatedPerson.getFirstName());
        assertEquals("vitor@gmail.com", updatedPerson.getEmail());
    }

    @DisplayName("Given Person Object when Delete then Remove Person")
    @Test
    void testGivenPersonObject_whenDelete_thenRemovePerson(){
        // Arrange
        Person person = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");
        personRepository.save(person);

        // Act
        personRepository.deleteById(person.getId());
        Optional<Person> personOptional = personRepository.findById(person.getId());

        // Assert
        assertTrue(personOptional.isEmpty());
    }

    @DisplayName("Given FirstName And LastName when FindByJPQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindByJPQL_thenReturnPersonObject(){
        // Arrange
        Person person = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");
        Person savedPerson = personRepository.save(person);

        // Act
        Person personFound = personRepository.findByJPQL("Marcos", "André");

        // Assert
        assertNotNull(personFound, () -> "Objeto Pessoa não foi encontrado por id.");
        assertEquals("Marcos", personFound.getFirstName(), () -> "FirstName não encontrado.");
        assertEquals("André", personFound.getLastName(), () -> "LastName não encontrado.");
    }

    @DisplayName("Given FirstName And LastName when FindBySQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindBySQL_thenReturnPersonObject(){
        // Arrange
        Person person = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");
        Person savedPerson = personRepository.save(person);

        // Act
        Person personFound = personRepository.findBySQL("Marcos", "André");

        // Assert
        assertNotNull(personFound, () -> "Objeto Pessoa não foi encontrado por id.");
        assertEquals("Marcos", personFound.getFirstName(), () -> "FirstName não encontrado.");
        assertEquals("André", personFound.getLastName(), () -> "LastName não encontrado.");
    }

}