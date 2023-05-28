FROM adoptopenjdk:11-jdk-hotspot
WORKDIR /app
COPY target/contact-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "contact-0.0.1-SNAPSHOT.jar"]
