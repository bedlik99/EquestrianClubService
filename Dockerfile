FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=DOWNLOAD_JAR_AND_RUN/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

#docker build -t janbed/eqservice .
#docker run --name eqservice -d -p 8092:8080 janbed/eqservice
