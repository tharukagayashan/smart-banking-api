FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/*.jar /app/smart-banking-api.jar
ENTRYPOINT ["java","-jar","/app/smart-banking-api.jar"]
EXPOSE 8081