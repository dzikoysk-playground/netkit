package net.dzikoysk.netkit;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NetkitTest extends Application {

    public static void main(String[] args) {
        Application.launch(NetkitTest.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        NetkitFactory factory = new NetkitFactory();
        Netkit netkit = factory.createSynchronizedNetkit();
        netkit.resize(900, 600);

        NetkitPage page = netkit.createPage();
        page.addLoadListener(loadedPage -> System.out.println("lol"));
        page.loadURL("file:///C:/Users/dzikoysk/Desktop/index.html");

        Group root = new Group(netkit.toParent());
        Scene scene = new Scene(root, root.getBoundsInParent().getWidth(), root.getBoundsInParent().getHeight());

        stage.setTitle("Tests :: Netkit");
        stage.setScene(scene);
        stage.show();
    }

}
