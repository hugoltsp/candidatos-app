FROM openjdk:8-jre-slim
WORKDIR /
ADD target/*[0-9].jar app.jar
EXPOSE 9092
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar" ]
