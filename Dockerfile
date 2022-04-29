FROM adoptopenjdk/openjdk11:alpine-jre
#RUN mkdir /root/.aws
COPY target/fruit-0.0.1.jar app.jar
#COPY aws/ /root/.aws/
ENTRYPOINT ["java","-jar","/app.jar"]
