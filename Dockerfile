FROM openjdk:15.0.2
ADD target/app.jar app.jar
EXPOSE 8081
CMD ["java","-jar","app.jar"]