FROM openjdk:17-jdk-alpine
MAINTAINER hainamhoang.wordpress.com
COPY target/*.jar demo.jar
ENTRYPOINT ["java", "-jar", "/demo.jar"]