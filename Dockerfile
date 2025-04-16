FROM eclipse-temurin:17.0.7_7-jre AS builder
COPY . /src
WORKDIR /src
RUN ./mvnw clean package


FROM eclipse-temurin:17.0.7_7-jre
WORKDIR /app
COPY --from=builder /src/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]