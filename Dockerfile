FROM gradle:8-jdk17 AS build

WORKDIR /app

COPY gradlew ./ 
COPY gradlew.bat ./ 
COPY gradle ./gradle 

COPY build.gradle settings.gradle ./ 

RUN chmod +x gradlew

RUN ./gradlew --no-daemon build -x test || true

COPY . .

RUN chmod +x gradlew

RUN ./gradlew --no-daemon clean bootJar

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "app.jar"]
