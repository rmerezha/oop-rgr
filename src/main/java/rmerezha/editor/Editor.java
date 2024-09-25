package rmerezha.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class Editor {

    public abstract void onLBdown(MouseEvent event);
    public abstract void onLBup(MouseEvent event);
    public abstract void onMouseMove(MouseEvent event);
    protected abstract void onPaint();

}
