FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/cl_shop:0.0.1-SNAPSHOT.jar /app/cl_shop.jar
ENTRYPOINT ["java", "-jar", "cl_shop.jar"]


COPY target/profile:0.0.1-SNAPSHOT.jar /app/profile.jar
ENTRYPOINT ["java", "-jar", "profile.jar"]
