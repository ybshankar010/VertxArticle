package in.bhavanishankar.vertxzookeeperexample;

import in.bhavanishankar.vertxzookeeperexample.config.Utils;
import in.bhavanishankar.vertxzookeeperexample.config.ZooKeeperServiceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class VertxZookeeperExampleApplication {

	@Value("${zookeeper.namespace}")
	private static String serviceName;

	@Value("${service.host}")
	private static String host;

	@Value("${service.port}")
	private static String port;



	public static void main(String[] args) {
		var context = SpringApplication.run(VertxZookeeperExampleApplication.class, args);

		log.info("Adding service registry entries");
		ZooKeeperServiceRegistry registry = context.getBean(ZooKeeperServiceRegistry.class);
		registry.registerService(serviceName, Utils.getServiceHost(host,port));
	}
}
