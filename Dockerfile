FROM openjdk:17-jdk-slim

COPY build/libs/jmix-ui-samples-2.jar jmix-ui-samples-2.jar

ENTRYPOINT ["java", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/app/heap-dumps", "-jar", "/jmix-ui-samples-2.jar"]
