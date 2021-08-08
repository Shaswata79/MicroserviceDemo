FROM openjdk:11
VOLUME /tmp
EXPOSE 8083
COPY /build/libs/ratings-data-0.0.1-SNAPSHOT.jar ratings-data-api.jar
ENTRYPOINT ["java","-jar","/ratings-data-api.jar"]