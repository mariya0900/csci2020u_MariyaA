package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class Controller {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn studentIDCol;
    @FXML
    private TableColumn assignmentsCol;
    @FXML
    private TableColumn midtermCol;
    @FXML
    private TableColumn finalExamCol;
    @FXML
    private TableColumn finalMarkCol;
    @FXML
    private TableColumn letterGradeCol;

    @FXML
    private Button btnAdd;
    @FXML
    private TextField addStudentID;
    @FXML
    private TextField addAssignments;
    @FXML
    private TextField addMidterm;
    @FXML
    private TextField addFinalExam;

    private TableView<StudentRecord> students;

    @FXML
    public void initialize(){
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("SID"));
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        finalExamCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        tableView.setItems(DataSource.getAllMarks());

    }
    @FXML
    private void clearStudentID(MouseEvent event) {
        addStudentID.clear();
    }

    @FXML
    private void clearAssignments(MouseEvent event) {
        addAssignments.clear();
    }

    @FXML
    private void clearMidterm(MouseEvent event) {
        addMidterm.clear();
    }
    @FXML
    private void clearFinalExam(MouseEvent event) {
        addFinalExam.clear();
    }

    public void btnPress(ActionEvent actionEvent) {
        DataSource.marks.add(new StudentRecord(addStudentID.getText(),Float.parseFloat(addAssignments.getText()),
                                    Float.parseFloat(addMidterm.getText()), Float.parseFloat(addFinalExam.getText())));

        System.out.println("click");
        //DataSource.getAllMarks().marks.add();

        //System.print.out(DataSource.getAllMarks().marks);
        //initialize();
        //tableView.setItems(DataSource.getAllMarks());
    }
}
