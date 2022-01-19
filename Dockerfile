FROM openjdk:11
ADD target/*.jar settings_ms
ENTRYPOINT ["java", "-jar","settings_ms"]
EXPOSE 8082