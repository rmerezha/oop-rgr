package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import rmerezha.util.Point;

public interface LineOOShape extends LineShape,EllipseShape {

    @Override
    default void show(GraphicsContext gc, Point p1, Point p2, boolean visible) {
        LineShape.super.show(gc, p1, p2, false);
        Point p3 = new Point(p1.x() + 10, p1.y() + 10);
        Point p4 = new Point(p2.x() + 10, p2.y() + 10);
        EllipseShape.super.show(gc, p1, p3, visible);
        EllipseShape.super.show(gc, p2,p4, visible);
    }

    @Override
    default String getName() {
        return "Лінія з кружечками";
    }

}
