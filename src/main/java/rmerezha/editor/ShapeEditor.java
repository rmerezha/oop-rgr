package rmerezha.editor;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import rmerezha.shape.LineShape;
import rmerezha.shape.Shape;
import rmerezha.util.Point;
import rmerezha.util.WrappedArray;

import java.util.HashMap;
import java.util.List;

public abstract class ShapeEditor extends Editor {
    protected Shape shape;
    protected GraphicsContext gc;
    protected WrappedArray<Shape> shapes;


    public ShapeEditor(GraphicsContext gc, WrappedArray<Shape> shapes) {
        this.gc = gc;
        this.shapes = shapes;
    }

    @Override
    public void onLBdown(MouseEvent event) {
        shape.setP1(new Point(event.getX(), event.getY()));
    }

    @Override
    public void onLBup(MouseEvent event) {
        shape.setP2(new Point(event.getX(), event.getY()));
        shapes.add(shape.clone());
        onPaint();
    }


    @Override
    public void onMouseMove(MouseEvent event) {

        onPaint();
        Point currentPoint = new Point(event.getX(), event.getY());

        double minX2 = Point.minX(shape.getP1(), currentPoint);
        double minY2 = Point.minY(shape.getP1(), currentPoint);
        double w2 = Point.w(shape.getP1(), currentPoint);
        double h2 = Point.h(shape.getP1(), currentPoint);

        gc.setStroke(Color.RED);
        gc.strokeRect(minX2, minY2, w2, h2);
        gc.setStroke(Color.BLACK);

    }

    @Override
    protected void onPaint() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).show(gc);
        }
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public WrappedArray<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(WrappedArray<Shape> shapes) {
        this.shapes = shapes;
    }
}
