FROM openjdk:11

RUN mkdir /app
COPY build/libs/order-generation-service.jar /app

ENTRYPOINT  java ${JAVA_OPTS} -jar order-generation-service.jar
