FROM openjdk:17
MAINTAINER sergey sundukovskiy
COPY target/se498-final-0.0.1-SNAPSHOT.jar se498-final-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/se498-final-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080