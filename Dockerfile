FROM openjdk
COPY target/*.jar order-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","order-service.jar"]