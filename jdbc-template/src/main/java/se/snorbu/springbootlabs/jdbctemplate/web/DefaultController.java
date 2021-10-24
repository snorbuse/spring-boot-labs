package se.snorbu.springbootlabs.jdbctemplate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.snorbu.springbootlabs.jdbctemplate.domain.PersonService;
import se.snorbu.springbootlabs.jdbctemplate.domain.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class DefaultController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public List<Map<String, Object>> getIndex() {
        List<Person> persons = personService.findPersons();

        return persons.stream()
                .map(p -> mapPerson(p)
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPerson(@PathVariable("id") String id) {
        Person person = personService.findPerson(Integer.parseInt(id));
        return mapPerson(person);
    }

    @PostMapping("/")
    public Map<String, String> postIndex() {
        personService.createPerson();
        return Map.of("status", "ok");
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> errorHandler(Exception exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private Map<String, Object> mapPerson(Person p) {
        return Map.of(
                "id", String.valueOf(p.getId()),
                "firstname", p.getFirstname(),
                "lastname", p.getLastname(),
                "cars", p.getCars().stream()
                        .filter(Objects::nonNull)
                        .map(c -> Map.of("regnr", c.getRegnr(), "model", c.getModel()))
                        .collect(Collectors.toList())
        );
    }
}
