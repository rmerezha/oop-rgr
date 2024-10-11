package rmerezha.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import rmerezha.shape.LineShape;
import rmerezha.shape.RectShape;
import rmerezha.shape.Shape;
import rmerezha.util.Point;
import rmerezha.util.WrappedArray;

import java.util.List;

public class RectEditor extends ShapeEditor {

    public RectEditor(GraphicsContext gc, WrappedArray<Shape> shapes) {
        super(gc, shapes);
        shape = new RectShape();
    }

    @Override
    public void onInitMenuPopup(Menu menu) {
        for (MenuItem i : menu.getItems()) {
            if (!(i instanceof CheckMenuItem cmi)) {
                continue;
            }
            cmi.setSelected(false);
            if (cmi.getText().equals("Прямокутник")) {
                cmi.setSelected(true);
            }
        }
    }
}
