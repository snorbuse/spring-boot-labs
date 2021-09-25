package se.snorbu.springbootlabs.featureflags.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class ResponseModel {
    private String message;
    private String status;
}
