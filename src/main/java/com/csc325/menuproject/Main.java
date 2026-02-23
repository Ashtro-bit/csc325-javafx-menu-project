package com.csc325.menuproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        // Top Menu
        MenuBar menuBar = new MenuBar(
                new Menu("File"),
                new Menu("Edit"),
                new Menu("Theme"),
                new Menu("Help")
        );
        root.setTop(menuBar);

        // Left panel
        VBox leftPanel = new VBox();
        leftPanel.setPrefWidth(200);
        leftPanel.setPadding(new Insets(20));
        leftPanel.setAlignment(Pos.TOP_CENTER);
        leftPanel.setStyle("-fx-background-color: #B77B2F;");
        root.setLeft(leftPanel);

        // Center Table
        TableView<Object> table = new TableView<>();
        table.getColumns().addAll(
                new TableColumn<>("ID"),
                new TableColumn<>("First Name"),
                new TableColumn<>("Last Name"),
                new TableColumn<>("Department"),
                new TableColumn<>("Major"),
                new TableColumn<>("Email")
        );
        root.setCenter(table);

        // Right panel
        VBox rightPanel = new VBox(10);
        rightPanel.setPrefWidth(260);
        rightPanel.setPadding(new Insets(20));
        rightPanel.setStyle("-fx-background-color: #7CFC00;");

        TextField id = new TextField(); id.setPromptText("ID");
        TextField first = new TextField(); first.setPromptText("First Name");
        TextField last = new TextField(); last.setPromptText("Last Name");
        TextField dept = new TextField(); dept.setPromptText("Department");
        TextField major = new TextField(); major.setPromptText("Major");
        TextField email = new TextField(); email.setPromptText("Email");
        TextField imageUrl = new TextField(); imageUrl.setPromptText("ImageURL");

        Button clear = fullWidth(new Button("Clear"));
        Button add = fullWidth(new Button("Add"));
        Button delete = fullWidth(new Button("Delete"));
        Button edit = fullWidth(new Button("Edit"));

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        rightPanel.getChildren().addAll(
                id, first, last, dept, major, email, imageUrl,
                spacer,
                clear, add, delete, edit
        );

        root.setRight(rightPanel);

        Scene scene = new Scene(root, 1100, 600);

        stage.setTitle("CSC325 - Menu Project");
        stage.setScene(scene);
        stage.show();
    }

    private Button fullWidth(Button b) {
        b.setMaxWidth(Double.MAX_VALUE);
        return b;
    }

    public static void main(String[] args) {
        launch();
    }
}