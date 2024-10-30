package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import rmerezha.util.Point;

public class EllipseShape extends Shape {


    @Override
    public void show(GraphicsContext gc) {
        var corner = Point.findOppositeCorner(p1, p2);
        double minX = Point.minX(p2, corner);
        double minY = Point.minY(p2, corner);
        double w = Point.w(p2, corner);
        double h = Point.h(p2, corner);
        if (isFilled) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillOval(minX, minY, w, h);
        }
        gc.setFill(Color.BLACK);
        gc.strokeOval(minX, minY, w, h);
    }

    @Override
    public String getName() {
        return "Еліпс";
    }

    @Override
    public Shape clone() {
        var shape = new EllipseShape();
        shape.setP1(this.p1);
        shape.setP2(this.p2);
        shape.isFilled = this.isFilled;
        return shape;
    }

}
