#
# Build stage
#
FROM maven:3.9.2 AS build
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-jdk-alpine
WORKDIR /home/application
COPY --from=build  /home/app/target/*.jar /home/application/app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/home/application/app.jar"]
