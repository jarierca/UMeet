FROM openjdk:11
VOLUME /tmp
EXPOSE 8090
ADD ./target/umeet-0.0.1-SNAPSHOT.jar Umeet.jar
ENTRYPOINT ["java", "-jar", "/Umeet.jar"]