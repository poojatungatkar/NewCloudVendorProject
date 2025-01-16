FROM openjdk:8
EXPOSE 8083
ADD target/CloudVendorIntegration.jar CloudVendorIntegration.jar
ENTRYPOINT ["java", "-jar","/cloudvendor-integration.jar"]