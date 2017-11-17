FROM java:8u111-jre-alpine

WORKDIR /app

ADD target/blockframe-1.0-SNAPSHOT-jar-with-dependencies.jar /app

EXPOSE 4567

ENTRYPOINT ["java", "-jar", "blockframe-1.0-SNAPSHOT-jar-with-dependencies.jar"]

