FROM docker.io/eclipse-temurin:17.0.7_7-jre

WORKDIR /walletApp
COPY target/bank-operation-0.0.1-SNAPSHOT.jar /walletApp/bank-operation-0.0.1-SNAPSHOT.jar
EXPOSE 8080/tcp
CMD ["java", "-jar", "bank-operation-0.0.1-SNAPSHOT.jar"]
