package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.routines.EmailValidator;


public class Controller {
    @FXML
    public Button btnRegister;
    public TextField txFieldUsername;
    public TextField txFieldFullName;
    public TextField txFieldEmail;
    public TextField txFieldPhone;
    public PasswordField passwordField;
    public DatePicker datePicker;
    public DateTimeFormatter dateTimeFormatter;
    public Label lbFieldEmailError;

    @FXML
    public void initialize() {
        //Create a formatter for the date
        String pattern = "dd/M/yyyy";
        dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                String finalDate = null;
                if (date != null) {
                    finalDate = dateTimeFormatter.format(date);
                }
                return finalDate;
            }

            @Override
            public LocalDate fromString(String string) {
                LocalDate date = null;
                if (string != null) {
                    date = LocalDate.parse(string, dateTimeFormatter);
                }
                return date;
            }
        });




    }

    public void btnPress(ActionEvent actionEvent) {

        System.out.println("Username: "+txFieldUsername.getText());
        System.out.println("Password: "+passwordField.getText());
        System.out.println("Full name: "+txFieldFullName.getText());

        if (EmailValidator.getInstance().isValid(txFieldEmail.getText())){
            System.out.println("Email: "+txFieldEmail.getText());
        }
        else{
            lbFieldEmailError.setText("Invalid email address");
            System.out.println("ERROR: invalid email");
        }

        System.out.println("Phone: "+txFieldPhone.getText());
        System.out.println("Date(dd/M/yyyy): "+datePicker.getValue().format(dateTimeFormatter));

    }
}
