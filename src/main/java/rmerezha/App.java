package rmerezha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rmerezha.editor.*;
import rmerezha.shape.PointShape;

import java.util.ArrayList;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1200, 900);

        ShapeObjectsEditor editor = new ShapeObjectsEditor(canvas);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Об’єкти");
        MenuItem point = new MenuItem("Крапка");
        MenuItem line = new MenuItem("Лінія");
        MenuItem rect = new MenuItem("Прямокутник");
        MenuItem ellipse = new MenuItem("Еліпс");

        point.setOnAction(event -> {
            primaryStage.setTitle("Point");
            editor.setEditor(PointEditor.class);
        });

        line.setOnAction(event -> {
            primaryStage.setTitle("Line");
            editor.setEditor(LineEditor.class);
        });

        rect.setOnAction(event -> {
            primaryStage.setTitle("Rectangle");
            editor.setEditor(RectEditor.class);
        });

        ellipse.setOnAction(event -> {
            primaryStage.setTitle("Ellipse");
            editor.setEditor(EllipseEditor.class);
        });

        Button clear = new Button("clear");
        clear.setLayoutX(1100);
        clear.setLayoutY(800);
        clear.setOnAction(event -> {
            primaryStage.setTitle("Lab2");
            editor.getGc().clearRect(0, 0, 1200, 900);
            editor.getShapes().clear();
        });

        menu.getItems().addAll(point, line, rect, ellipse);
        menuBar.getMenus().add(menu);

        Pane root = new Pane();
        root.getChildren().addAll(canvas, menuBar, clear);

        Scene scene = new Scene(root, 1200, 900);
        primaryStage.setTitle("Lab2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
