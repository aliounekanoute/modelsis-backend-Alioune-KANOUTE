FROM maven:3.8.5-openjdk-17

RUN mkdir app
WORKDIR /app

CMD mvn clean install -DskipTests && mvn spring-boot:run