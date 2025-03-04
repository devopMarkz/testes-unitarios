package com.erudio.app_test.controllers;

import com.erudio.app_test.entities.Person;
import com.erudio.app_test.services.PersonService;
import com.erudio.app_test.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PersonService service;

    private Person person;

    @BeforeEach
    public void setup() {
        // Given / Arrange
        person = new Person(
                "Leandro",
                "Costa",
                "leandro@erudio.com.br",
                "Uberlândia - Minas Gerais - Brasil",
                "Male");
    }

    @Test
    @DisplayName("JUnit test for Given Person Object when Create Person then Return Saved Person")
    void testGivenPersonObject_WhenCreatePerson_thenReturnSavedPerson() throws JsonProcessingException, Exception {

        // Given / Arrange
        given(service.createPerson(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // When / Act
        ResultActions response = mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person)));

        // Then / Assert
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())))
                .andExpect(jsonPath("$.address").value(person.getAddress()));
    }

    @Test
    @DisplayName("Given List Of Persons when FindAll Persons then Return Persons List")
    void testGivenListOfPersons_whenFindAllPersons_thenReturnPersonsList() throws Exception {

        // Arrange
        Person otherPerson = new Person("Marcos", "André", "marcos@gmail.com", "São Luís - Maranhão - Brasil", "Male");
        List<Person> personList = List.of(person, otherPerson);
        given(service.findAll()).willReturn(personList);

        // Simula a requisição
        ResultActions response = mockMvc.perform(get("/person"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(personList.size())));
    }

    @Test
    @DisplayName("Given Person Id when FindById then Return Person Object")
    void testGivenPersonId_whenFindById_thenReturnPersonObject() throws JsonProcessingException, Exception {
        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willReturn(person);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then / Assert
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())))
                .andExpect(jsonPath("$.address").value(person.getAddress()));;
    }

    @Test
    @DisplayName("Given Invalid Person Id when FindById then Return Not Found")
    void testGivenInvalidPersonId_whenFindById_thenReturnNotFound() throws JsonProcessingException, Exception {
        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then / Assert
        response.
                andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("Given Updated Person When Update Person then Return Updated Person")
    void testGivenUpdatedPerson_WhenUpdatePerson_thenReturnUpdatedPerson() throws JsonProcessingException, Exception {

        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willReturn(person);
        given(service.updatePerson(any(Person.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        // When / Act
        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(
                        new Person("Vitor", "Nascimento", "Maracanã", "Male", "vitor@gmail.com")
                ))
        );

        // Then / Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Vitor")));
    }

    @Test
    @DisplayName("Given Person Id When DeletePerson then Return No Content")
    void testGivenPersonId_WhenDeletePerson_thenReturnNoContent() throws JsonProcessingException, Exception {

        // Given / Arrange
        long personId = 1L;
        willDoNothing().given(service).deletePerson(personId);

        // When / Act
        ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

        // Then / Assert
        response.andExpect(status().isNoContent());
    }

}
