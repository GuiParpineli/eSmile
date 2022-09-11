FROM openjdk:11
WORKDIR /
COPY target/app_esmile-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
