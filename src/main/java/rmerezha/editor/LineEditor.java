package rmerezha.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import rmerezha.shape.LineShape;
import rmerezha.shape.PointShape;
import rmerezha.shape.Shape;
import rmerezha.util.Point;
import rmerezha.util.WrappedArray;

import java.util.List;

public class LineEditor extends ShapeEditor {

    public LineEditor(GraphicsContext gc, WrappedArray<Shape> shapes) {
        super(gc, shapes);
        shape = new LineShape();
    }

    @Override
    public void onMouseMove(MouseEvent event) {
        onPaint();
        Point currentPoint = new Point(event.getX(), event.getY());
        double minX2 = Point.minX(shape.getP1(), currentPoint);
        double minY2 = Point.minY(shape.getP1(), currentPoint);
        double w2 = Point.w(shape.getP1(), currentPoint);
        double h2 = Point.h(shape.getP1(), currentPoint);
        gc.setStroke(Color.BLUE);
        gc.strokeLine(shape.getP1().x(), shape.getP1().y(), currentPoint.x(), currentPoint.y());
        gc.setStroke(Color.BLACK);
    }

    @Override
    public void onInitMenuPopup(Menu menu) {
        for (MenuItem i : menu.getItems()) {
            if (!(i instanceof CheckMenuItem cmi)) {
                continue;
            }
            cmi.setSelected(false);
            if (cmi.getText().equals("Лінія")) {
                cmi.setSelected(true);
            }
        }
    }

}
