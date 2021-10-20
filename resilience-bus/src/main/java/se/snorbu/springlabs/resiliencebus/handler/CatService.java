package se.snorbu.springlabs.resiliencebus.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import se.snorbu.springlabs.resiliencebus.event.CatAgeEvent;
import se.snorbu.springlabs.resiliencebus.event.CatEvent;
import se.snorbu.springlabs.resiliencebus.integration.ExternalCaller;

@Slf4j
@Service
public class CatService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ExternalCaller externalCaller;

    @Async
    @EventListener
    public void handleEvent(CatEvent event) {
//        log.info("Taking care of cat {}, {}", event.getId(), event.getName());

        StopWatch stopWatch = new StopWatch();
//        log.info("Fetching age (id={})", event.getId());
        stopWatch.start();
        int age = externalCaller.doCall(event.getId());
        stopWatch.stop();
        log.info("Fetched age ({}) in {} (id={})", age, stopWatch.getLastTaskTimeMillis(), event.getId());

        publisher.publishEvent(new CatAgeEvent(event.getId(), age));
    }

}
