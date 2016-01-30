package org.helpful.nag.nags.tomcat;

public class TomcatApp {

    private final String appName;
    private final String appVersion;
    private final boolean running;
    private final String context;
    private final int sessions;

    public TomcatApp(String appName, String appVersion, boolean running, String context, int sessions) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.running = running;
        this.context = context;
        this.sessions = sessions;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
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
}
