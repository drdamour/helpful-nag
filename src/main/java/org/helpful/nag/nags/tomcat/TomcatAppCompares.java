package org.helpful.nag.nags.tomcat;

import org.helpful.nag.nags.Nag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
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
        LOG.info("finding nags for candidate {} and authority {}", candidate, authority);

        //I wanted this to be a stream...but there's no yield
        //and no concat, so it gets really messy..java sucks
        //read http://stackoverflow.com/a/22741520/442773
        //maybe there's a better way?
        ArrayList<Nag> nags = new ArrayList<>();
        if(authority == null){
            nags.add(new MissingAppNag(candidate));
        }
        return nags.stream();

    }


}
