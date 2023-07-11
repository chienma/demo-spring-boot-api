package com.example.demo.dao;

import com.example.demo.model.Person;
import com.example.demo.model.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person insertPerson(UUID id, Person person) {
        boolean check = jdbcTemplate.update("INSERT INTO person(id, name) VALUES (?, ?)", id, person.getName()) > 0;
        if (check) return new Person(id, person.getName());
        return null;
    }

    @Override
    public List<Person> getAllPerson() {
        return jdbcTemplate.query("SELECT id, name FROM person", new PersonMapper());
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        Person person = jdbcTemplate.queryForObject("SELECT * FROM person WHERE id = ?", new PersonMapper(), id);
        return Optional.ofNullable(person);
    }

    @Override
    public Person updatePersonById(Person person) {
        boolean check = jdbcTemplate.update("UPDATE person SET name = ? WHERE id = ?", person.getName(), person.getId()) > 0;
        if (check) return person;
        return null;
    }

    @Override
    public boolean deletePersonById(UUID id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id = ?", id) > 0;
    }
}
