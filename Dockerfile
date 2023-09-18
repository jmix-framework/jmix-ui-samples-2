FROM openjdk:17-jdk-slim

COPY build/libs/jmix-ui-samples-2.jar jmix-ui-samples-2.jar

ENTRYPOINT ["java", "-jar", "/jmix-ui-samples-2.jar"]
