package rmerezha;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MyListViewer {

    private final ListView<Button> listView;


    public MyListViewer() {
        this.listView = new ListView<>();
        listView.setCellFactory(param -> new ButtonListCell());
    }


    public void addElement(Button button) {
        button.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10; " +
                        "-fx-padding: 0 0; " +
                        "-fx-text-fill: black; " +
                        "-fx-font-size: 30px; " +
                        "-fx-cursor: hand;"
        );
        listView.getItems().add(button);
    }

    public void removeElement(String name) {
        listView.getItems().removeIf(b -> b.getText().equals(name));
    }

    public void clear() {
        listView.getItems().clear();
    }

    private static class ButtonListCell extends javafx.scene.control.ListCell<Button> {
        @Override
        protected void updateItem(Button button, boolean empty) {
            super.updateItem(button, empty);

            if (empty || button == null) {
                setGraphic(null);
            } else {
                button.setMaxWidth(Double.MAX_VALUE);
                button.prefWidthProperty().bind(
                        getListView().widthProperty().subtract(20)
                );
                setGraphic(button);
            }
        }
    }

    public ListView<Button> getListView() {
        return listView;
    }

    @Override
    public String toString() {
        return "MyListViewer{" +
                "listView=" + listView +
                '}';
    }
}
