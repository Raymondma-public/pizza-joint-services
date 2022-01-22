FROM openjdk:11

RUN mkdir /app
COPY build/libs/order-persistence-service.jar /app

WORKDIR /app

ENTRYPOINT  java ${JAVA_OPTS} -jar order-persistence-service.jar
