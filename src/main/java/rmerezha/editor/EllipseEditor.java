package rmerezha.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import rmerezha.shape.EllipseShape;
import rmerezha.shape.LineShape;
import rmerezha.shape.Shape;
import rmerezha.util.Point;
import rmerezha.util.WrappedArray;

import java.util.List;

public class EllipseEditor extends ShapeEditor {

    private Point corner1 = new Point(0, 0);

    public EllipseEditor(GraphicsContext gc, WrappedArray<Shape> shapes) {
        super(gc, shapes);
        shape = new EllipseShape();
    }

    @Override
    public void onMouseMove(MouseEvent event) {
        onPaint();

        Point currentPoint = new Point(event.getX(), event.getY());
        var corner = Point.findOppositeCorner(shape.getP1(), currentPoint);
        corner1 = corner;
        double minX2 = Point.minX(currentPoint, corner);
        double minY2 = Point.minY(currentPoint, corner);
        double w2 = Point.w(currentPoint, corner);
        double h2 = Point.h(currentPoint, corner);

        gc.setStroke(Color.BLUE);
        gc.strokeOval(minX2, minY2, w2, h2);
        gc.setStroke(Color.BLACK);
    }

    @Override
    public void onInitMenuPopup(Menu menu) {
        for (MenuItem i : menu.getItems()) {
            if (!(i instanceof CheckMenuItem cmi)) {
                continue;
            }
            cmi.setSelected(false);
            if (cmi.getText().equals("Еліпс")) {
                cmi.setSelected(true);
            }
        }
    }

    @Override
    public void onLBup(MouseEvent event) {
        shape.setP1(corner1);
        super.onLBup(event);
    }



}
