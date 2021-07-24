FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app
COPY build/libs/partner-0.0.1-SNAPSHOT.*jar .

EXPOSE 8080

RUN addgroup -S group && adduser -S user -G group
USER user:group

ENTRYPOINT ["java","-jar","app.jar"]