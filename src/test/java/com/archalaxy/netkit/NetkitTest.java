package com.archalaxy.netkit;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.dzikoysk.netkit.Netkit;
import net.dzikoysk.netkit.NetkitFactory;

public class NetkitTest extends Application {

    public static void main(String[] args) {
        Application.launch(NetkitTest.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        NetkitFactory factory = new NetkitFactory();
        Netkit netkit = factory.createNetkit();
        netkit.loadContent("<p>xxx</p><script>console.log(\"contes\");</script>");
        netkit.loadURL("http://onet.pl");
        netkit.loadContent("<p>yyyy</p><script>console.log(\"tescon\");</script>");

        Group root = new Group(netkit.toParent());
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());

        stage.setTitle("Tests :: Netkit");
        stage.setScene(scene);
        stage.show();
    }

}
