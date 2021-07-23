FROM adoptopenjdk/openjdk11:alpine-jre
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

ENV JAR_NAME partner-*.jar

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]