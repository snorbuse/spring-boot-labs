package se.snorbu.springlabs.resiliencebus.integration;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.snorbu.springlabs.resiliencebus.helper.SleepHelper;

import java.util.Random;

@Slf4j
@Service
public class ExternalCaller {

    private int counter = 0;

    @CircuitBreaker(name = "catbreaker", fallbackMethod = "fallback")
    public int doCall(int id) {
        counter++;
//        if (id == 2 || id == 4) {
//            throw new RuntimeException("Aj då");
//        }

        int millis = 700;
        int rand = new Random().nextInt(10);
        if (rand >= 7) {
            millis = 3000;
        }

        SleepHelper.sleep(millis);
        return 12;
    }

    public int fallback(int id, Exception ex) {
//        log.info("CircuitBreaker is active, this is fallback (cat id={})", id);
        return -1;
    }
}
