# Zé Patner Challenge - Backend Software Engineer (Vaga afirmativa para Mulheres)

### Reference Documentation
What you’ll need:

* JDK 11 or later
* Spring Boot
* Gradle
* Docker
* Makefiles

### Guides
The following guides illustrate how to use some features concretely:

Method	| Path	| Description
--- | --- | --- |
GET	| /partner/nearest/long/{long}/lat/{lat}	| Get nearest partner between long and lat
GET	| /{id}	| Get partner by ID
POST	| /partner	| Create partner

### Commands

To run the integration and unit tests, run:
```shell
make test
```
To build and start the application and MongoDB, run:
```shell
make run
```
To stop the application and MongoDB, run:
```shell
make down
```

### Swagger
You can also check the swagger page here:

http://localhost:8081/partner/swagger-ui.html

