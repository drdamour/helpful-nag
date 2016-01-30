package org.helpful.nag.nags.tomcat;

import org.helpful.nag.nags.Nag;
import org.helpful.nag.nags.tomcat.dao.Tomcat8TextDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AppCompareNagger {

    static Set<String> ignored = new HashSet<>(Arrays.<String>asList("manager", "ROOT"));

    public static Stream<Nag> findNags() throws IOException {
        List<TomcatApp> authorityApps = Tomcat8TextDAO.findApps("http://catalog1.body.prod:8080")
            .collect(Collectors.toList());


        return Tomcat8TextDAO.findApps("http://atg3-api2.dev:8080")
            //Skip ignored
            .filter(candidate -> !ignored.contains(candidate.getName()))
            //Matche candidates up with authorities
            .map(candidate -> authorityApps.stream()
                .filter(authority -> authority.getName().equals(candidate.getName()))
                .findFirst()
                .map(authority -> new TomcatAppCompares(candidate, authority))
                .orElseGet(() -> new TomcatAppCompares(candidate, null))
            )
            //go get them nags!
            .flatMap(TomcatAppCompares::findNags)
            ;

    }

}
