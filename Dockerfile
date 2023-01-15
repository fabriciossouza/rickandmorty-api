FROM maven:3.8.1-openjdk-17-slim AS build
RUN mkdir -p /root/.m2 && mkdir /root/.m2/repository
RUN mkdir /opt/rickandmorty-api
WORKDIR /opt/rickandmorty-api
COPY . /opt/rickandmorty-api
ARG CI_JOB_TOKEN
RUN mvn clean package

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /opt/rickandmorty-api/target/rickandmorty-api*.jar rickandmorty-api.jar
EXPOSE 8080
ENTRYPOINT java ${ADDITIONAL_OPTS} -jar rickandmorty-api.jar
