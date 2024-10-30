package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;
import rmerezha.util.Point;

public interface CubeShape extends RectShape, LineShape {
    @Override
    default void show(GraphicsContext gc, Point p1, Point p2, boolean visible) {
        var x = Point.minX(p1, p2);
        var y = Point.minY(p1, p2);
        var p = new Point(x, y);
        var dist = Math.sqrt(Math.pow(p2.x() - p1.x(), 2) + Math.pow(p2.y() - p1.y(), 2));
        Point p11 = null;
        Point p22 = null;
        if (p.equals(p1)) {
            p22 = new Point(p1.x() + dist /7 * 2 , p1.y() - dist /7 * 2.5);
            p11 = new Point(p2.x() - dist /7 * 2 , p2.y() + dist /7 * 2.5);
            RectShape.super.show(gc, p1, p11, visible);
            RectShape.super.show(gc, p2, p22, visible);
            Point p111 = new Point(p11.x(), p1.y());
            Point p1111 = new Point(p1.x(), p11.y());
            Point p222 = new Point(p2.x(), p22.y());
            Point p2222 = new Point(p22.x(), p2.y());
            LineShape.super.show(gc, p11, p2, visible);
            LineShape.super.show(gc, p1, p22, visible);
            LineShape.super.show(gc, p111, p222, visible);
            LineShape.super.show(gc, p1111, p2222, visible);
        } else {
            p11 = new Point(p2.x() + dist /7 * 2 , p2.y() - dist /7 * 2.5);
            p22 = new Point(p1.x() - dist /7 * 2 , p1.y() + dist /7 * 2.5);
            RectShape.super.show(gc, p1, p11, visible);
            RectShape.super.show(gc, p2, p22, visible);
            Point p222 = new Point(p22.x(), p2.y());
            Point p2222 = new Point(p2.x(), p22.y());
            Point p111 = new Point(p1.x(), p11.y());
            Point p1111 = new Point(p11.x(), p1.y());
            LineShape.super.show(gc, p11, p2, visible);
            LineShape.super.show(gc, p1, p22, visible);
            LineShape.super.show(gc, p111, p222, visible);
            LineShape.super.show(gc, p1111, p2222, visible);
        }


    }

    @Override
    default String getName() {
        return "Куб";
    }
}
