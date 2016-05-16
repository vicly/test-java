## Overview
* Jersey + Swagger
* Swagger annotated resource class and model
* Auto generate swagger api schema doc and expose via HTTP endpoint
* **TODO**: scan annotation --> gen Swagger Json --> gen client code -> package

## Build and run
```
cd jersey-swagger-test
../gradlew jettyRun
```

## Access API and Swagger API doc
```
// API
http://localhost:8080/jersey-swagger-test/api/users/1

// swagger API-doc
http://localhost:8080/jersey-swagger-test/api/swagger
http://localhost:8080/jersey-swagger-test/api/swagger.json
http://localhost:8080/jersey-swagger-test/api/swagger.yaml
```

## Ref
* See `JerseySwaggerApplication.java` for Swagger configuration details
* https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-Jersey-2.X-Project-Setup-1.5
