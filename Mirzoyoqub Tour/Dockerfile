## Stage 1: Build the application using Maven
#FROM maven:3.9.6-eclipse-temurin-17 AS builder
#
## Set working directory
#WORKDIR /app
#
## Copy pom.xml and download dependencies
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#
## Copy the source code
#COPY src ./src
#
## Package the application
#RUN mvn clean package -DskipTests
#
## Stage 2: Run the built JAR
#FROM eclipse-temurin:17-jdk-alpine
#
## Set working directory
#WORKDIR /app
#
## Copy the JAR file from the builder stage
#COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
#
## Expose the application port
#EXPOSE 8080
#
## Run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]
## Stage 1: Build the application using Maven
#FROM maven:3.9.6-eclipse-temurin-17 AS builder
#
## Set working directory
#WORKDIR /app
#
## Copy pom.xml and download dependencies
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#
## Copy the source code
#COPY src ./src
#
## Package the application
#RUN mvn clean package -DskipTests
#
## Stage 2: Run the built JAR
#FROM eclipse-temurin:17-jdk-alpine
#
## Set working directory
#WORKDIR /app
#
## Copy the JAR file from the builder stage
#COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
#
## Expose the application port
#EXPOSE 8080
#
## Run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]


FROM maven:3.8.3-openjdk-17
COPY . .
RUN mvn clean package -DskipTests



FROM openjdk:17 as layers
WORKDIR /tmp
COPY target/*jar app.jar

RUN java -Djarmode=layerstools -jar app.jar extract

FROM openjdk:17
WORKDIR /tmp

RUN useradd -ms /bin/bash spring-user
USER spring-user


COPY --from=layers /tmp/dependencies/ ./
COPY --from=layers /tmp/spring-boot-loader/ ./
COPY --from=layers /tmp/snapshot-dependencies ./
COPY --from=layers /tmp/application/ ./

EXPOSE 8080

ENTRYPOINT exec java org.springframework.boot.loader.JarLauncher
