FROM openjdk:8
EXPOSE 8082
COPY target/cloudvendor-integration.jar cloudvendor-integration.jar
ENTRYPOINT ["java", "-jar","/cloudvendor-integration.jar"]