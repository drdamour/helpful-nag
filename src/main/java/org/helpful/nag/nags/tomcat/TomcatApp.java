package org.helpful.nag.nags.tomcat;

import lombok.ToString;

import java.util.Objects;

@ToString
public class TomcatApp {

    private final String host;
    private final String name;
    private final String appVersion;
    private final boolean running;
    private final String context;
    private final int sessions;

    public TomcatApp(String host, String name, String version, boolean running, String context, int sessions) {
        Objects.requireNonNull(host);
        Objects.requireNonNull(name);
        Objects.requireNonNull(version);
        Objects.requireNonNull(context);

        this.host = host;
        this.name = name;
        this.appVersion = version;
        this.running = running;
        this.context = context;
        this.sessions = sessions;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return appVersion;
    }

    public boolean isRunning() {
        return running;
    }

    public String getContext() {
        return context;
    }

    public int getSessions() {
        return sessions;
    }

    public String getHost() {
        return host;
    }
}
