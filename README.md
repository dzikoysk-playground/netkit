# Netkit [![Build Status](https://travis-ci.org/dzikoysk/Netkit.svg?branch=master)](https://travis-ci.org/dzikoysk/Netkit)
[**Abandoned**] Tiny wrapper for JavaFX WebView component which improves bugs and adds a more intuitive api

#### Getting Started
```java
public class NetkitTest extends Application {

    public static void main(String[] args) {
        Application.launch(NetkitTest.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        NetkitFactory factory = new NetkitFactory();
        Netkit netkit = factory.createSynchronizedNetkit();
        netkit.loadURL("https://dzikoysk.net");
        netkit.resize(900, 600);

        Group root = new Group(netkit.toParent());
        Scene scene = new Scene(root, root.getBoundsInParent().getWidth(), root.getBoundsInParent().getHeight());

        stage.setTitle("Tests :: Netkit");
        stage.setScene(scene);
        stage.show();
    }

}
```

#### Maven
```xml
<dependencies>
    <dependency>
        <groupId>net.dzikoysk</groupId>
        <artifactId>netkit</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>

<repositories>
    <repository>
        <id>panda-repo</id>
        <url>https://repo.panda-lang.org/</url>
    </repository>
</repositories>
```
