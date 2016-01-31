package org.helpful.nag.naggers;

import java.util.Set;

public interface Nag {

    String getName();
    String getBriefDescription();
    String getDetailedDescription();

    Set<NagTag> getTags();

}
