package rmerezha;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rmerezha.shape.*;
import rmerezha.table.MyTable;

import java.util.concurrent.atomic.AtomicBoolean;

public class App extends Application {

    public static final PointShape POINT_SHAPE = new PointShape() {};
    public static final LineShape LINE_SHAPE = new LineShape() {};
    public static final RectShape RECT_SHAPE = new RectShape() {};
    public static final EllipseShape ELLIPSE_SHAPE = new EllipseShape() {};
    public static final LineOOShape LINE_OO_SHAPE = new LineOOShape() {};
    public static final CubeShape CUBE_SHAPE = new CubeShape() {};
    public static final MyEditor editor = MyEditor.getInstance();
    public static final MyTable<ShapeStorage> myTable = MyTable.createWindow();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Canvas canvas = new Canvas(1200, 900);

        editor.setCanvas(canvas);

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

        Image lineOOShapeImage = new Image("line2.png");
        ImageView lineOOShapeImageView = new ImageView(lineOOShapeImage);
        lineOOShapeImageView.setFitWidth(20);
        lineOOShapeImageView.setFitHeight(20);

        Image cubeImage = new Image("cube.png");
        ImageView cubeView = new ImageView(cubeImage);
        cubeView.setFitWidth(20);
        cubeView.setFitHeight(20);

        Image trashImage = new Image("trash.png");
        ImageView trashImageView = new ImageView(trashImage);
        trashImageView.setFitWidth(20);
        trashImageView.setFitHeight(20);


        Button point = new Button("", pointImageView);
        Button line = new Button("", lineImageView);
        Button rect = new Button("", rectImageView);
        Button ellipse = new Button("", ellipseImageView);
        Button lineOOShape = new Button("", lineOOShapeImageView);
        Button cube = new Button("", cubeView);
        Button clear = new Button("", trashImageView);
        Menu mock1 = new Menu("Файл");
        Menu mock2 = new Menu("Довiдка");
        Menu tableMenu = new Menu("Таблиця");

        myTable.addColumn("Назва", cellData ->
                new SimpleStringProperty(cellData.getValue().getShape().getName())
        );
        myTable.addColumn("Початкові координати", cellData ->
                new SimpleStringProperty(cellData.getValue().getP1().toString())
        );
        myTable.addColumn("Кінцеві координати", cellData ->
                new SimpleStringProperty(cellData.getValue().getP2().toString()));

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            myTable.addEntity(editor.getCurrentShape());
        });

        point.setOnAction(event -> {
            editor.start(POINT_SHAPE, menu);
        });

        line.setOnAction(event -> {
            editor.start(LINE_SHAPE, menu);
        });

        rect.setOnAction(event -> {
            editor.start(RECT_SHAPE, menu);
        });

        ellipse.setOnAction(event -> {
            editor.start(ELLIPSE_SHAPE, menu);
        });

        lineOOShape.setOnAction(event -> {
            editor.start(LINE_OO_SHAPE, menu);
        });

        cube.setOnAction(event -> {
            editor.start(CUBE_SHAPE, menu);
        });

        clear.setLayoutX(1100);
        clear.setLayoutY(800);
        clear.setOnAction(event -> {
            editor.getGc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            editor.getShapes().clear();
            myTable.clear();
        });

        point.setTooltip(new Tooltip(POINT_SHAPE.getName()));
        line.setTooltip(new Tooltip(LINE_SHAPE.getName()));
        rect.setTooltip(new Tooltip(RECT_SHAPE.getName()));
        ellipse.setTooltip(new Tooltip(ELLIPSE_SHAPE.getName()));
        lineOOShape.setTooltip(new Tooltip(LINE_OO_SHAPE.getName()));
        cube.setTooltip(new Tooltip(CUBE_SHAPE.getName()));
        clear.setTooltip(new Tooltip("Очистити"));

        ToolBar toolBar = ToolbarBuilder.builder()
                .setNode(point)
                .setNode(line)
                .setNode(rect)
                .setNode(ellipse)
                .setNode(lineOOShape)
                .setNode(cube)
                .setNode(clear)
                .setNode(new Separator())
                .build();


        CheckMenuItem pointMenu = new CheckMenuItem(POINT_SHAPE.getName());
        CheckMenuItem lineMenu = new CheckMenuItem(LINE_SHAPE.getName());
        CheckMenuItem rectMenu = new CheckMenuItem(RECT_SHAPE.getName());
        CheckMenuItem ellipseMenu = new CheckMenuItem(ELLIPSE_SHAPE.getName());
        CheckMenuItem lineOOShapeMenu = new CheckMenuItem(LINE_OO_SHAPE.getName());
        CheckMenuItem cubeMenu = new CheckMenuItem(CUBE_SHAPE.getName());

        MenuItem showTable = new MenuItem("Вiдкрити таблицю");
        MenuItem closeTable = new MenuItem("Закрити таблицю");

        AtomicBoolean isOn = new AtomicBoolean(false);
        showTable.setOnAction(event -> {
            if (!isOn.get()) {
                myTable.show();
                isOn.set(true);
            }
        });
        closeTable.setOnAction(event -> {
            if (isOn.get()) {
                myTable.close();
                isOn.set(false);
            }
        });
        menu.getItems().addAll(pointMenu, lineMenu, rectMenu, ellipseMenu, lineOOShapeMenu, cubeMenu);
        tableMenu.getItems().addAll(showTable, closeTable);
        menuBar.getMenus().addAll(mock1, menu, mock2, tableMenu);




        // ----
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

        primaryStage.setOnCloseRequest(e -> {
            if (isOn.get()) {
                myTable.close();
            }
        });

        Scene scene = new Scene(root, 1200, 900);
        primaryStage.setTitle("Lab5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
