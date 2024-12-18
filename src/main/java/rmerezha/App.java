package rmerezha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import rmerezha.entity.Group;
import rmerezha.page.MainPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;


public class App extends Application {
    private static Set<Group> GROUPS = new HashSet<>();
    private static final String PATH = "/home/rmerezha/oop-rgr/data.bin";
    private static final Serializer<HashSet<Group>> SERIALIZER = new Serializer<>();

    static {
        try {
            Path path = Path.of(PATH);
            if (Files.exists(path) && Files.size(path) > 0) {
                GROUPS = new HashSet<>(SERIALIZER.read(PATH));
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        var root = new StackPane();
        var pm = new PageManager(primaryStage, root);


        MainPage mainPage = new MainPage(GROUPS, pm);

        mainPage.show(root);
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setOnCloseRequest(event -> {
            try {
                SERIALIZER.write((HashSet<Group>) GROUPS, PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("RGR");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
