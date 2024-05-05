FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/orders-service-0.0.1-SNAPSHOT.jar /app/orders-service.jar
ENTRYPOINT ["java", "-jar", "orders-service.jar"]


COPY target/profile-service-0.0.1-SNAPSHOT.jar /app/profile-service.jar
ENTRYPOINT ["java", "-jar", "profile-service.jar"]