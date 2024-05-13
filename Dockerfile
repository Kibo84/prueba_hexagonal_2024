FROM openjdk:17-jdk-slim

LABEL authors="enriquejavierboronatponce"

WORKDIR /app

COPY target/pruebahexagonal-0.0.1-SNAPSHOT.jar /app/pruebahexagonal.jar
COPY wait-for-it.sh /app/wait-for-it.sh

RUN chmod +x /app/wait-for-it.sh

ENTRYPOINT ["/app/wait-for-it.sh", "postgres:5432", "--", "java", "-jar", "/app/pruebahexagonal.jar"]