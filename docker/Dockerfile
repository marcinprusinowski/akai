FROM openjdk:19-alpine as build

COPY . /app
WORKDIR /app

ENV GRALDE_USER_HOME = "/app/.gradle/"
RUN ./gradlew assemble --build-cache --no-daemon

FROM openjdk:19-alpine
COPY --from=build /app/build/libs/*.jar /app.jar

COPY --from=build /app /app
WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8000
