FROM java:8
VOLUME /tmp
ADD target/fortune_cookie_repository.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["sh", "-c", "java -jar /app.jar" ]
