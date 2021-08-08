FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
COPY /build/libs/movie-catalog-0.0.1-SNAPSHOT.jar movie-catalog-api.jar
ENTRYPOINT ["java","-jar","/movie-catalog-api.jar"]