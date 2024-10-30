package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import rmerezha.util.Point;

public interface PointShape extends Shape {

    @Override
    default void show(GraphicsContext gc, Point p1, Point p2, boolean visible) {
        gc.fillOval(p1.x(), p1.y(), 10,10);
    }

    @Override
    default String getName() {
        return "Крапка";
    }


}
