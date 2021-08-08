FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
COPY /build/libs/discovery-server-0.0.1-SNAPSHOT.jar discovery-server-api.jar
ENTRYPOINT ["java","-jar","/discovery-server-api.jar"]