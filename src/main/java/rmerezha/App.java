package rmerezha;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import rmerezha.editor.*;

public class App extends Application {

    public static final String POINT = "Крапка";
    public static final String LINE = "Лінія";
    public static final String RECT = "Прямокутник";
    public static final String ELLIPSE = "Еліпс";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Canvas canvas = new Canvas(1200, 900);

        ShapeObjectsEditor editor = new ShapeObjectsEditor(canvas);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Об’єкти");

        Image pointImage = new Image("dot.png");
        ImageView pointImageView = new ImageView(pointImage);
        pointImageView.setFitWidth(20);
        pointImageView.setFitHeight(20);

        Image lineImage = new Image("line.png");
        ImageView lineImageView = new ImageView(lineImage);
        lineImageView.setFitWidth(20);
        lineImageView.setFitHeight(20);

        Image rectImage = new Image("rect.png");
        ImageView rectImageView = new ImageView(rectImage);
        rectImageView.setFitWidth(20);
        rectImageView.setFitHeight(20);

        Image ellipseImage = new Image("ellipse.png");
        ImageView ellipseImageView = new ImageView(ellipseImage);
        ellipseImageView.setFitWidth(20);
        ellipseImageView.setFitHeight(20);

        Image trashImage = new Image("trash.png");
        ImageView trashImageView = new ImageView(trashImage);
        trashImageView.setFitWidth(20);
        trashImageView.setFitHeight(20);


        Button point = new Button("", pointImageView);
        Button line = new Button("", lineImageView);
        Button rect = new Button("", rectImageView);
        Button ellipse = new Button("", ellipseImageView);
        Button clear = new Button("", trashImageView);
        Menu mock1 = new Menu("Файл");
        Menu mock2 = new Menu("Довiдка");
        Label label = new Label("");

        point.setOnAction(event -> {
            editor.setEditor(PointEditor.class, menu);
        });

        line.setOnAction(event -> {
            editor.setEditor(LineEditor.class, menu);
        });

        rect.setOnAction(event -> {
            editor.setEditor(RectEditor.class, menu);
        });

        ellipse.setOnAction(event -> {
            editor.setEditor(EllipseEditor.class, menu);
        });

        clear.setLayoutX(1100);
        clear.setLayoutY(800);
        clear.setOnAction(event -> {
            editor.getGc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            editor.getShapes().clear();
        });

        point.setTooltip(new Tooltip(POINT));
        line.setTooltip(new Tooltip(LINE));
        rect.setTooltip(new Tooltip(RECT));
        ellipse.setTooltip(new Tooltip(ELLIPSE));
        clear.setTooltip(new Tooltip("Очистити"));

        ToolBar toolBar = ToolbarBuilder.builder()
                .setNode(point)
                .setNode(line)
                .setNode(rect)
                .setNode(ellipse)
                .setNode(clear)
                .setNode(new Separator())
                .build();


        CheckMenuItem pointMenu = new CheckMenuItem("Крапка");
        CheckMenuItem lineMenu = new CheckMenuItem("Лінія");
        CheckMenuItem rectMenu = new CheckMenuItem("Прямокутник");
        CheckMenuItem ellipseMenu = new CheckMenuItem("Еліпс");
        menu.getItems().addAll(pointMenu, lineMenu, rectMenu, ellipseMenu);
        menuBar.getMenus().addAll(mock1, menu, mock2);

        BorderPane root = new BorderPane();
        VBox topContainer = new VBox(0);
        topContainer.getChildren().addAll(menuBar, toolBar);

        topContainer.prefWidthProperty().bind(root.widthProperty());


        root.setTop(topContainer);
        root.setCenter(canvas);


        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth(newVal.doubleValue());
        });

        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            var t = newVal.doubleValue() - toolBar.getHeight();
            canvas.setHeight(t);

            toolBar.setMinHeight(newVal.doubleValue() / 24);
            toolBar.setMaxHeight(newVal.doubleValue() / 24);

            pointImageView.setFitHeight(toolBar.getMaxHeight() / 2);
            pointImageView.setFitWidth(toolBar.getMaxHeight() / 2);

            lineImageView.setFitHeight(toolBar.getMaxHeight() / 2);
            lineImageView.setFitWidth(toolBar.getMaxHeight() / 2);

            rectImageView.setFitHeight(toolBar.getMaxHeight() / 2);
            rectImageView.setFitWidth(toolBar.getMaxHeight() / 2);

            ellipseImageView.setFitHeight(toolBar.getMaxHeight() / 2);
            ellipseImageView.setFitWidth(toolBar.getMaxHeight() / 2);

            trashImageView.setFitHeight(toolBar.getMaxHeight() / 2);
            trashImageView.setFitWidth(toolBar.getMaxHeight() / 2);
        });



        Scene scene = new Scene(root, 1200, 900);
        primaryStage.setTitle("Lab2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
