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

## Feature flags
This is a sample project which enables different features according to the corresponding feature flag.
You can either enable a global flag/feature, which is enabled/disabled for all users. 
Or you can enable the feature for a special set of users.

### Example 1a - nice greeting message
This displays a nice welcome message that says godday to the user.
This feature is enabled just for user1 and user2.

```bash
# Make a request for user 1
curl -s localhost:8080 --user user1:password | jq
```

```json
{
  "message": "Godday user1",
  "status": "This is fine"
}
```

### Example 1b - standard greeting message
This displays the standard welcome message for all other users (in this case user3).

```bash
# Make a request for user 3
curl -s localhost:8080 --user user3:password | jq
```

```json
{
  "message": "Hello user1",
  "status": "This is fine"
}
```