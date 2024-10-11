package rmerezha;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import java.util.ArrayList;
import java.util.List;

public class ToolbarBuilder {

    private final List<Node> nodes = new ArrayList<>();

    private ToolbarBuilder() {}

    public static ToolbarBuilder builder() {
        return new ToolbarBuilder();
    }

    public ToolbarBuilder setNode(Node node) {
        nodes.add(node);
        return this;
    }

    public ToolBar build() {
        var toolBar = new ToolBar();
        toolBar.getItems().addAll(nodes);
        return toolBar;
    }


}
