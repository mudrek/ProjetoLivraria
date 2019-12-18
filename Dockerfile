FROM openjdk:13-jdk-slim
COPY target/apirest-0.0.1-SNAPSHOT.jar apirest.jar

RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
     && tar xzvf docker-17.04.0-ce.tgz \
     && mv docker/docker /usr/local/bin \
     && rm -r docker docker-17.04.0-ce.tgz

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "apirest.jar"]
