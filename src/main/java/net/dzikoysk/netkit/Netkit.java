package net.dzikoysk.netkit;

import net.dzikoysk.netkit.util.WebViewUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.panda_lang.panda.utilities.commons.io.IOUtils;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class Netkit {

    private final WebView view;
    private final WebEngine engine;
    private final NetkitConsole console;
    private final NetkitGateway gateway;
    private final JSObject window;

    protected Netkit() {
        this.view = new WebView();
        this.engine = view.getEngine();
        this.console = new NetkitConsole();
        this.gateway = new NetkitGateway();
        this.window = executeScript("window");
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

        if (sync) {
            WebViewUtils.wait(view);
        }
    }

    public void loadURL(String url) {
        engine.executeScript("Netkit.loadURL('" + url + "');");
    }

    public void loadContent(String content) {
        engine.executeScript("Netkit.loadContent('" + content + "');");
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

    protected WebView getView() {
        return view;
    }

}
