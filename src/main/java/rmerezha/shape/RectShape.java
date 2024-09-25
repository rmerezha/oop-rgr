package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import rmerezha.util.Point;

public class RectShape extends Shape {

    @Override
    public void show(GraphicsContext gc) {
        double minX = Point.minX(p1, p2);
        double minY = Point.minY(p1,p2);
        double w = Point.w(p1, p2);
        double h = Point.h(p1, p2);
        gc.strokeRect(minX, minY, w, h);
    }

    @Override
    public Shape clone() {
        var shape = new RectShape();
        shape.setP1(this.p1);
        shape.setP2(this.p2);
        return shape;
    }

}
