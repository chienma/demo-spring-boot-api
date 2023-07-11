package com.example.demo.service;

import com.example.demo.dao.FakePersonDataAccessService;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;
    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }
    public Person addPerson(Person person) {
        return personDao.insertPerson(person);
    }
    public List<Person> getAllPerson() {
        return personDao.getAllPerson();
    }
    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }
    public Person updatePerson(Person person) {
        return  personDao.updatePersonById(person);
    }
    public boolean deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }
}
