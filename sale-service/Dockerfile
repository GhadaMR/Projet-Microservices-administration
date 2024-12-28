# Use a base image that has Java and Maven installed
FROM maven:3.8.5-openjdk-11 AS build

# Set the working directory
WORKDIR /app

# Build the application
RUN mvn clean package -DskipTests

# Use a Java runtime image for running the application
FROM openjdk:11-jre-slim
COPY --from=build /app/target/your-application.jar /app/your-application.jar

CMD ["java", "-jar", "/app/your-application.jar"]
