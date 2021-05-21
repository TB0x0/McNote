import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            openFile.getExtensionFilters().add(extFilter);
            openFile.setTitle("Open Text File");
            File ofile = openFile.showOpenDialog(stage);

            if (ofile != null){
                textArea.setText(FileReader(ofile));
            }
        });
        saveDoc.setOnAction(e -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            FileChooser saveFile = new FileChooser();
            saveFile.getExtensionFilters().add(extFilter);
            saveFile.setTitle("Save Text File");
            File sfile = saveFile.showSaveDialog(stage);

            if(sfile != null){
                FileSaver(textArea.getText(), sfile);
            }
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

    private void FileSaver(String text, File sfile){
        try{
            FileWriter fw = new FileWriter(sfile);
            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException ioex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioex);
        }
    }

    private void FileReader(File file){

    }
}