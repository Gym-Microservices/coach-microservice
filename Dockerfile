# Multi-stage Docker build for Coach Service

# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy all POM files
COPY parent/pom.xml /app/parent/pom.xml
COPY core-microservice/pom.xml /app/core-microservice/pom.xml
COPY coach-microservice/pom.xml /app/coach-microservice/pom.xml

# Install parent POM
RUN cd /app/parent && mvn install -N

# Build core first
COPY core-microservice/src /app/core-microservice/src
RUN cd /app/core-microservice && mvn clean install -DskipTests

# Download microservice dependencies
RUN mkdir -p /app/coach-microservice/src/main/java/temp && \
    echo "public class Temp {}" > /app/coach-microservice/src/main/java/temp/Temp.java

RUN cd /app/coach-microservice && mvn dependency:go-offline -DskipTests

# Clean temp files
RUN rm -rf /app/coach-microservice/src/main/java/temp

# Build coach service
COPY coach-microservice/src /app/coach-microservice/src
RUN cd /app/coach-microservice && mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/coach-microservice/target/coach-microservice-*.jar app.jar

# Expose port
EXPOSE 8082

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
