package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import rmerezha.util.Point;

public class EllipseShape extends Shape {

    @Override
    public void show(GraphicsContext gc) {
        double minX = Point.minX(p1, p2);
        double minY = Point.minY(p1,p2);
        double w = Point.w(p1, p2);
        double h = Point.h(p1, p2);
        gc.setFill(Color.LIGHTGREEN);
        gc.fillOval(minX, minY, w, h);
        gc.setFill(Color.BLACK);
        gc.strokeOval(minX, minY, w, h);
    }

    @Override
    public Shape clone() {
        var shape = new EllipseShape();
        shape.setP1(this.p1);
        shape.setP2(this.p2);
        return shape;
    }

}
