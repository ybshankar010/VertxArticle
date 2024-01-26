FROM eclipse-temurin:17.0.8_7-jre

WORKDIR /app

COPY target/vertxzookeeperexample-0.0.1-SNAPSHOT.jar /app/vertxzookeeperexample.jar

# Command to run your microservice (modify as needed)
#CMD ["java", "-jar", "vertxzookeeperexample.jar"]

EXPOSE 8080


ENTRYPOINT java -XX:MaxRAMPercentage=75 -DDEPLOYMENT_TYPE=$DEPLOYMENT_TYPE -jar vertxzookeeperexample.jar