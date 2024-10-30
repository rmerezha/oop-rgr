package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import rmerezha.util.Point;

public interface Shape {

    void show(GraphicsContext gc, Point p1, Point p2, boolean visible);

    String getName();

}
