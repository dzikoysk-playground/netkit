package net.dzikoysk.netkit;

import net.dzikoysk.netkit.js.JavascriptInterface;
import net.dzikoysk.netkit.js.JavascriptMethod;
import net.dzikoysk.netkit.listener.LoadListener;

@JavascriptInterface
public class NetkitGateway {

    private final Netkit netkit;

    public NetkitGateway(Netkit netkit) {
        this.netkit = netkit;
    }

    @JavascriptMethod
    public void callLoadListeners(long id) {
        NetkitPage page = netkit.getPages().get(id);

        if (page == null) {
            throw new RuntimeException("Page (id: " + id + ") doesn't exist");
        }

        for (LoadListener listener : page.getLoadListeners()) {
            try {
                listener.onLoad(page);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
