


#### Run project in Gradle
```
export JAVA_OPTS=-Xmx1024m # If needed

./gradlew :spring-boot-study:bootRun

./gradlew :spring-boot-study:bootRun --args='--spring.profiles.active=dev'
```

#### Run in Maven

```
export MAVEN_OPTS=-Xmx1024m # If needed

mvn spring-boot:run
```

#### Run JAR with remote debugging support

```
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
        -jar target/myapplication-0.0.1-SNAPSHOT.jar
```

#### How to disable devtools

```
-Dspring.devtools.restart.enabled=false
```

#### How to use `spring-boot-devtools`

1. Add dependency, see `build.gradle`
2. Install LiveReload Chrome extension
3. In IDE, run `Application.java`
4. Change files
5. `CMD + F9`: rebuild project, so devtools will reload changes