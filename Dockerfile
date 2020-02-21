ARG docker_service_name=kafka-dispatcher
ARG docker_version=1.0.0

# prod build stage
FROM openjdk:11 as prod
ARG docker_service_name
ARG docker_version

WORKDIR /code
COPY ./target/${docker_service_name}.jar /code/target/${docker_service_name}.jar
COPY ./src/main/resources/application.yml /code/application.yml

EXPOSE 8080

CMD ["java", "-jar", "/code/target/kafka-dispatcher.jar", "--spring.config.location=/code/application.yml"]

# LABEL is added as the last step only to make sure previous layers are not labeled
LABEL service=${docker_service_name} image=prod build=${docker_version}
