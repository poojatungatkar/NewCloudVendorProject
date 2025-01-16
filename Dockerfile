FROM openjdk:17-alpine
EXPOSE 8082
ADD target/cloudvendor-integration.jar cloudvendor-integration.jar
ENTRYPOINT ["java", "-jar","/cloudvendor-integration.jar"]