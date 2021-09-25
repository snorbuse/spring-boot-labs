package se.snorbu.springbootlabs.featureflags.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import se.snorbu.springbootlabs.featureflags.model.Feature;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "snorbuse")
public class FeatureFlags {
    private Map<String, Feature> features;
}
