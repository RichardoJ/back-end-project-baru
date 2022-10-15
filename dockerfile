FROM openjdk:16-jdk-alpine

# # Create app directory
# RUN mkdir -p /app
# WORKDIR /app

# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./

# RUN ./mvnw dependency:go-offline
 
# COPY src ./src
 
# CMD ["./mvnw", "spring-boot:run"]

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]