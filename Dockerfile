FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

COPY gradle gradle
COPY gradlew build.gradle* settings.gradle* ./
RUN chmod +x gradlew
RUN ./gradlew -q --no-daemon dependencies

COPY src src
RUN ./gradlew -q --no-daemon -x test bootJar

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=build /app/build/libs/*.jar /app/app.jar

ENV PORT=8088
EXPOSE 8088

CMD ["sh", "-c", "java -Dserver.port=${PORT:-8088} -jar /app/app.jar"]
