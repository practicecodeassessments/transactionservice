FROM amazoncorretto:17.0.7-alpine
MAINTAINER RC
COPY target/transaction-service.jar transaction-service.jar
ENTRYPOINT ["java","-jar","/transaction-service.jar"]