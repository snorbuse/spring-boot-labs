package se.snorbu.springlabs.resiliencebus.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import se.snorbu.springlabs.resiliencebus.event.CatEvent;

@Slf4j
@Component
public class CatLogger {

    @Async
    @EventListener
    public void logCats(CatEvent event) {
//        log.info("Found a new cat, {}", event.getName());
    }
}
