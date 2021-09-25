package se.snorbu.springbootlabs.featureflags.model;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Data
public class Feature {
    private FeatureStatus status = FeatureStatus.INACTIVE;
    private List<String> users = List.of();

    public boolean isActive() {
        if (this.status == FeatureStatus.USERS) {
            String user = SecurityContextHolder.getContext().getAuthentication().getName();
            return this.users.contains(user);
        }
        return this.status == FeatureStatus.ACTIVE;
    }
}
