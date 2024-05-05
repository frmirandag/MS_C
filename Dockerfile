FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/Atenciones-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_TESTER1 /app/oracle_wallet1
EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]