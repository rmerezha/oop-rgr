package rmerezha.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import rmerezha.shape.EllipseShape;
import rmerezha.shape.LineShape;
import rmerezha.shape.Shape;
import rmerezha.util.Point;

import java.util.List;

public class EllipseEditor extends ShapeEditor{

    public EllipseEditor(GraphicsContext gc, List<Shape> shapes) {
        super(gc, shapes);
        shape = new EllipseShape();
    }

}
