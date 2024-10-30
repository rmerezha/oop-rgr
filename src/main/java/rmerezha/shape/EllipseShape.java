package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import rmerezha.util.Point;


public interface EllipseShape extends Shape {

    @Override
    default void show(GraphicsContext gc, Point p1, Point p2, boolean visible) {
        var corner = Point.findOppositeCorner(p1, p2);
        double minX = Point.minX(p2, corner);
        double minY = Point.minY(p2, corner);
        double w = Point.w(p2, corner);
        double h = Point.h(p2, corner);
        if (visible) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillOval(minX, minY, w, h);
        }
        gc.setFill(Color.BLACK);
        gc.strokeOval(minX, minY, w, h);
    }

    @Override
    default String getName() {
        return "Еліпс";
    }


}
