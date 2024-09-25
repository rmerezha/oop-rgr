package rmerezha;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rmerezha.editor.PointEditor;
import rmerezha.editor.ShapeEditor;
import rmerezha.shape.Shape;
import rmerezha.util.WrappedArray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShapeObjectsEditor {

    private ShapeEditor editor;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final WrappedArray<Shape> shapes = new WrappedArray<>(100+13);
    private final HashMap<EventType<MouseEvent>, EventHandler<MouseEvent>> handlers = new HashMap<>();

    public ShapeObjectsEditor(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(5);
        editor = new PointEditor(this.gc, shapes);
    }


    public <T extends ShapeEditor> void setEditor(Class<T> clazz) {
        try {
            this.editor = clazz.getConstructor(new Class<?>[]{GraphicsContext.class, WrappedArray.class}).newInstance(gc, shapes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        removeHandlers();
        handlers.clear();
        addHandlers();
    }

    private void addHandlers() {
        handlers.put(MouseEvent.MOUSE_PRESSED, editor::onLBdown);
        handlers.put(MouseEvent.MOUSE_DRAGGED, editor::onMouseMove);
        handlers.put(MouseEvent.MOUSE_RELEASED, editor::onLBup);

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
}
