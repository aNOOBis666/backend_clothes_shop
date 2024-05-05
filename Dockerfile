FROM openjdk:17-jdk-slim

WORKDIR /app

COPY docker.io/library/cl_shop:0.0.1-SNAPSHOT /app/cl_shop.jar
COPY docker.io/library/profile:0.0.1-SNAPSHOT.jar /app/profile.jar

ENTRYPOINT ["java", "-jar", "cl_shop.jar"]
ENTRYPOINT ["java", "-jar", "profile.jar"]
