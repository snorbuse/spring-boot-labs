package se.snorbu.springbootlabs.featureflags.service.status;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import se.snorbu.springbootlabs.featureflags.service.StatusService;

@Service
@ConditionalOnProperty(name = "snorbuse.features.nice-status.status", havingValue = "ACTIVE")
public class StatusFineService implements StatusService {

    public String getStatus() {
        return "This is fine";
    }

}
