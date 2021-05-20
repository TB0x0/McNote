import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.application.Application;
import java.awt.Color;

import java.awt.*;

public class Window{
    public void start(Stage stage){
        Font font = Font.font("Times New Roman", 12);
        TextArea area = new TextArea();
        area.setPrefHeight(400);
        area.setPrefWidth(400);

        HBox hbox = new HBox(area);
        Scene scene = new Scene(hbox, 400, 400);
        stage.setTitle("McNote");
        stage.setScene(scene);
        stage.show();
    }
}
