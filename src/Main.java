import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.PrintStream;


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
            textArea.setText("");
        });
        openDoc.setOnAction(e -> {
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open Text File");
            openFile.showOpenDialog(stage);
        });
        saveDoc.setOnAction(e -> {
            String oString = textArea.getText();
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Save Text File");
            openFile.showOpenDialog(stage);
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