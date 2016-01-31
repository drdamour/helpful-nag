package org.helpful.nag.naggers.tomcat;

import org.helpful.nag.naggers.Nag;
import org.helpful.nag.naggers.tomcat.nags.MismatchedVersionsNag;
import org.helpful.nag.naggers.tomcat.nags.MissingAppNag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class TomcatAppCompares {
    final private static Logger LOG = LoggerFactory.getLogger(TomcatAppCompares.class);

    final private TomcatApp candidate;
    final private TomcatApp authority;

    public TomcatAppCompares(TomcatApp candidate, TomcatApp authority) {
        Objects.requireNonNull(candidate);

        this.candidate = candidate;
        this.authority = authority;
    }

    public Stream<Nag> findNags(){
        LOG.info("finding naggers for candidate {} and authority {}", candidate, authority);

        //I wanted this to be a stream...but there's no yield
        //and no concat, so it gets really messy..java sucks
        //read http://stackoverflow.com/a/22741520/442773
        //maybe there's a better way?
        ArrayList<Nag> nags = new ArrayList<>();
        if(authority == null){
            nags.add(new MissingAppNag(candidate));
        } else {
            if (candidate.getVersion().equalsIgnoreCase(authority.getVersion())) {
                nags.add(new MismatchedVersionsNag(candidate, authority));
            }
        }
        return nags.stream();

    }


}
