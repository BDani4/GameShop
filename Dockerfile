FROM openjdk:17-jdk-alpine3.14

COPY "./target/gameshop.jar" "/application/gameshop.jar"

CMD ["java", "-jar", "/application/gameshop.jar"]