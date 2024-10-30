package rmerezha.shape;

import javafx.scene.canvas.GraphicsContext;

public class LineShape extends Shape {

    @Override
    public void show(GraphicsContext gc) {
        gc.strokeLine(p1.x(), p1.y(), p2.x(), p2.y());
    }

    @Override
    public Shape clone() {
        var shape = new LineShape();
        shape.setP1(this.p1);
        shape.setP2(this.p2);
        return shape;
    }

    @Override
    public String getName() {
        return "Лінія";
    }


}
