FROM openjdk:8-jdk-alpine
#RUN mkdir /root/.aws
COPY target/fruit-0.0.1.jar app.jar
#COPY aws/ /root/.aws/
ENTRYPOINT ["java","-jar","/app.jar"]
