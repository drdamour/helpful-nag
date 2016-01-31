package org.helpful.nag.naggers.tomcat.nags;

import org.helpful.nag.naggers.NagBase;
import org.helpful.nag.naggers.Nag;
import org.helpful.nag.naggers.NagTag;
import org.helpful.nag.naggers.tomcat.TomcatApp;

import java.util.Objects;
import java.util.stream.Stream;

public class MismatchedVersionsNag extends NagBase implements Nag {

    final private TomcatApp candidate;
    final private TomcatApp authority;



    public MismatchedVersionsNag(TomcatApp candidate, TomcatApp authority){
        super(4);

        Objects.requireNonNull(candidate);
        Objects.requireNonNull(authority);

        this.candidate = candidate;
        this.authority = authority;


        this.tags.add(new NagTag(NagTag.TAG_NAME_CATEGORY, "tomcat"));
        this.tags.add(new NagTag(NagTag.TAG_NAME_APP, candidate.getName()));
        this.tags.add(new NagTag(NagTag.TAG_NAME_HOST, candidate.getHost()));
        this.tags.add(new NagTag(NagTag.TAG_NAME_HOST, authority.getHost()));

    }


    @Override
    public String getName() {
        return "Version Mismatch";
    }

    @Override
    public String getBriefDescription() {
        return candidate.getName() + " differs in versions";
    }

    @Override
    public String getDetailedDescription() {
        return "candidate " + candidate.getHost() + " had " + candidate.getVersion() + " but authority " + authority.getHost() + " had " + authority.getVersion();
    }


}
