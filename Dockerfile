FROM maven:3.6.3-openjdk-17 AS build
LABEL authors="lucas"

COPY src ./home/app/src
COPY pom.xml ./home/app/pom.xml
RUN mvn -f ./home/app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17
COPY --from=build /home/app/target/app.jar /user/local/lib/app.jar
COPY target/app.jar /user/local/lib/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/user/local/lib/app.jar"]