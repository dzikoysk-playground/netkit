package net.dzikoysk.netkit;

import javafx.scene.web.WebView;

public class NetkitUnsafe {

    public static WebView extractWebView(Netkit netkit) {
        return netkit.getView();
    }

}
