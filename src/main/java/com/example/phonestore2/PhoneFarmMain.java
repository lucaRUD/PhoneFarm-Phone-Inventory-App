package com.example.phonestore2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.sql.*;


public class PhoneFarmMain extends Application {

    private static Stage stage;


    @Override
    public void start(Stage _stage) throws IOException {
        stage = _stage;
        FXMLLoader fxmlLoader = new FXMLLoader(PhoneFarmMain.class.getResource("FXML/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Phone Farm");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    public static void changeScene(String fxml) throws IOException {
//        System.out.println("FXML" + File.separator + fxml);
        Parent root = FXMLLoader.load(PhoneFarmMain.class.getResource("FXML/" + fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, SQLException {

        if(args.length>0&&args[0].equals("show_phones")) {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonestore", "root", "1234");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from phones");

            System.out.println("Phone List\nModel | Condition | Accessories | Price \n-----------------------------------------------");

            while (resultSet.next()) try {
                System.out.println(resultSet.getString("model") + " | " + resultSet.getString("cond") + " | " + resultSet.getString("accessories") + " | " + resultSet.getString("price"));
            } catch (Exception e1) {
                e1.printStackTrace();

            }
        }
        if(args.length>1&&args[0].equals("show_users")) {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonestore", "root", "1234");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from login");

            System.out.println("Phone List\nUsername | First Name | Last Name | Gender \n-----------------------------------------------");

            while (resultSet.next()) try {
                System.out.println(resultSet.getString("username") + " | " + resultSet.getString("firstname") + " | " + resultSet.getString("lastname") + " | " + resultSet.getString("gender"));
            } catch (Exception e1) {
                e1.printStackTrace();

            }
        }




        launch();
    }

}
