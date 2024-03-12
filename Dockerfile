FROM gradle:8.5-jdk17 as builder
WORKDIR /app
COPY . /app/.
RUN gradle build -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]