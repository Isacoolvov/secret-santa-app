# Use the official Maven image with OpenJDK 17
FROM maven:latest

# Set the working directory inside the container
WORKDIR /app

# Copy the current directory's contents into the container at /app
COPY . .

# Build the Maven project inside the container
COPY pom.xml .
RUN mvn clean install -DskipTests

# Command to run the Spring Boot application using Maven
CMD ["mvn", "spring-boot:run"]