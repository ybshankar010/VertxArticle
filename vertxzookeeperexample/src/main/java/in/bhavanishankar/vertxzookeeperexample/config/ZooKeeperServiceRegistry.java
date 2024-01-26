package in.bhavanishankar.vertxzookeeperexample.config;

import java.io.IOException;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ZooKeeperServiceRegistry {

    @Value("${zookeeper.hosts}")
    private String zookeeperHosts;

    @Value("${zookeeper.namespace}")
    private String zookeeperNamespace;

    private ZooKeeper zooKeeper;

    public ZooKeeperServiceRegistry() {
    }

    @PostConstruct
    public void init() throws IOException {
        this.zooKeeper = new ZooKeeper(zookeeperHosts, 5000, null);
    }

    public void registerService(String serviceName, String address) {
        // Register the service address in ZooKeeper under the specified namespace
        String servicePath = zookeeperNamespace + "/" + serviceName;
        try {
            if (zooKeeper.exists(servicePath, false) == null) {
                zooKeeper.create(servicePath, address.getBytes(),
                                 ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            log.error("Error registering service",e);
        }
    }

    private String getServiceAddress(String serviceName) {
        // Retrieve the service address from ZooKeeper
        String servicePath = zookeeperNamespace + "/" + serviceName;
        try {
            byte[] data = zooKeeper.getData(servicePath, false, null);
            return new String(data);
        } catch (Exception e) {
            log.error("Error generating service address ",e);
            return null;
        }
    }
}

