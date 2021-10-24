package se.snorbu.springbootlabs.jdbctemplate.domain.model;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value(staticConstructor = "of")
public class Person {
    private int id;
    private String firstname;
    private String lastname;
    private List<Car> cars;

    public Person with(Car car) {
        List<Car> cars = new ArrayList<>();
        cars.addAll(this.cars);
        cars.add(car);

        return new Person(
                this.id,
                this.firstname,
                this.lastname,
                cars
        );
    }
}
