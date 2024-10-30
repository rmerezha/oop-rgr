package rmerezha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rmerezha.shape.EllipseShape;
import rmerezha.shape.LineShape;
import rmerezha.shape.PointShape;
import rmerezha.shape.RectShape;

public class App extends Application {

    public static final PointShape POINT_SHAPE = new PointShape();
    public static final LineShape LINE_SHAPE = new LineShape();
    public static final RectShape RECT_SHAPE = new RectShape();
    public static final EllipseShape ELLIPSE_SHAPE = new EllipseShape();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Canvas canvas = new Canvas(1200, 900);

        MyEditor editor = new MyEditor(canvas);

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
            editor.start(new PointShape(), menu);
        });

        line.setOnAction(event -> {
            editor.start(new LineShape(), menu);
        });

        rect.setOnAction(event -> {
            editor.start(new RectShape(), menu);
        });

        ellipse.setOnAction(event -> {
            editor.start(new EllipseShape(), menu);
        });

        clear.setLayoutX(1100);
        clear.setLayoutY(800);
        clear.setOnAction(event -> {
            editor.getGc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            editor.getShapes().clear();
        });

        point.setTooltip(new Tooltip(POINT_SHAPE.getName()));
        line.setTooltip(new Tooltip(LINE_SHAPE.getName()));
        rect.setTooltip(new Tooltip(RECT_SHAPE.getName()));
        ellipse.setTooltip(new Tooltip(ELLIPSE_SHAPE.getName()));
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
