package net.dzikoysk.netkit;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.dzikoysk.netkit.util.WebViewUtils;
import netscape.javascript.JSObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.panda_lang.panda.utilities.commons.io.IOUtils;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class Netkit {

    private final WebView view;
    private final WebEngine engine;
    private final NetkitConsole console;
    private final NetkitGateway gateway;
    private final JSObject window;
    private final Map<UUID, NetkitPage> pages;

    protected Netkit() {
        this.view = new WebView();
        this.engine = view.getEngine();
        this.console = new NetkitConsole();
        this.gateway = new NetkitGateway(this);
        this.window = executeScript("window");
        this.pages = new HashMap<>();
    }

    protected void initialize(boolean sync) {
        addJavascriptInterface("console", console);
        addJavascriptInterface("NetkitGateway", gateway);

        AtomicBoolean done = new AtomicBoolean(false);
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    done.set(true);
                }
            }
        });

        String netkitView = IOUtils.convertStreamToString(Netkit.class.getResourceAsStream("/netkit-view.html"));
        engine.loadContent(netkitView);

        if (!done.get() && sync) {
            WebViewUtils.wait(view);
        }
    }

    public NetkitPage loadURL(String url) {
        NetkitPage page = new NetkitPage(this, UUID.randomUUID());
        pages.put(page.getUUID(), page);

        engine.executeScript("Netkit.loadURL('" + url + "');");
        return page;
    }

    public NetkitPage loadContent(String content) {
        NetkitPage page = new NetkitPage(this, UUID.randomUUID());
        pages.put(page.getUUID(), page);

        engine.executeScript("Netkit.loadContent('" + StringEscapeUtils.escapeHtml(content) + "');");
        return page;
    }

    @SuppressWarnings({ "unchecked "})
    public <T> T executeScript(String script) {
        return (T) engine.executeScript(script);
    }

    public void resize(int width, int height) {
        view.setPrefSize(width, height);
        view.resize(width, height);
    }

    public void addJavascriptInterface(String interfaceName, Object gateway) {
        window.setMember(interfaceName, gateway);
    }

    public void setOutput(PrintStream output) {
        console.setOutput(output);
    }

    public Parent toParent() {
        return view;
    }

    protected Map<UUID, NetkitPage> getPages() {
        return pages;
    }

    protected WebView getView() {
        return view;
    }

}
