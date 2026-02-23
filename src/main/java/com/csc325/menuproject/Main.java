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
        leftPanel.getStyleClass().add("left-panel");
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
        rightPanel.getStyleClass().add("right-panel");

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

        // When you click a row, load it into the form
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, sel) -> {
            if (sel == null) return;

            id.setText(sel.getId());
            first.setText(sel.getFirstName());
            last.setText(sel.getLastName());
            dept.setText(sel.getDepartment());
            major.setText(sel.getMajor());
            email.setText(sel.getEmail());
            imageUrl.setText(sel.getImageUrl());
        });

        // Clear button
        clear.setOnAction(e -> {
            id.clear();
            first.clear();
            last.clear();
            dept.clear();
            major.clear();
            email.clear();
            imageUrl.clear();
            table.getSelectionModel().clearSelection();
        });

// Add button (minimal validation: require ID + first + last)
        add.setOnAction(e -> {
            String idText = id.getText().trim();
            String firstText = first.getText().trim();
            String lastText = last.getText().trim();

            if (idText.isEmpty() || firstText.isEmpty() || lastText.isEmpty()) {
                showAlert("Missing fields", "Please enter at least ID, First Name, and Last Name.");
                return;
            }

            Student s = new Student(
                    idText,
                    firstText,
                    lastText,
                    dept.getText().trim(),
                    major.getText().trim(),
                    email.getText().trim(),
                    imageUrl.getText().trim()
            );

            table.getItems().add(s);
            table.getSelectionModel().select(s); // nice UX
        });

// Delete button
        delete.setOnAction(e -> {
            Student sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) {
                showAlert("Nothing selected", "Select a row in the table to delete.");
                return;
            }
            table.getItems().remove(sel);
            table.getSelectionModel().clearSelection();
        });

// Edit button (updates selected row)
        edit.setOnAction(e -> {
            Student sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) {
                showAlert("Nothing selected", "Select a row in the table to edit.");
                return;
            }

            String idText = id.getText().trim();
            String firstText = first.getText().trim();
            String lastText = last.getText().trim();

            if (idText.isEmpty() || firstText.isEmpty() || lastText.isEmpty()) {
                showAlert("Missing fields", "Please enter at least ID, First Name, and Last Name.");
                return;
            }

            sel.setId(idText);
            sel.setFirstName(firstText);
            sel.setLastName(lastText);
            sel.setDepartment(dept.getText().trim());
            sel.setMajor(major.getText().trim());
            sel.setEmail(email.getText().trim());
            sel.setImageUrl(imageUrl.getText().trim());

            table.refresh(); // because we used plain String fields, refresh forces UI update
        });

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        rightPanel.getChildren().addAll(
                id, first, last, dept, major, email, imageUrl,
                spacer,
                clear, add, delete, edit
        );

        root.setRight(rightPanel);

        Scene scene = new Scene(root, 1100, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("CSC325 - Menu Project");
        stage.setScene(scene);
        stage.show();
    }

    private Button fullWidth(Button b) {
        b.setMaxWidth(Double.MAX_VALUE);
        return b;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}