FROM openjdk:11
VOLUME /tmp
EXPOSE 8082
COPY /build/libs/movie-info-0.0.1-SNAPSHOT.jar movie-info-api.jar
ENTRYPOINT ["java","-jar","/movie-info-api.jar"]