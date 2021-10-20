package se.snorbu.springlabs.resiliencebus.helper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepHelper {

    public static void sleep(int millis) {
        try {
//            log.info("Sleeping for {} ms", millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("Kunde inte sova", e);
        }
    }
}
