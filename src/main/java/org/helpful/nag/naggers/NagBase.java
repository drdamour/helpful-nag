package org.helpful.nag.naggers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class NagBase implements Nag {

    final protected Set<NagTag> tags;

    public NagBase(int tagCount){
        this.tags = new HashSet<>(tagCount + 1);
        this.tags.add(new NagTag("class", this.getClass().getCanonicalName()));
    }

    @Override
    public Set<NagTag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}
