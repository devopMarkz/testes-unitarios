package com.erudio.app_test.services;

import com.erudio.app_test.entities.Person;
import com.erudio.app_test.repositories.PersonRepository;
import com.erudio.app_test.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    private Person person;

    @BeforeEach
    void setup(){
        // Arrange
        person = new Person("Marcos", "André", "Coroado", "Male", "marcos@gmail.com");
    }

    @DisplayName("Given Person Object when Save Person then Return Person Object")
    @Test
    void testGivenPersonObject_whenSavePerson_thenReturnPersonObject(){
        given(personRepository.findByEmail(anyString())).willReturn(Optional.empty());
        given(personRepository.save(person)).willReturn(person);

        Person savedPerson = personService.createPerson(person);

        assertNotNull(savedPerson);
        assertEquals("Marcos", person.getFirstName());
    }

    @DisplayName("Given Person Object when Save Repeated Person then Throw Exception")
    @Test
    void testGivenPersonObject_whenSaveRepeatedPerson_thenThrowException(){
        given(personRepository.findByEmail(anyString())).willReturn(Optional.of(person));

        assertThrows(ResourceNotFoundException.class, () -> {
            personService.createPerson(person);
        });

        verify(personRepository, never()).save(any(Person.class));
    }

    @DisplayName("Given Persons List when FindAllPersons then Return Persons List")
    @Test
    void testGivenPersonsList_whenFindAllPersons_thenReturnPersonsList(){
        Person otherPerson = new Person("Vitor", "Nascimento", "Maracanã", "Male", "vitor@gmail.com");
        given(personRepository.findAll()).willReturn(List.of(person, otherPerson));

        List<Person> personList = personService.findAll();

        assertNotNull(personList);
        assertEquals(2, personList.size());
    }

    @DisplayName("Given Empty Persons List when FindAllPersons then Return Empty Persons List")
    @Test
    void testGivenEmptyPersonsList_whenFindAllPersons_thenReturnEmptyPersonsList(){
        given(personRepository.findAll()).willReturn(Collections.emptyList());

        List<Person> personList = personService.findAll();

        assertTrue(personList.isEmpty());
        assertEquals(0, personList.size());
    }

    @DisplayName("Given Person Id when Find By Id then Return Person Object")
    @Test
    void testGivenPersonId_whenFindById_thenReturnPersonObject(){
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));

        Person foundedPerson = personService.findById(1L);

        assertNotNull(foundedPerson);
        assertEquals("Marcos", foundedPerson.getFirstName());
    }

    @DisplayName("Given Person Object when Update Person then Return Updated Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject(){
        person.setId(1L);
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));

        person.setEmail("vitor@gmail.com");
        person.setFirstName("Vitor");

        given(personRepository.save(person)).willReturn(person);

        Person updatedPerson = personService.updatePerson(person);

        assertNotNull(updatedPerson);
        assertEquals("Vitor", updatedPerson.getFirstName());
        assertEquals("vitor@gmail.com", updatedPerson.getEmail());
    }

}