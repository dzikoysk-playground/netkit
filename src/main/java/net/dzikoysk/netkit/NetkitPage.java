package net.dzikoysk.netkit;

import net.dzikoysk.netkit.listener.LoadListener;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

public class NetkitPage {

    private static final AtomicLong idAssigner = new AtomicLong();

    private final long id;
    private final Netkit netkit;
    private final Collection<LoadListener> loadListeners;

    public NetkitPage(Netkit netkit) {
        this.id = idAssigner.getAndIncrement();
        this.netkit = netkit;
        this.loadListeners = new ArrayList<>(1);
    }

    public void loadURL(String url) {
        netkit.executeScript("Netkit.loadURL(" + id + ",'" + url + "');");
    }

    public void loadContent(String content) {
        netkit.executeScript("Netkit.loadContent(" + id + ", '" + StringEscapeUtils.escapeHtml(content) + "');");
    }

    public void addJavascriptInterface(String interfaceName, Object gateway) {
        String temp = getId() + "-" + interfaceName;
        netkit.addJavascriptInterface(temp, gateway);
        netkit.executeScript("Netkit.setPageJavascriptInterface(" + temp + ", '" + interfaceName + "');");
    }

    public void executeScript(String script) {
        netkit.executeScript(script);
    }

    public void addLoadListener(LoadListener listener) {
        this.loadListeners.add(listener);
    }

    protected Collection<LoadListener> getLoadListeners() {
        return loadListeners;
    }

    public long getId() {
        return id;
    }

}
