package se.snorbu.springbootlabs.featureflags.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.snorbu.springbootlabs.featureflags.model.ResponseModel;
import se.snorbu.springbootlabs.featureflags.service.GreeterService;
import se.snorbu.springbootlabs.featureflags.service.StatusService;

@RestController
public class MainController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private GreeterService greeterService;

    @GetMapping("")
    public ResponseModel getIndex() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseModel.of(
                greeterService.greetUser(user),
                statusService.getStatus()
        );
    }
}
