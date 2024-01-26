package in.bhavanishankar.vertxzookeeperexample.consumer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer extends AbstractVerticle {

    @Autowired
    private Vertx vertx;

    @Override
    public void start() throws Exception {

        vertx.eventBus().consumer("hello-batman",message -> {
            log.info("registering event hello-batman in eventbus");

            var received = message.body().toString();

            log.info("Received message {} at consumer",received);

            String acknowledgement = "Hello robin..!! from Batsy";
            message.reply(acknowledgement);
        });

        log.info("Consumer is registered");
    }
}
