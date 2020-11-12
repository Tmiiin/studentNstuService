FROM openjdk:8u252-jdk
EXPOSE 8888
EXPOSE 5432
COPY target/hello-service-*.jar hello-service.jar
ENTRYPOINT exec java $JAVA_OPTS -jar hello-service.jar