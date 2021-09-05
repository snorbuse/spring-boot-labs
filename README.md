# Spring Boot labs

## Actuator - with custom metrics

```bash
# Fetch the custom health information
curl -s localhost:8080/actuator/health | jq 
```

```json
{
  "status": "DOWN",
  "components": {
    "connections": {
      "status": "DOWN",
      "details": {
        "Maven": "Connected",
        "https://gradle.none/maven-vs-gradle/": "No connection",
        "Spring": "Connected"
      }
    },
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 170114576384,
        "free": 41044598784,
        "threshold": 10485760,
        "exists": true
      }
    },
    "ping": {
      "status": "UP"
    }
  }
}

```
