package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    Person insertPerson(UUID id, Person person);
    default Person insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    public List<Person> getAllPerson();
    public Optional<Person> selectPersonById(UUID id);
    public Person updatePersonById(Person person);
    public boolean deletePersonById(UUID id);
}
