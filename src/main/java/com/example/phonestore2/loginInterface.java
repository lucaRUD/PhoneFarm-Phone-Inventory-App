package com.example.phonestore2;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

public interface loginInterface {
    void loginAction(ActionEvent e) throws SQLException, ClassNotFoundException;

    void signUp(ActionEvent e1) throws IOException;

    void signInAdmin(ActionEvent e2) throws IOException;

    void backButton(ActionEvent e3) throws  IOException;
}
