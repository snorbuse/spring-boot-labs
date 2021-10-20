package se.snorbu.springlabs.resiliencebus.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.snorbu.springlabs.resiliencebus.core.DomainCoordinator;

import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private DomainCoordinator domainCoordinator;

    @PostMapping("/cat")
    public Map<String, String> postCat(@RequestBody Map<String, String> body) {
        domainCoordinator.createNewCat(body.get("name"));

        return Map.of("status", "ok");
    }
}
