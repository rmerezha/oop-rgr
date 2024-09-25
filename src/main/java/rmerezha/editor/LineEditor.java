package rmerezha.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import rmerezha.shape.LineShape;
import rmerezha.shape.PointShape;
import rmerezha.shape.Shape;
import rmerezha.util.Point;

import java.util.List;

public class LineEditor extends ShapeEditor {

    public LineEditor(GraphicsContext gc, List<Shape> shapes) {
        super(gc, shapes);
        shape = new LineShape();
    }

//    @Override
//    public void onLBup(MouseEvent event) {
//        shape.setP2(new Point(event.getX(), event.getY()));
//        shape.show(gc);
//    }


}
