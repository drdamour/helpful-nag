package org.helpful.nag.naggers.tomcat.nags;

import org.helpful.nag.naggers.Nag;
import org.helpful.nag.naggers.NagBase;
import org.helpful.nag.naggers.NagTag;
import org.helpful.nag.naggers.tomcat.TomcatApp;

import java.util.Objects;

public class MissingAppNag extends NagBase implements Nag {

    final private TomcatApp app;


    public MissingAppNag(TomcatApp app){
        super(3);
        Objects.requireNonNull(app);

        this.app = app;

        this.tags.add(new NagTag(NagTag.TAG_NAME_CATEGORY, "tomcat"));
        this.tags.add(new NagTag(NagTag.TAG_NAME_APP, app.getName()));
        this.tags.add(new NagTag(NagTag.TAG_NAME_HOST, app.getHost()));

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
