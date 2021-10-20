package se.snorbu.springlabs.resiliencebus.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import se.snorbu.springlabs.resiliencebus.event.CatAgeEvent;
import se.snorbu.springlabs.resiliencebus.event.CatEvent;

@Slf4j
@Service
public class DomainCoordinator {

    private int currentId = 0;

    @Autowired
    private ApplicationEventPublisher publisher;

    public void createNewCat(String name) {
        publisher.publishEvent(new CatEvent(currentId++, name));
    }

    @Async
    @EventListener
    public void updateCatAge(CatAgeEvent event) {
//        log.info("Cat {} age is {}", event.getId(), event.getAge());

    }

}
