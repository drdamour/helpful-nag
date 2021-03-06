package org.helpful.nag.naggers.tomcat.dao;

import com.typesafe.config.Config;
import org.helpful.nag.naggers.tomcat.TomcatApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.stream.Stream;

/**
 * Uses the text/status service to retrieve app info
 * see https://tomcat.apache.org/tomcat-7.0-doc/manager-howto.html#List_Currently_Deployed_Applications
 */
public class Tomcat8TextDAO {
    private final static Logger LOG = LoggerFactory.getLogger(Tomcat8TextDAO.class);

    public static Stream<TomcatApp> findApps(Config config) throws IOException {
        String host = config.getString("host");

        String user = config.getString("user");
        String pass = config.getString("pass");

        java.net.URLConnection c = new URL(host + "/manager/text/list").openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888)));
        c.addRequestProperty("Authorization", "Basic " + Base64Utils.encodeToString((user + ":" + pass).getBytes()));

        BufferedReader in = new BufferedReader(
            new InputStreamReader(
                c.getInputStream()));

        return in.lines()
            .peek(l -> LOG.info("Line from {}: {}", host, l))
            //there's some header line
            .skip(1)
            .map(l -> fromStatusText(host, l))
            ;

    }

    static TomcatApp fromStatusText(String host, String text){
        String[] parts = text.split(":");

        String[] appParts = parts[3].split("##");
        String version = "unknown";
        if(appParts.length > 1){
            version = appParts[1];
        }

        return new TomcatApp(
            host,
            appParts[0],
            version,
            parts[1].equalsIgnoreCase("running"),
            parts[0],
            Integer.parseInt(parts[2])
        );
    }

}
