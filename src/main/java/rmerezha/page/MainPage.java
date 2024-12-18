package rmerezha.page;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import rmerezha.ListController;
import rmerezha.MyListViewer;
import rmerezha.PageManager;
import rmerezha.entity.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MainPage implements Page {

    private final Set<Group> groups;

    private final ListController<Group> listController;
    private final PageManager pageManager;

    public MainPage(Set<Group> groups, PageManager pageManager) {
        this.groups = groups;
        this.listController = new ListController<>(groups);
        this.pageManager = pageManager;
    }

    @Override
    public void show(Pane root) {
        BorderPane page = new BorderPane();
        List<Button> buttons = new ArrayList<>();
        for (var group : groups) {
            Button element = new Button();
            element.setText(group.getName());
            element.setOnAction(event -> {
                GroupPage groupPage = new GroupPage(group, listController, pageManager);
                groupPage.show(root);
            });
            buttons.add(element);
        }
        listController.zip(buttons);
        Button addGroupButton = getAddGroupButton(root);
        page.setCenter(listController.getListView());
        Pane pane = pageManager.createPage("Main Page", page, false, addGroupButton);
        root.getChildren().add(pane);
    }

    private Button getAddGroupButton(Pane root) {
        Button addGroupButton = new Button("Add Group");
        addGroupButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("New Group");
            dialog.setHeaderText("Name:");
            dialog.setContentText("");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(value -> {
                Group newGroup = new Group(value, new ArrayList<>());
                Button button = new Button(value);
                button.setOnAction(_event -> {
                    GroupPage groupPage = new GroupPage(newGroup, listController, pageManager);
                    groupPage.show(root);
                });
                listController.addElement(newGroup, button);
            });
        });
        return addGroupButton;
    }

}
