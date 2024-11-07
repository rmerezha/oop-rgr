package rmerezha.table;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class MyTable<T> {

    private final Stage dialogStage;
    private final TableView<T> table;
    private final ObservableList<T> entities = FXCollections.observableArrayList(new ArrayList<>());
    private final static Path PATH = Path.of("history.txt");

    static {
        try {
            Files.deleteIfExists(PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MyTable(Stage dialogStage) {
        this.dialogStage = dialogStage;
        table = new TableView<>();
        table.setItems(entities);
    }

    public static <E> MyTable<E> createWindow() {
        MyTable<E> myTable = new MyTable<>(new Stage());
        myTable.dialogStage.initModality(Modality.NONE);
        Scene scene = new Scene(myTable.table);
        myTable.dialogStage.setScene(scene);
        return myTable;
    }

    public void addColumn(String name, Callback<TableColumn.CellDataFeatures<T,String>, ObservableValue<String>> callback) {
        TableColumn<T, String> column = new TableColumn<>(name);
        column.setCellValueFactory(callback);
        column.setStyle("-fx-font-size: 20px;");
        table.getColumns().add(column);
    }

    public void addEntity(T entity) {
        if ( entity == null ) {
            return;
        }
        entities.add(entity);
        write(entity);
    }

    public void clear() {
        entities.clear();
    }

    private void write(T entity) {
        try {
            Files.writeString(PATH, entity.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void show() {
        dialogStage.show();
    }

    public void close() {
        dialogStage.close();
    }
}
