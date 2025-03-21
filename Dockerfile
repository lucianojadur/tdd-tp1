FROM openjdk:17-jdk-slim

ENV HOST=127.0.0.1
ENV PORT=8080
ENV ENVIRONMENT=development
ENV DATABASE_NAME=class-connect
ENV DATABASE_USER=user
ENV DATABASE_PASSWORD=pass

COPY build/libs/tp1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
