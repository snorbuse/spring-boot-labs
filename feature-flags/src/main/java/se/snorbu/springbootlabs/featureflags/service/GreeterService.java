package se.snorbu.springbootlabs.featureflags.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreeterService {

    @Autowired
    private FeatureService featureService;

    public String greetUser(String user) {
        if (featureService.feature("greeter").isActive()) {
            return "Godday " + user;
        }

        return "Hello " + user;
    }
}
