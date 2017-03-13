FROM java:8
VOLUME /tmp
ADD target/fortune_cookie_service.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar" ]
