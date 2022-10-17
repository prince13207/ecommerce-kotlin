###### Platform SpringBoot Kotlin App

This service interacts with postgresDB to perform CRUD ops for platform's order data

###### Building the app

     ./gradlew clean build

###### Run Configs

    -Dspring.datasource.platformdb.url= jdbc:postgresql://postgres:5432/platform_db?currentSchema=public
    -Dspring.datasource.platformdb.name= platformuser
    -Dspring.datasource.platformdb.password= platformpwd
    -Dspring.datasource.platformdb.hikari.connectiontimeout=60000
    -Dspring.datasource.platformdb.hikari.maximumpoolsize=5
    -Dspring.datasource.platformdb.hikari.poolname=platformdb
    -Dspring.datasource.platformdb.hikari.autocommit=false
    -Dservice.name=platform
    -Dspring_application_context_path=/platform

###### Running the app

1.Run the app independently

You can bring up postgres using

      docker-compose up postgres

Start the SpringBootApp in IDE using the above specified env args

2.Running docker-compose to start both postgres and platformApp:

      docker-compose up 
      docker container ls

3.Running app through Dockerfile and postgres through DockerCompose:

      docker-compose up postgres
      docker build -t platform .
      docker images
      docker run --env-file=docker-env-file platform
      docker container ls

Note:

* All the env args are specified in the docker-env-file
* PostgresSQL startup create script is specified within DockerCompose [setup.sql]
* Verify running instances from docker Desktop

###### DB connections

Once DB instance is up, from any of your favorite DB editors or intelliJ :

    URL : jdbc:postgresql://localhost:5432/platform_db
    HOST : localhost
    USERNAME : platformuser
    PASSWORD : platformpwd

###### Swagger

http://localhost:8080/platform/swagger-ui/index.html

######

App Specific Notes:

* Make sure to convert entities to dtos in controller response.
* Add global exception handler to handle various error scenarios and input validations if any
* Checkout migration from springfox vs springdoc
  - https://springdoc.org/migrating-from-springfox.html
