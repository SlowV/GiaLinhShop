FROM openjdk:8-jdk-alpine
#RUN mkdir /root/.aws
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#COPY aws/ /root/.aws/
ENTRYPOINT ["java","-jar","/app.jar"]
