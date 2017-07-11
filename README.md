# Netkit [![Build Status](https://travis-ci.org/dzikoysk/Netkit.svg?branch=master)](https://travis-ci.org/dzikoysk/Netkit)
Tiny wrapper for JavaFX WebView component which improves bugs and adds a more intuitive api

#### Getting Started
```java
public class NetkitTest extends Application {

    public static void main(String[] args) {
        Application.launch(NetkitTest.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        NetkitFactory factory = new NetkitFactory();
        Netkit netkit = factory.createNetkit();
        netkit.loadURL("https://panda-lang.org");

        Group root = new Group(netkit.toParent());
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());

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
