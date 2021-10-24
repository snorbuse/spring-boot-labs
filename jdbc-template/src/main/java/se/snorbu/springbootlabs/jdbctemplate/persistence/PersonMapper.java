package se.snorbu.springbootlabs.jdbctemplate.persistence;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import se.snorbu.springbootlabs.jdbctemplate.domain.model.Car;
import se.snorbu.springbootlabs.jdbctemplate.domain.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonMapper implements ResultSetExtractor<List<Person>> {

    @Override
    public List<Person> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Person> persons = new ArrayList<>();

        Person person = null;
        while (rs.next()) {
            if (isNewPerson(rs, person)) {
                persons.add(person);
                person = createPerson(rs);
            }
            if (person == null) {
                person = createPerson(rs);
            }


            person = addCar(rs, person);
        }

        persons.add(person);

        return persons;
    }

    private Person addCar(ResultSet rs, Person person) throws SQLException {
        Car car = createCar(rs);
        if (car != null) {
            person = person.with(createCar(rs));
        }
        return person;
    }

    private Car createCar(ResultSet rs) throws SQLException {
        if (rs.getString("regnr") == null) {
            return null;
        }
        return Car.of(rs.getString("regnr"), rs.getString("model"));
    }

    private Person createPerson(ResultSet rs) throws SQLException {
        return Person.of(
                rs.getInt("personid"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                List.of()
        );
    }

    private boolean isNewPerson(ResultSet rs, Person person) throws SQLException {
        return person != null && person.getId() != rs.getInt("personid");
    }
}
