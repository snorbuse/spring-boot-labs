package se.snorbu.springlabs.resiliencebus.event;

import lombok.Value;

@Value
public class CatEvent implements Event {
    private int id;
    private String name;
}
