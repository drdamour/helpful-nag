package org.helpful.nag.naggers.tomcat;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.helpful.nag.naggers.Nag;
import org.helpful.nag.naggers.Nagger;
import org.helpful.nag.naggers.tomcat.dao.Tomcat8TextDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AppCompareNagger implements Nagger {


    public Stream<Nag> findNags() throws IOException {
        Config config = ConfigFactory.load();

        Config authorityConfig = config.getConfigList("tomcat.AppCompareNagger.authorities").get(0);


        List<TomcatApp> authorityApps = Tomcat8TextDAO.findApps(authorityConfig)
            .collect(Collectors.toList());


        List<String> ignored = config.getStringList("tomcat.AppCompareNagger.ignoredApps");
        Config candidateConfig = config.getConfigList("tomcat.AppCompareNagger.candidates").get(0);

        return Tomcat8TextDAO.findApps(candidateConfig)
            //Skip ignored
            .filter(candidate -> !ignored.contains(candidate.getName()))
            //Matche candidates up with authorities
            .map(candidate -> authorityApps.stream()
                .filter(authority -> authority.getName().equals(candidate.getName()))
                .findFirst()
                .map(authority -> new TomcatAppCompares(candidate, authority))
                .orElseGet(() -> new TomcatAppCompares(candidate, null))
            )
            //go get them naggers!
            .flatMap(TomcatAppCompares::findNags)
            ;

    }

}
