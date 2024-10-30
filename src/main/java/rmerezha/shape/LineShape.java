package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import rmerezha.util.Point;

public interface LineShape extends Shape {

    @Override
    default void show(GraphicsContext gc, Point p1, Point p2, boolean visible) {
        gc.strokeLine(p1.x(), p1.y(), p2.x(), p2.y());
    }


    @Override
    default String getName() {
        return "Лінія";
    }


}
