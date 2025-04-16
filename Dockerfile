FROM eclipse-temurin:17.0.7_7-jdk AS builder
WORKDIR /workspace
COPY . .
RUN ./mvnw clean package -DskipTests



FROM eclipse-temurin:17.0.7_7-jre
RUN mkdir -p /app
WORKDIR /app
COPY --from=builder /workspace/target/bank-operation-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]