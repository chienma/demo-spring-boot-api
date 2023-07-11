package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> listOfPerson = new ArrayList<>();

    @Override
    public Person insertPerson(UUID id, Person person) {
        Person newPerson = new Person(id, person.getName());
        listOfPerson.add(newPerson);
        return newPerson;
    }

    @Override
    public List<Person> getAllPerson() {
        return listOfPerson;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return listOfPerson.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public Person updatePersonById(Person newPerson) {
        Person oldPerson  = selectPersonById(newPerson.getId()).get();
        if(oldPerson == null) return null;
        int index = listOfPerson.indexOf(oldPerson);
        listOfPerson.set(index, newPerson);
        return newPerson;
    }

    @Override
    public boolean deletePersonById(UUID id) {
        Person person = selectPersonById(id).orElse(null);
        if (person == null) return false;
        return listOfPerson.remove(person);
    }
}
