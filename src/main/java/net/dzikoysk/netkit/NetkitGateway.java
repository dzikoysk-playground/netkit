package net.dzikoysk.netkit;

import net.dzikoysk.netkit.js.JavascriptInterface;
import net.dzikoysk.netkit.js.JavascriptMethod;

@JavascriptInterface
public class NetkitGateway {

    private final Netkit netkit;

    public NetkitGateway(Netkit netkit) {
        this.netkit = netkit;
    }

    @JavascriptMethod
    public void callLoadListeners(String uuid) {

    }

}
