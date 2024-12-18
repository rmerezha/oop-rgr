package rmerezha.page;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import rmerezha.ListController;
import rmerezha.MyListViewer;
import rmerezha.PageManager;
import rmerezha.entity.Group;
import rmerezha.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GroupPage implements Page {

    private final Group group;
    private final ListController<Group> parentListController;
    private final ListController<Student> listController;
    private final PageManager pageManager;

    public GroupPage(Group group, ListController<Group> parentListController, PageManager pageManager) {
        this.group = group;
        this.parentListController = parentListController;
        this.listController = new ListController<>(group.getStudents());
        this.pageManager = pageManager;
    }

    @Override
    public void show(Pane root) {
        BorderPane page = new BorderPane();
        List<Button> buttons = new ArrayList<>();
        for (var student : group.getStudents()) {
            Button element = new Button();
            element.setText(student.getFullName());
            element.setOnAction(event -> {
                StudentPage studentPage = new StudentPage(student, listController, pageManager);
                studentPage.show(root);
            });
            listController.addElement(student, element);
        }
        listController.zip(buttons);
        Button addStudentButton = getAddStudentButton(root);

        Button deleteGroup = new Button("Delete Group");
        deleteGroup.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Do you really want to delete this group?");
            alert.setContentText("");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    parentListController.removeElement(group, group.getName());
                    pageManager.goBack();
                }
            });
        });


        page.setCenter(listController.getListView());
        BorderPane pane = pageManager.createPage(group.getName(), page, true, addStudentButton, deleteGroup);
        pageManager.navigateTo(pane);
        root.getChildren().removeFirst();
        root.getChildren().add(pane);
    }

    private Button getAddStudentButton(Pane root) {
        Button addStudentButton = new Button("Add Student");
        addStudentButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("New Student");
            dialog.setHeaderText("Full Name:");
            dialog.setContentText("");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(value -> {
                Student newStudent = new Student(value, new ArrayList<>());
//                group.addStudent(newStudent);
                Button button = new Button(value);
                button.setOnAction(_event -> {
                    StudentPage studentPage = new StudentPage(newStudent, listController, pageManager);
                    studentPage.show(root);
                });
                listController.addElement(newStudent, button);
            });
        });
        return addStudentButton;
    }

}
