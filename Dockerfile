FROM maven:3.6.3-openjdk-17 AS build
COPY ./src /app/src
COPY /pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:17
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "/app.jar"]