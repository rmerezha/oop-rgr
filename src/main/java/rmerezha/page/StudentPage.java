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
import rmerezha.entity.Grade;
import rmerezha.entity.Student;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class StudentPage implements Page {

    private final Student student;
    private final ListController<Student> parentListController;
    private final ListController<Grade> listController;
    private final PageManager pageManager;

    public StudentPage(Student student, ListController<Student> parentListController, PageManager pageManager) {
        this.student = student;
        this.parentListController = parentListController;
        this.listController = new ListController<>(student.getGrades());
        this.pageManager = pageManager;
    }

    @Override
    public void show(Pane root) {
        BorderPane page = new BorderPane();
        List<Button> buttons = new ArrayList<>();
        for (var grade : student.getGrades()) {
            Button element = new Button();
            element.setText(grade.getDate() + "\t" + grade.getValue());
            listController.addElement(grade, element);
        }
        listController.zip(buttons);
        Button addGradeButton = new Button("Add Grade");
        addGradeButton.setOnAction(event -> {
            TextInputDialog dateDialog = new TextInputDialog();
            dateDialog.setTitle("Enter Date");
            dateDialog.setHeaderText("Please enter the date:");
            dateDialog.setContentText("Date (format: YYYY-MM-DD):");

            Optional<String> dateResult = dateDialog.showAndWait();

            dateResult.ifPresent(dateValue -> {
                try {
                    LocalDate date = LocalDate.parse(dateValue);

                    TextInputDialog numberDialog = new TextInputDialog();
                    numberDialog.setTitle("Enter Grade Number");
                    numberDialog.setHeaderText("Please enter a grade with a decimal point:");
                    numberDialog.setContentText("Grade:");

                    Optional<String> numberResult = numberDialog.showAndWait();

                    numberResult.ifPresent(numberValue -> {
                        try {
                            double value = Double.parseDouble(numberValue);
                            Grade grade = new Grade(value, date);
//                            student.addGrade(grade);
                            Button button = new Button();
                            button.setText(grade.getDate() + "\tGrade: " + grade.getValue());
                            listController.addElement(grade, button);
                            pageManager.getTitleLabel().setText(student.getFullName() + "\tTotalGrade: " + student.getTotalGrades());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
            });
        });

        Button deleteGroup = new Button("Delete Student");
        deleteGroup.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Do you really want to delete this Student?");
            alert.setContentText("");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    parentListController.removeElement(student, student.getFullName());
                    pageManager.goBack();
                }
            });
        });


        page.setCenter(listController.getListView());
        BorderPane pane = pageManager.createPage(student.getFullName() + "\tTotalGrade: " + student.getTotalGrades(), page, true, addGradeButton, deleteGroup);
        pageManager.navigateTo(pane);
        root.getChildren().removeFirst();
        root.getChildren().add(pane);
    }
}
