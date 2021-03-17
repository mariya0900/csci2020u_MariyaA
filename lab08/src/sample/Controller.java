package sample;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


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

    String currentFilename="initialFile.csv";
    private ObservableList<StudentRecord> data=DataSource.getAllMarks();

    @FXML
    public void initialize(){
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("SID"));
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        finalExamCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        tableView.setItems(data);

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

        System.out.println("Add button clicked");

    }
    @FXML
    public void save() throws IOException {
        FileWriter output = new FileWriter(currentFilename, false);//overwrites the file
        //output.write("SID, Assignments, Midterm, FinalExam\n");
        for (StudentRecord student : data) {
            output.write(student.getSID() + "," + student.getAssignments() +","+student.getMidterm()+","+student.getFinalExam()+"\n");
        }
        output.close();
        System.out.println("Saved to "+ currentFilename);
    }
    @FXML
    public void clear(){
        tableView.setItems(null);//clear the tableView without changing ObservableList
        System.out.println("TableView cleared");
        
        //System.out.println(data);
    }
    @FXML
    public void exit(){
        Platform.exit();
    }

    public void load(String filename) throws IOException {

        DataSource.marks.clear();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line =br.readLine ()) != null){
            String[] fields=line.split(",");

            StudentRecord newStudent=new StudentRecord(fields[0], Float.parseFloat(fields[1]),
                                                            Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));

            DataSource.marks.add(newStudent);

        }
        tableView.setItems(data);
    }
    @FXML
    public void open() throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            currentFilename=selectedFile.getName();
            System.out.println("Opening "+selectedFile.getName());
            load(selectedFile.getName());
        }
    }


    public void saveAs(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save As");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV file", ".csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(new Stage());
        currentFilename=file.getName();
        if (file != null) {
            save();
        }
    }
}
