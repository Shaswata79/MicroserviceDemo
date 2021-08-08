FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
COPY /build/libs/config-server-0.0.1-SNAPSHOT.jar config-server-api.jar
ENTRYPOINT ["java","-jar","/config-server-api.jar"]