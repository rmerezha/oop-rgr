package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;

public class PointShape extends Shape {

    @Override
    public void show(GraphicsContext gc) {
        gc.fillOval(p1.x(), p1.y(), 10,10);
    }

    @Override
    public String getName() {
        return "Крапка";
    }

    @Override
    public Shape clone() {
        var shape = new PointShape();
        shape.setP1(this.p1);
        shape.setP2(this.p2);
        return shape;
    }

}
