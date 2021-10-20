package se.snorbu.springlabs.resiliencebus.event;

import lombok.Value;

@Value
public class CatAgeEvent {
    private int id;
    private int age;
}
