FROM openjdk:8
ADD target/refactoring-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]