package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import rmerezha.util.Point;

import java.util.StringJoiner;

public class ShapeStorage {

    private final Shape shape;


    private Point p1,p2;
    private Boolean visible = true;

    public ShapeStorage(Shape shape, Point p1, Point p2) {
        this.shape = shape;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Shape getShape() {
        return shape;
    }

    public ShapeStorage clone() {
        return new ShapeStorage(shape, p1.clone(), p2.clone());
    }

    public void show(GraphicsContext gc) {
        shape.show(gc, p1, p2, visible);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\t");
        return sj.add(shape.getName())
                .add(p1.toString())
                .add(p2.toString()) + System.lineSeparator();
    }
}
