package rmerezha;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class ListController<T> {

    private final Set<T> set;
    private final MyListViewer myListViewer;

    public ListController(Set<T> list) {
        this.set = list;
        this.myListViewer = new MyListViewer();
    }

    public void addElement(T element, Button button) {
        set.add(element);
        myListViewer.addElement(button);
    }


    public void removeElement(T element, String name) {
        set.remove(element);
        myListViewer.removeElement(name);
    }

    public ListView<Button> getListView() {
        return myListViewer.getListView();
    }

    public void clearLists() {
        set.clear();
        myListViewer.clear();
    }

    public void zip(List<Button> buttons) {
        for (Button button : buttons) {
            myListViewer.addElement(button);
        }
    }
}
