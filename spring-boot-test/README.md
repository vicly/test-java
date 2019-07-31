**NOT COMPLETED YET**

Demonstrate how to create a REST service. `web`, spring `JPA`, `H2`

https://spring.io/guides/tutorials/bookmarks/


```
gradle build

java -jar build/libs/spring-boot-test-0.1.0.jar
```

http://localhost:8080/greeting?name=Vic




keystore files MUST be on the file system for embedded Tomcat to read them. They can NOT be embedded in JAR files. (And frankly, that type of security item should NOT be part of your deliverable anyway.)
