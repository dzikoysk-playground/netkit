package net.dzikoysk.netkit;

import net.dzikoysk.netkit.listener.LoadListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class NetkitPage {

    private final Netkit netkit;
    private final UUID uuid;
    private final Collection<LoadListener> loadListeners;

    public NetkitPage(Netkit netkit, UUID uuid) {
        this.netkit = netkit;
        this.uuid = uuid;
        this.loadListeners = new ArrayList<>(1);
    }

    public void addLoadListener(LoadListener listener) {
        this.loadListeners.add(listener);
    }

    protected Collection<LoadListener> getLoadListeners() {
        return loadListeners;
    }

    public UUID getUUID() {
        return uuid;
    }

}
