package rmerezha;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class PageManager {
    private final Stage stage;
    private final StackPane root;
    private final Stack<BorderPane> pageHistory = new Stack<>();
    private Label titleLabel;

    public PageManager(Stage stage, StackPane root) {
        this.stage = stage;
        this.root = root;

    }

    public BorderPane createPage(String title, BorderPane basePage, boolean withBack, Button... buttons) {
        BorderPane page = new BorderPane();
        HBox hBox = new HBox(10);
        if (withBack) {
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> goBack());
            backButton.setStyle(
                    "-fx-background-color: red; " +
                            "-fx-border-color: black; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-radius: 0; " +
                            "-fx-padding: 0 0; " +
                            "-fx-text-fill: black; " +
                            "-fx-font-size: 30px; " +
                            "-fx-cursor: hand;"
            );
            hBox.getChildren().add(backButton);
        }

        for (Button button : buttons) {
            button.setStyle(
                    "-fx-background-color: yellow; " +
                            "-fx-border-color: black; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-radius: 0; " +
                            "-fx-padding: 0 0; " +
                            "-fx-text-fill: black; " +
                            "-fx-font-size: 30px; " +
                            "-fx-cursor: hand;"
            );
            hBox.getChildren().add(button);
        }


        titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 36px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #2C3E50; " +
                "-fx-text-alignment: center; " +
                "-fx-padding: 20px; " +
                "-fx-background-color: #ECF0F1; " +
                "-fx-border-radius: 0px; " +
                "-fx-border-color: #BDC3C7; " +
                "-fx-border-width: 2px;");
        titleLabel.prefWidthProperty().bind(stage.widthProperty());
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(titleLabel, hBox, basePage);
        page.setCenter(vBox);

        return page;
    }

    public void goBack() {
        if (!pageHistory.isEmpty()) {
            BorderPane previousPage = pageHistory.pop();
            root.getChildren().removeFirst();
            root.getChildren().add(previousPage);
        }
    }

    public void navigateTo(BorderPane page) {
        if (!root.getChildren().isEmpty()) {
            pageHistory.push((BorderPane) root.getChildren().getFirst());
            root.getChildren().removeFirst();
        }
        root.getChildren().add(page);
    }

    public Label getTitleLabel() {
        return titleLabel;
    }
}
