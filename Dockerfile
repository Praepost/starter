FROM openjdk:11
ADD service/target/service-2.7.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]