package in.bhavanishankar.vertxzookeeperexample.config;

import in.bhavanishankar.vertxzookeeperexample.consumer.EventConsumer;
import in.bhavanishankar.vertxzookeeperexample.producer.EventProducer;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class VertxDeploymentConfiguration {

    @Autowired
    private Vertx vertx;

    @Autowired
    private EventConsumer consumer;

    @Autowired
    private EventProducer producer;

    @Bean
    @ConditionalOnProperty(prefix = "deployment", name = "type", havingValue = "producer")
    public CommandLineRunner deployProducer() {
        log.info("Deploying producer...!!");
        return args -> {
            deploy(producer);
        };
    }

    @Bean
    @ConditionalOnProperty(prefix = "deployment", name = "type", havingValue = "consumer")
    public CommandLineRunner deployConsumer() {
        log.info("Deploying consumer...!!");
        return args -> {
            deploy(consumer);
        };
    }

    private void deploy(Verticle verticle) {
        var options = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle(verticle, options,ar -> {
            if (ar.succeeded()) {
                log.info("Deployed Verticle: {}" , verticle.getClass().getSimpleName());
            } else {
                log.error("Failed to deploy Verticle: {} ", verticle.getClass().getSimpleName(),ar.cause());
            }
        });
    }
}
