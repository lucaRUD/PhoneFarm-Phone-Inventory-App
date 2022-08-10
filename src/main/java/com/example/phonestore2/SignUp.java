package com.example.phonestore2;

import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.*;

public class SignUp implements Initializable {


    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;

    @FXML
    private JFXRadioButton Femalebtn;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private JFXRadioButton Malebtn;

    @FXML
    private ToggleGroup togglegroup1;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Username;

    @FXML
    private Button signInbtn;

    @FXML
    private Button signUpbtn;

    private Connection connection;
    private DBhandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        handler = new DBhandler();
    }
    public void signUpAction (ActionEvent ae1) throws SQLException, ClassNotFoundException {
        PauseTransition pt= new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> System.out.print("Sign Up Succesfully"));
        pt.play();

        //Saving Data

        String insert = "INSERT INTO login(firstname, lastname, username, password, gender)"
                + "VALUES (?,?,?,?,?)";
        connection = handler.getConnection();
        pst = connection.prepareStatement(insert);

        pst.setString(1,FirstName.getText());
        pst.setString(2,LastName.getText());
        pst.setString(3,Username.getText());
        pst.setString(4,Password.getText());
        pst.setString(5,getGender());

        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Signed Up Succesfully");
        }
    @FXML
    public void loginAction (ActionEvent ae2) throws IOException {
        PhoneFarmMain.changeScene("Login.fxml");
    }
    public String getGender(){
        String gen ="";

        if(Malebtn.isSelected())
        {
            gen= "Male";
        }else
        if(Femalebtn.isSelected())
        {
            gen= "Female";
        }
        return gen;
    }
}
