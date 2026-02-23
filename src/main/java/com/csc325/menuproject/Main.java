package com.csc325.menuproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

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

        // CENTER TABLE
        TableView<Student> table = new TableView<>();

        TableColumn<Student, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> firstCol = new TableColumn<>("First Name");
        firstCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Student, String> lastCol = new TableColumn<>("Last Name");
        lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Student, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));

        TableColumn<Student, String> majorCol = new TableColumn<>("Major");
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().setAll(idCol, firstCol, lastCol, deptCol, majorCol, emailCol);
        table.getItems().addAll(
                new Student("1", "Leon", "Kennedy", "CS", "Software", "leon@rpd.gov", ""),
                new Student("2", "Jill", "Valentine", "IT", "Security", "jill@stars.gov", "")
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