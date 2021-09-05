package se.snorbu.springbootlabs.actuatorcustom.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "connections")
public class ConnectionsHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        String status = "UP";
        Map<String, String> details = new HashMap<>();
        for (Map.Entry<String, String> entry : getUrls().entrySet()) {
            if (checkConnection(entry.getValue())) {
                details.put(entry.getKey(), "Connected");
            } else {
                details.put(entry.getValue(), "No connection");
                status = "DOWN";
            }
        }

        return Health.status(status)
                .withDetails(details)
                .build();
    }

    private Map<String, String> getUrls() {
        return Map.of(
                "Spring", "https://spring.io/guides",
                "Maven", "https://maven.apache.org/run.html"
                , "Gradle", "https://gradle.none/maven-vs-gradle/"
        );
    }

    private boolean checkConnection(String inputUrl) {
        URL url;
        try {
            url = new URL(inputUrl);
        } catch (MalformedURLException e) {
            return false;
        }

        InetSocketAddress socketAddress = new InetSocketAddress(url.getHost(), url.getDefaultPort());
        Socket socket = new Socket();
        try {
            socket.connect(socketAddress);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
