FROM java:8
COPY rest-api/target/rest-api-0.0.1-SNAPSHOT-spring-boot.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
