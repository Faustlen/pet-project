FROM openjdk:21-jdk-slim
WORKDIR /app
COPY /build/libs/ContentLoaderAdapter-0.0.1-SNAPSHOT.jar /app/ContentLoader.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -DKAFKA_BOOTSTRAP_SERVERS=${KAFKA_BOOTSTRAP_SERVERS} -DKF_TOPIC=${KF_TOPIC} -DDATA_DIR=${DATA_DIR} -jar ContentLoader.jar"]