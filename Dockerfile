#FROM openjdk:19-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ./target/lab1-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
FROM openjdk:19-jdk-alpine
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN apt-get update && apt-get install -y maven
RUN mvn clean install
COPY target/*.jar app.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","app.jar"]