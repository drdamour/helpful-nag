package org.helpful.nag.nags.tomcat;

import org.helpful.nag.nags.Nag;

import java.util.Objects;

public class MissingAppNag implements Nag {

    final private TomcatApp app;

    public MissingAppNag(TomcatApp app){
        Objects.requireNonNull(app);

        this.app = app;

    }


    @Override
    public String getName() {
        return "No Authoritative App";
    }

    @Override
    public String getBriefDescription() {
        return "No authoritative app found for " + app.getName();
    }

    @Override
    public String getDetailedDescription() {
        return app.getHost() + " had " + app.getName() + " version " + app.getVersion() + " but no app by that name found in the authority";
    }
}
