package in.bhavanishankar.vertxzookeeperexample.config;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {

    @Value("${zookeeper.hosts}")
    private String zookeeperHosts;

    @Bean
    public Vertx vertx() {
        JsonObject zkConfig = new JsonObject();
        zkConfig.put("zookeeperHosts", zookeeperHosts);
        zkConfig.put("rootPath", "in.bhavanishankar");
        zkConfig.put("retry", new JsonObject()
            .put("initialSleepTime", 3000)
            .put("maxTimes", 3)
            .put("policy","exponential_backoff"));

        var options = new VertxOptions()
            .setClusterManager(new ZookeeperClusterManager(zkConfig));

//        var vertx = new AtomicReference<Vertx>();
//        Vertx.clusteredVertx(options).onComplete(res -> {
//            if (res.succeeded()) {
//                log.info("Vertx initialization successful");
//                vertx.set(res.result());
//            } else {
//                log.error("Vertx failed to initialize {}",res.cause().getMessage());
//                vertx.set(null);
//            }
//        });

        return Vertx.vertx(options);
    }
}
