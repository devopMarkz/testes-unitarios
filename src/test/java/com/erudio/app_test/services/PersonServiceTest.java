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

}