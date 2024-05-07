FROM eclipse-temurin:17

LABEL mentainer="maulanamasiqbal@gmail.com"

WORKDIR /app

COPY target/login-system-0.0.1-SNAPSHOT.jar /app/login-system.jar

ENTRYPOINT ["java", "-jar", "login-system.jar"]