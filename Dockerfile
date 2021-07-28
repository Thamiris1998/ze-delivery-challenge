FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app
COPY build/libs/partner-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]