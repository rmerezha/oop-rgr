package rmerezha.util;

public record Point(double x, double y) {

    public Point clone() {
        return new Point(x, y);
    }

    public static double minX(Point p1, Point p2) {
        return Math.min(p1.x(), p2.x());
    }

    public static double minY(Point p1, Point p2) {
        return Math.min(p1.y(), p2.y());
    }

    public static double w(Point p1, Point p2) {
        return Math.abs(p1.x() - p2.x());
    }

    public static double h(Point p1, Point p2) {
        return Math.abs(p1.y() - p2.y());
    }

    public static Point findOppositeCorner(Point center, Point corner) {
        double oppositeX = 2 * center.x() - corner.x();
        double oppositeY = 2 * center.y() - corner.y();

        return new Point(oppositeX, oppositeY);
    }
}
