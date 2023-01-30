FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=DOWNLOAD_JAR_AND_RUN/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]