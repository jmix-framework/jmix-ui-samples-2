FROM openjdk:17-jdk-slim

COPY build/libs/jmix-flowui-sampler.jar jmix-flowui-sampler.jar

ENTRYPOINT ["java", "-jar", "/jmix-flowui-sampler.jar"]
