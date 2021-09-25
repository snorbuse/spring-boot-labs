package se.snorbu.springbootlabs.featureflags.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.snorbu.springbootlabs.featureflags.config.FeatureFlags;
import se.snorbu.springbootlabs.featureflags.model.Feature;

@Slf4j
@Service
public class FeatureService {

    @Autowired
    private FeatureFlags featureFlags;

    public Feature feature(String name) {
        if (!featureFlags.getFeatures().containsKey(name)) {
            log.error("Feature '{}' does not exist, returning inactive status", name);
            return new Feature();
        }
        return featureFlags.getFeatures().get(name);
    }
}
