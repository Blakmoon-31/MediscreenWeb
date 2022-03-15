FROM openjdk:11-jdk
COPY target/mediscreenWeb-0.0.1-SNAPSHOT.jar mediscreenWeb-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mediscreenWeb-0.0.1-SNAPSHOT.jar"]