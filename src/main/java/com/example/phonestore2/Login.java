package com.example.phonestore2;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable, loginInterface {

    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;


    @FXML
    private Button signUpbtn;

    @FXML
    private Hyperlink admin_link;

    @FXML
    private JFXCheckBox check;

    @FXML
    private JFXButton signInbtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private ImageView progress;

    private Connection connection;
    private DBhandler handler;
    private PreparedStatement pst;
    private static Login instance;

    public Login(){
        instance=this;
    }
    public static Login getInstance(){
        return instance;
    }
    public String username(){
        return username.getText();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progress.setVisible(false);
        handler = new DBhandler();

    }
    @Override
    public void loginAction(ActionEvent e) throws SQLException, ClassNotFoundException {
        progress.setVisible(true);
        PauseTransition pt= new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.play();

        //Retrieve Data from Database

        connection = handler.getConnection();
        String q1="SELECT * from login where username=? and password=?";
        try{
            pst = connection.prepareStatement(q1);
            pst.setString(1,username.getText());
            pst.setString(2,password.getText());
            ResultSet rs=pst.executeQuery();

            int count=0;
            while(rs.next()){
                count+=1;
            }
            if(count==1){
                PhoneFarmMain.changeScene("Homepage.fxml");

            } else{
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(("Username or password is not correct."));
                System.out.println("Username or password is not correct.");
                alert.show();
                progress.setVisible(false);
            }

        } catch (SQLException | IOException e1){
            e1.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    }
    @Override
    public void signUp(ActionEvent e1) throws IOException {
        PhoneFarmMain.changeScene("SignUp.fxml");

    }
    @Override
    public void signInAdmin(ActionEvent e2) throws IOException {
        PhoneFarmMain.changeScene("LoginAdmin.fxml");
    }
    @Override
    public void backButton(ActionEvent e3) throws  IOException{
        PhoneFarmMain.changeScene("Login.fxml");

    };


}
