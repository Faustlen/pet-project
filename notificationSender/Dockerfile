FROM openjdk:21-jdk-slim
WORKDIR /app
COPY /build/libs/notificationSender-0.0.1-SNAPSHOT.jar /app/NotificationSender.jar
EXPOSE 8081
ENTRYPOINT ["sh", "-c", "java -DMAIL_USERNAME=${MAIL_USERNAME} -DMAIL_PASSWORD=${MAIL_PASSWORD} -DKAFKA_BOOTSTRAP_SERVERS=${KAFKA_BOOTSTRAP_SERVERS} -DKF_TOPIC=${KF_TOPIC} -jar NotificationSender.jar"]