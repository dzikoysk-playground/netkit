package net.dzikoysk.netkit;

import net.dzikoysk.netkit.js.JavascriptInterface;
import net.dzikoysk.netkit.js.JavascriptMethod;

import java.io.PrintStream;

@JavascriptInterface
public class NetkitConsole {

    private PrintStream output = System.out;

    @JavascriptMethod
    public void log(String message) {
        output.println(message);
    }

    @JavascriptMethod
    public void error(String message) {
        output.println(message);
    }

    protected void setOutput(PrintStream output) {
        this.output = output;
    }

}
