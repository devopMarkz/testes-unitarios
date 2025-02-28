package com.erudio.app_test.repositories;

import com.erudio.app_test.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    @Query("SELECT obj FROM Person obj WHERE obj.firstName = :firstName AND obj.lastName = :lastName")
    Person findByJPQL(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
