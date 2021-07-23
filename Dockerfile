FROM adoptopenjdk/openjdk11:alpine-jre

RUN addgroup -S zegroup && adduser -S zeuser -G zegroup
USER zeuser:zegroup

WORKDIR /opt/app
COPY target/ze-partner-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]