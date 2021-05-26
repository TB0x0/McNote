import com.sun.deploy.util.StringUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
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

        Menu editMenu = new Menu("Edit");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        editMenu.getItems().addAll(cutItem, copyItem, pasteItem);

        Menu prefMenu = new Menu("Preferences");
        MenuItem fontItem = new MenuItem("Font");
        MenuItem textColor = new MenuItem("Text Color");
        Menu textSize = new Menu("Text Size");
        MenuItem subSize1 = new MenuItem("10px");
        MenuItem subSize2 = new MenuItem("12px");
        MenuItem subSize3 = new MenuItem("14px");
        MenuItem subSize4 = new MenuItem("16px");
        MenuItem subSize5 = new MenuItem("18px");
        textSize.getItems().addAll(subSize1,subSize2,subSize3,subSize4,subSize5);
        prefMenu.getItems().addAll(fontItem, textColor, textSize);

        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(editMenu);
        menuBar.getMenus().add(prefMenu);

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
                textArea.setText(readFile(ofile));
            }
        });
        saveDoc.setOnAction(e -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            FileChooser saveFile = new FileChooser();
            saveFile.getExtensionFilters().add(extFilter);
            saveFile.setTitle("Save Text File");
            File sfile = saveFile.showSaveDialog(stage);

            if(sfile != null){
                writeFile(textArea.getText(), sfile);
            }
        });
        exitDoc.setOnAction(e -> {
            stage.close();
        });

        cutItem.setOnAction(e-> {
            clipSelectedText(textArea);
            textArea.replaceSelection("");
        });
        copyItem.setOnAction(e-> {
            clipSelectedText(textArea);
        });
        pasteItem.setOnAction(e-> {
            Clipboard sysClipboard = Clipboard.getSystemClipboard();
            String clipText = sysClipboard.getString();
            int cursorPos = textArea.getCaretPosition();
            textArea.insertText(cursorPos, clipText);
        });

        subSize1.setOnAction(e-> {
            setFontSize(textArea, 10);
        });
        subSize2.setOnAction(e-> {
            setFontSize(textArea, 12);
        });
        subSize3.setOnAction(e-> {
            setFontSize(textArea, 14);
        });
        subSize4.setOnAction(e-> {
            setFontSize(textArea, 16);
        });
        subSize5.setOnAction(e-> {
            setFontSize(textArea, 18);
        });

        // Scene
        VBox vbox = new VBox(menuBar);
        vbox.getChildren().add(textArea);

        Scene scene = new Scene(vbox, 600, 600);
        //scene.getStylesheets().add("CustomStyles.css"); Add after v1.1
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void writeFile(String text, File sfile){
        try{
            FileWriter fw = new FileWriter(sfile);
            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException ioex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioex);
        }
    }

    private String readFile(File argFile){
        StringBuilder sBuffer = new StringBuilder();
        BufferedReader bReader = null;

        try{
            bReader = new BufferedReader(new FileReader(argFile));

            String text;
            while (( text = bReader.readLine()) != null){
                sBuffer.append(text);
                sBuffer.append("\n");
            }
        }catch (FileNotFoundException fnfex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, fnfex);
        }catch (IOException ioex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioex);
        }finally {
            try{
                bReader.close();
            }catch (IOException ioex){
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ioex);
            }
        }

        return sBuffer.toString();
    }

    private void clipSelectedText(TextArea argTextArea){
        Clipboard sysClipboard = Clipboard.getSystemClipboard();
        String selText = argTextArea.getSelectedText();
        ClipboardContent clip = new ClipboardContent();
        clip.putString(selText);
        sysClipboard.setContent(clip);
    }

    private void setFontSize(TextArea argTextArea, int fontSize){
        argTextArea.setFont(Font.font(fontSize));
    }

}