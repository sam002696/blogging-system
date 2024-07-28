#FROM openjdk:11-jre-slim
#COPY target/booking-system-0.0.1-SNAPSHOT.jar /usr/app/booking-system-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "/usr/app/booking-system-0.0.1-SNAPSHOT.jar"]
#FROM openjdk:17-jdk
#ARG JAR_FILE=target/*.jar
#COPY ./target/booking-system-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# Use the OpenJDK 22 base image
#FROM openjdk:22-jdk-slim

# Set the working directory in the container
#WORKDIR /app

# Copy the application's JAR file to the container
#COPY ./target/booking-system-0.0.1-SNAPSHOT.jar app.jar

# Specify the command to run the application
#ENTRYPOINT ["java", "-jar", "/app.jar"]


# Use the OpenJDK 22 base image
FROM openjdk:22-jdk-slim

# Argument to specify the JAR file
ARG JAR_FILE=target/booking-system-0.0.1-SNAPSHOT.jar

# Add the JAR file to the container and rename it
ADD ${JAR_FILE} app.jar

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]


