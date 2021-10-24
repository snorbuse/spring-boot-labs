package se.snorbu.springbootlabs.jdbctemplate.domain.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class Car {
    private String regnr;
    private String model;
}
