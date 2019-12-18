FROM openjdk:13-jdk-slim
COPY target/apirest-0.0.1-SNAPSHOT.jar apirest.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "apirest.jar"]
