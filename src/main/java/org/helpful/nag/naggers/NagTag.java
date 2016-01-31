package org.helpful.nag.naggers;

import lombok.ToString;

@ToString
public class NagTag {
    public final static String TAG_NAME_CATEGORY = "category";
    public final static String TAG_NAME_APP = "app";
    public final static String TAG_NAME_HOST = "host";

    final private String name;
    final private String value;

    public NagTag(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
