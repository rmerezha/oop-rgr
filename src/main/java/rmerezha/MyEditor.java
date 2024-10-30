package rmerezha;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import rmerezha.shape.Shape;
import rmerezha.shape.ShapeStorage;
import rmerezha.util.Point;
import rmerezha.util.WrappedArray;

import java.util.HashMap;

public class MyEditor {
    private ShapeStorage shapeStorage;
    private Canvas canvas;
    private final GraphicsContext gc;
    private final WrappedArray<ShapeStorage> shapes = new WrappedArray<>(100+14);
    private final HashMap<EventType<MouseEvent>, EventHandler<MouseEvent>> handlers = new HashMap<>();

    private static MyEditor myEditor;

    public static MyEditor getInstance(Canvas canvas) {
        if (myEditor == null) {
            myEditor = new MyEditor(canvas);
        }
        return myEditor;
    }

    private MyEditor(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(5);
    }


    public void start(Shape shape, Menu menu) {
        this.shapeStorage = new ShapeStorage(shape, null, null);
        removeHandlers();
        handlers.clear();
        addHandlers();
        this.onInitMenuPopup(menu);
    }

    private void addHandlers() {
        handlers.put(MouseEvent.MOUSE_PRESSED, this::onLBdown);
        handlers.put(MouseEvent.MOUSE_DRAGGED, this::onMouseMove);
        handlers.put(MouseEvent.MOUSE_RELEASED, this::onLBup);

        for (var entry : handlers.entrySet()) {
            canvas.addEventHandler(entry.getKey(), entry.getValue());
        }
    }

    private void removeHandlers() {
        for (var entry : handlers.entrySet()) {
            canvas.removeEventHandler(entry.getKey(), entry.getValue());
        }
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public WrappedArray<ShapeStorage> getShapes() {
        return shapes;
    }

    public void onLBdown(MouseEvent event) {
        shapeStorage.setVisible(false);
        shapeStorage.setP1(new Point(event.getX(), event.getY()));
    }

    public void onLBup(MouseEvent event) {
        shapeStorage.setVisible(true);
        shapeStorage.setP2(new Point(event.getX(), event.getY()));
        shapes.add(shapeStorage.clone());
        onPaint();
    }


    public void onMouseMove(MouseEvent event) {
        onPaint();

        shapeStorage.setP2(new Point(event.getX(), event.getY()));

        gc.setLineDashes(50, 25);
        shapeStorage.show(gc);
        gc.setLineDashes(1);
        gc.setStroke(Color.BLACK);

    }

    protected void onPaint() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).show(gc);
        }
    }

    public void onInitMenuPopup(Menu menu) {
        for (MenuItem i : menu.getItems()) {
            var cmi = (CheckMenuItem) i;
            cmi.setSelected(false);
            if (cmi.getText().equals(shapeStorage.getShape().getName())) {
                cmi.setSelected(true);
            }
        }
    }

    public ShapeStorage getShapeStorage() {
        return shapeStorage;
    }

    public void setShapeStorage(ShapeStorage shapeStorage) {
        this.shapeStorage = shapeStorage;
    }

}
