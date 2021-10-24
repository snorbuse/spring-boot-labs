package se.snorbu.springbootlabs.jdbctemplate.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import se.snorbu.springbootlabs.jdbctemplate.domain.model.Car;
import se.snorbu.springbootlabs.jdbctemplate.domain.model.Person;
import se.snorbu.springbootlabs.jdbctemplate.domain.persistence.PersonDatabase;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JdbcTemplatePersonDatabase implements PersonDatabase {

    private static final QueryOptions QUERY_OPTIONS = QueryOptions.MULTI_QUERY;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person VALUES(?, ?, ?)",
                person.getId(),
                person.getFirstname(),
                person.getLastname()
        );

        for (Car car : person.getCars()) {
            jdbcTemplate.update("INSERT INTO car (personid, regnr, model) VALUES (?, ?, ?)",
                    person.getId(),
                    car.getRegnr(),
                    car.getModel()
            );
        }
    }

    @Override
    public Person find(int personid) {
        List<Car> cars = jdbcTemplate.query("SELECT regnr, model FROM car WHERE personid=?",
                (rs, rowNum) -> Car.of(rs.getString("regnr"), rs.getString("model")),
                personid
        );

        List<Person> queryResult = jdbcTemplate
                .query("SELECT personid, firstname, lastname FROM person WHERE personid=?",
                        (rs, rowNum) -> Person.of(
                                rs.getInt("personid"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                cars
                        ),
                        personid
                );

        if (queryResult.size() == 0) {
            throw new RuntimeException("Hittade ingen person med personid=" + personid);
        } else if (queryResult.size() > 1) {
            throw new RuntimeException("Hittade fler personer med samma personid=" + personid);
        }
        return queryResult.get(0);
    }


    @Override
    public List<Person> findAll() {
        return QUERY_OPTIONS == QueryOptions.SINGLE_QUERY ? singleQuery() : multiQuery();
    }

    private List<Person> multiQuery() {
        log.info("Finding all persons in multiple queries");
        return findAllIds().stream()
                .map(this::find)
                .collect(Collectors.toList());
    }

    private List<Person> singleQuery() {
        log.info("Finding all persons in one single query");
        return jdbcTemplate.query("""
                        SELECT p.personid, firstname, lastname, regnr, model FROM person AS p 
                        LEFT JOIN car AS c ON c.personid = p.personid
                        """,
                new PersonMapper()
        );
    }

    private List<Integer> findAllIds() {
        return jdbcTemplate.queryForList("SELECT personid FROM person", Integer.class);
    }

    private enum QueryOptions {
        SINGLE_QUERY, MULTI_QUERY
    }
}
