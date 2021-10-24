package se.snorbu.springbootlabs.jdbctemplate.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.snorbu.springbootlabs.jdbctemplate.domain.model.Car;
import se.snorbu.springbootlabs.jdbctemplate.domain.model.Person;
import se.snorbu.springbootlabs.jdbctemplate.domain.persistence.PersonDatabase;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonDatabase personDatabase;

    private int index = 1;

    @Transactional
    public void createPerson() {
        List<Car> carlist = new ArrayList<>();
        if (index == 2) {
            carlist.add(Car.of("abc123", "Saab"));
        } else if (index >= 3) {
            carlist.add(Car.of("mnb567", "VW"));
            carlist.add(Car.of("xyz098", "Volvo"));
        }
        Person person = Person.of(index, "Kalle", "Anka", carlist);

        log.info("Saving iteration {} with person {}", index, person);
        personDatabase.save(person);

        if (index == 3) {
            throw new RuntimeException("Jag vill inte skapa nr 3");
        }

        index++;
    }

    public List<Person> findPersons() {
        return personDatabase.findAll();
    }

    public Person findPerson(int id) {
        return personDatabase.find(id);
    }
}
