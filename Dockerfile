FROM openjdk:8
EXPOSE 8083
ADD target/cloudvendor-integration.jar cloudvendor-integration.jar
ENTRYPOINT ["java", "-jar","/cloudvendor-integration.jar"]