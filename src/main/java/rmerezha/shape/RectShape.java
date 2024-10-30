package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import rmerezha.util.Point;

public interface RectShape extends Shape {

    @Override
    default void show(GraphicsContext gc, Point p1, Point p2, boolean visible) {
        double minX = Point.minX(p1, p2);
        double minY = Point.minY(p1,p2);
        double w = Point.w(p1, p2);
        double h = Point.h(p1, p2);
        gc.strokeRect(minX, minY, w, h);
    }

    @Override
    default String getName() {
        return "Прямокутник";
    }

}
