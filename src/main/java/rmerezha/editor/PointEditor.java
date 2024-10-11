package rmerezha.editor;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import rmerezha.shape.PointShape;
import rmerezha.shape.Shape;
import rmerezha.util.Point;
import rmerezha.util.WrappedArray;

import java.util.HashMap;
import java.util.List;

public class PointEditor extends ShapeEditor{

    public PointEditor(GraphicsContext gc, WrappedArray<Shape> shapes) {
        super(gc, shapes);
        shape = new PointShape();
    }


    @Override
    public void onLBdown(MouseEvent event) {
        shape.setP1(new Point(event.getX(), event.getY()));
        shape.setP2(new Point(event.getX(), event.getY()));
    }

    @Override
    public void onLBup(MouseEvent event) {
        shapes.add(shape.clone());
        onPaint();
    }

    @Override
    public void onMouseMove(MouseEvent event) {
        // nothing
    }

    @Override
    public void onInitMenuPopup(Menu menu) {
        for (MenuItem i : menu.getItems()) {
            if (!(i instanceof CheckMenuItem cmi)) {
                continue;
            }
            cmi.setSelected(false);
            if (cmi.getText().equals("Крапка")) {
                cmi.setSelected(true);
            }
        }
    }
}
