### BUILD ###
FROM maven:3.6.2-jdk-13
COPY app/ /app/
WORKDIR /app/
RUN mvn clean install -Dspring.profiles.active=stage dependency:purge-local-repository

### APP Image ###
FROM openjdk:13-jdk-slim
MAINTAINER Ganex <suporte@ganex.com.br>

# Arguments
ARG USER_NAME="app"
ARG USER_UID="1000"
ARG GROUP_NAME="app"
ARG GROUP_GID="1000"
ARG USER_HOME="/app/"

# Environment variables
ENV LANG="pt_BR.UTF-8" \
    LANGUAGE="pt_BR:br" \
    LC_ALL="pt_BR.UTF-8" \
    TZ="America/Sao_Paulo"

# Create a user and group used to launch processes
RUN addgroup --gid $GROUP_GID --group $GROUP_NAME \
    && adduser --uid $USER_UID --disabled-login --system --home $USER_HOME --shell /sbin/nologin --gid $GROUP_GID $USER_NAME

# Add Application
COPY --from=0 --chown=1000:1000 app/target/api-0.0.1.jar /app/dandelin-server.jar

# User
USER $USER_NAME

# Expose ports
EXPOSE 8080

# Set the default command to run on boot
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/dandelin-server.jar"]