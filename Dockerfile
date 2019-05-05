FROM openjdk:8-jdk-alpine

COPY ./build/libs/helloworld*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=aws"]
