/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import dao.coursesDAO;
import dao.enrollDAO;
import dao.studentDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.enroll;

/**
 * FXML Controller class
 *
 * @author Raghad Saqallah
 */
public class EnrollmentController implements Initializable {

    @FXML
    private ComboBox<Integer> studentComboBox;
    @FXML
    private ComboBox<Integer> courseComboBox;
    @FXML
    private DatePicker enrollmentDatePicker;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button viewButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TableView<enroll> enrollmentTable;
    @FXML
    private TableColumn<enroll, Integer> colEnrollId;
    @FXML
    private TableColumn<enroll, Integer> colStudentId;
    @FXML
    private TableColumn<enroll, Integer> colCourseId;
    @FXML
    private TableColumn<enroll, Integer> colEnrollDate;
    enrollDAO enrollDAO = new enrollDAO();
    studentDAO studentDAO = new studentDAO();
    coursesDAO coursesDAO = new coursesDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseComboBox.getItems().addAll(coursesDAO.getCourseID());  // put the ids in combo
        studentComboBox.getItems().addAll(studentDAO.getStudentID());

        colEnrollId.setCellValueFactory(new PropertyValueFactory<>("enroll_id"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colEnrollDate.setCellValueFactory(new PropertyValueFactory<>("enrollDate"));

        enrollmentTable.getSelectionModel().selectedItemProperty().addListener(  // return data of the selected row to the field(combo and datepicker)
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    courseComboBox.setValue(newValue.getCourse_id());
                    studentComboBox.setValue(newValue.getStudent_id());
                    enrollmentDatePicker.setValue(LocalDate.parse(newValue.getEnrollDate()));
                }
        );

    }

    @FXML
    private void handleAdd(ActionEvent event) { // add a new record with validiting and showing the result 
        if (validor()) {
            int studentID = studentComboBox.getValue();
            int courseID = courseComboBox.getValue();
            String date = enrollmentDatePicker.getValue().toString();
            enroll o = new enroll(studentID, courseID, date);

            if (enrollDAO.isEnrolled(studentID, courseID)) {
                showWarningAlert("Duplicate Entry", "Record already exists",
                        "This student is already enrolled in this course.");
                return;
            }

            if (enrollDAO.insertOne(o)) {
                clear();
                handleView(event);
                showInfoAlert("Success", "Added Successfully");
            }
        } else {
            showWarningAlert("Invalid input", "Missing Data", "Please fill all fields");
        }

    }

    @FXML
    private void handleUpdate(ActionEvent event) {  // update a selected row data
        enroll o = enrollmentTable.getSelectionModel().getSelectedItem();

        if (o == null) {
            showWarningAlert("No Selection", "No Record Selected",
                    "please select record from the table");
            return;
        }

        o.setStudent_id(studentComboBox.getValue());
        o.setCourse_id(courseComboBox.getValue());
        o.setEnrollDate(enrollmentDatePicker.getValue().toString());

        if (enrollDAO.update(o)) {
            handleView(event);
            showInfoAlert("Success", "Record Updated Successfully");
            clear();
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {  //delete a selected row from the table
        enroll o = enrollmentTable.getSelectionModel().getSelectedItem();
        if (o == null) {
            showWarningAlert("No Selection", "No Record Selected",
                    "please select record from the table");
        } else {
            if (showConfirmationAlert("Delete Confirmation",
                     "Are you sure",
                    "Do you want to delete this record")) {
                enrollDAO.deleteRecorde(o);
                handleView(event);
                clear();
            }
        }

    }

    @FXML
    private void handleView(ActionEvent event) {  // show all the records
        List<enroll> list = enrollDAO.findAll();
        enrollmentTable.getItems().setAll(list);
    }

    private boolean showConfirmationAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    private void showWarningAlert(String title, String header, String message) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();

    }

    private void showInfoAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void clear() {  // clear method
        studentComboBox.setValue(null);
        courseComboBox.setValue(null);
        enrollmentDatePicker.setValue(null);
    }

    public boolean validor() { // validate method
        if (studentComboBox != null && courseComboBox != null && enrollmentDatePicker.getValue() != null) {
            return true;
        }
        return false;
    }

}
