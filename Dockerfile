FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/franquicias-0.0.1-SNAPSHOT.jar franquicias.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "franquicias.jar"]
