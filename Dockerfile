#Runner
FROM openjdk:17-alpine
ADD build/libs/platform.jar /app.jar
#COPY setup.sql /docker-entrypoint-initdb.d/

ENTRYPOINT java \
              $JAVA_OPTS \
              -Dspring.datasource.platformdb.url= $spring_datasource_platformdb_url \
              -Dspring.datasource.platformdb.name= $spring_datasource_platformdb_name \
              -Dspring.datasource.platformdb.password= $spring_datasource_platformdb_password \
              -Dspring.datasource.platformdb.hikari.connectiontimeout= $spring_datasource_platformdb_hikari_connectiontimeout \
              -Dspring.datasource.platformdb.hikari.maximumpoolsize= $spring_datasource_platformdb_hikari_maximumpoolsize \
              -Dspring.datasource.platformdb.hikari.poolname= $spring_datasource_platformdb_hikari_poolname \
              -Dspring.datasource.platformdb.hikari.autocommit= $spring_datasource_platformdb_hikari_autocommit \
              -Dspring_application_context_path= $spring_application_context_path \
             -jar \
             app.jar

EXPOSE 8080
