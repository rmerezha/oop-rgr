package rmerezha.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import rmerezha.shape.LineShape;
import rmerezha.shape.RectShape;
import rmerezha.shape.Shape;
import rmerezha.util.Point;
import rmerezha.util.WrappedArray;

import java.util.List;

public class RectEditor extends ShapeEditor {

    private Point corner1 = new Point(0, 0);

    public RectEditor(GraphicsContext gc, WrappedArray<Shape> shapes) {
        super(gc, shapes);
        shape = new RectShape();
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

        gc.setStroke(Color.RED);
        gc.strokeRect(minX2, minY2, w2, h2);
        gc.setStroke(Color.BLACK);
    }

    @Override
    public void onLBup(MouseEvent event) {
        shape.setP1(corner1);
        super.onLBup(event);
    }
}
