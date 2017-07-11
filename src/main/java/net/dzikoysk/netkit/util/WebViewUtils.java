package net.dzikoysk.netkit.util;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WebViewUtils {

    public static void wait(WebView view) {
        Stage stage = new Stage();

        WebEngine engine = view.getEngine();
        engine.documentProperty().addListener((observable, o, n) -> stage.close());

        Scene scene = new Scene(view);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(1);
        stage.setHeight(1);
        stage.setScene(scene);

        stage.showAndWait();
        stage.close();
    }

}
