package in.bhavanishankar.vertxzookeeperexample.producer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventProducer extends AbstractVerticle {

    @Autowired
    private Vertx vertx;

    @Override
    public void start() throws Exception {

        vertx.setPeriodic(1000, count -> {
            log.info("Starting consumer thread...!!!");
            String message = "Hello batsy";

            vertx.eventBus().request("hello-batman", message, reply -> {
                log.info("Sending message to hello.batman handler..!!");

                if (reply.succeeded()) {
                    var response = reply.result().body().toString();
                    log.info("Message successfully sent on the address {}",response);
                } else {
                    log.error("Message push failed {}",reply.cause().getMessage());
                }
            });
        });
    }
}
