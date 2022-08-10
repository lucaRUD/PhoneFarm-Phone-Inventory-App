package com.example.phonestore2.Homepage;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface HomepageInterface {
    @FXML
    void search_phone();

    @FXML
    void getSelected(MouseEvent event);


    void UpdateTable();
}
