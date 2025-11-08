# --- 1. Build Stage ---
# We use "multi-stage build" to create a lightweight final container
# We use the official Maven image that contains JDK 17 to build the project
FROM maven:3.9.6-eclipse-temurin-17-focal AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml first (to leverage Docker cache)
COPY pom.xml .

# Copy the rest of the project source code
COPY src ./src

# Run Maven command to build the project (it will download dependencies and build the .jar file)
# We use -DskipTests to speed up the build process (you can remove it if you want to run tests inside Docker)
RUN mvn clean package -DskipTests


# --- 2. Run Stage ---
# We use an official and lightweight JDK 17 image (slim) to run the application
FROM eclipse-temurin:17-jre-focal

# Set the working directory
WORKDIR /app

# Copy only the .jar file that was built in the previous stage
# (Replace 'delivery-optimizer-0.0.1-SNAPSHOT.jar' with the actual JAR file name if different)
COPY --from=builder /app/target/delivery-optimizer-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which the Spring application runs (usually 8080)
EXPOSE 8080

# Default command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]