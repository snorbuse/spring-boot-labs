package se.snorbu.springbootlabs.jdbctemplate.domain.persistence;

import se.snorbu.springbootlabs.jdbctemplate.domain.model.Person;

import java.util.List;

public interface PersonDatabase {
    void save(Person person);

    List<Person> findAll();

    Person find(int id);
}
