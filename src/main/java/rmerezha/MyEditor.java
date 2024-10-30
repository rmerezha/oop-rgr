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
import rmerezha.util.Point;
import rmerezha.util.WrappedArray;

import java.util.HashMap;

public class MyEditor {
    private Shape shape;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final WrappedArray<Shape> shapes = new WrappedArray<>(100+14);
    private final HashMap<EventType<MouseEvent>, EventHandler<MouseEvent>> handlers = new HashMap<>();

    public MyEditor(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(5);
    }


    public void start(Shape shape, Menu menu) {
        this.shape = shape;
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

    public WrappedArray<Shape> getShapes() {
        return shapes;
    }

    public void onLBdown(MouseEvent event) {
        shape.withFill(false);
        shape.setP1(new Point(event.getX(), event.getY()));
    }

    public void onLBup(MouseEvent event) {
        shape.withFill(true);
        shape.setP2(new Point(event.getX(), event.getY()));
        shapes.add(shape.clone());
        onPaint();
    }


    public void onMouseMove(MouseEvent event) {
        onPaint();

        shape.setP2(new Point(event.getX(), event.getY()));

        gc.setLineDashes(50, 25);
        shape.show(gc);
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
            if (cmi.getText().equals(shape.getName())) {
                cmi.setSelected(true);
            }
        }
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

}
