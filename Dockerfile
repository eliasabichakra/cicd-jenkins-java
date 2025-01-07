# Step 1: Build the JAR file using Maven
FROM maven:3.9.9-eclipse-temurin-23 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and src directory to the container
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean package -DskipTests

# Step 2: Create the final image with OpenJDK
FROM eclipse-temurin:23-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the final image
COPY --from=build /app/target/java-demo-1.0-SNAPSHOT.jar .

# Expose the port the app will run on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "java-demo-1.0-SNAPSHOT.jar"]
