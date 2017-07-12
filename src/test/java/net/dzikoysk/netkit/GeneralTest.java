package net.dzikoysk.netkit;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GeneralTest extends Application {

    public static void main(String[] args) {
        Application.launch(GeneralTest.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        WebView view = new WebView();
        view.setPrefSize(1800, 1100);
        view.getEngine().load("https://html5test.netkit/");

        Group root = new Group(view);
        Scene scene = new Scene(root, 1800, 1100);

        stage.setTitle("Tests :: GeneralTest");
        stage.setScene(scene);
        stage.show();
    }

}
