import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application  {
    public void start(Stage stage) throws Exception {
        stage.setTitle("McNote");

        // Menu
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newDoc = new MenuItem("New");
        MenuItem openDoc = new MenuItem("Open");
        MenuItem saveDoc = new MenuItem("Save");
        MenuItem exitDoc = new MenuItem("Exit");
        fileMenu.getItems().add(newDoc);
        fileMenu.getItems().add(openDoc);
        fileMenu.getItems().add(saveDoc);
        fileMenu.getItems().add(exitDoc);

        menuBar.getMenus().add(fileMenu);

        // Text Editor
        TextArea textArea = new TextArea();
        textArea.setPrefHeight(600);
        textArea.setPrefWidth(600);

        // Events
        newDoc.setOnAction(e -> {
            System.out.println("This will start a new document");
        });
        openDoc.setOnAction(e -> {
            System.out.println("This will open a document");
        });
        saveDoc.setOnAction(e -> {
            System.out.println("This will save a document");
        });
        exitDoc.setOnAction(e -> {
            stage.close();
        });

        // Scene
        VBox vbox = new VBox(menuBar);
        vbox.getChildren().add(textArea);

        Scene scene = new Scene(vbox, 600, 600);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}