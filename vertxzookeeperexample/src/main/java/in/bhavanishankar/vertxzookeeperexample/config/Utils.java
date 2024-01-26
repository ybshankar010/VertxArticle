package in.bhavanishankar.vertxzookeeperexample.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    public static String getServiceHost(String serviceHost,String servicePortStr) {
        if (serviceHost != null && servicePortStr != null) {
            int servicePort = Integer.parseInt(servicePortStr);
            var serviceUrl = "http://" + serviceHost + ":" + servicePort;
            log.info("Service URL: {}" ,serviceUrl);

            return serviceUrl;
        } else {
            log.error("Invalid parameters ");
            throw new RuntimeException("Invalid Environment Variables ");
        }
    }
}
