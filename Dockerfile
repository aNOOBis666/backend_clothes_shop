FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar /cl_shop.jar

ENTRYPOINT ["java", "-jar", "cl_shop.jar"]
