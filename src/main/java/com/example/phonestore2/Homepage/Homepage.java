package com.example.phonestore2.Homepage;



import DBConnection.DBConnectionPhones;
import com.example.phonestore2.Login;
import com.example.phonestore2.PhoneFarmMain;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Homepage implements Initializable, HomepageInterface {
    ObservableList<String> condition_boxlist = FXCollections.observableArrayList("Sealed", "Not sealed but new", "Good", "Average", "Worse than average", "Bad", "Defect");


    @FXML
    private JFXButton signout_btn;
    @FXML
    private TextField accessories_box;

    @FXML
    private TextField id_box;
    @FXML
    private Text welcome;

    @FXML
    private TextField condition_box;

    @FXML
    private TextField model_box;

    @FXML
    private TextField search_box;

    @FXML
    private TextField price_box;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_update;

    @FXML
    private TableColumn<phones, String> col_access;

    @FXML
    private TableColumn<phones, String> col_condition;

    @FXML
    private TableColumn<phones, String> col_model;

    @FXML
    private TableColumn<phones, String> col_price;
    @FXML
    private TableColumn<phones, Integer> col_id;

    @FXML
    private TableView<phones> table_phones;

    @FXML
    private ImageView dots_img;

    @FXML
    private Label dots_label;



    ObservableList<phones> listM;
    ObservableList<phones> dataList;

    @Override
    @FXML
    public void search_phone() {
        col_id.setCellValueFactory(new PropertyValueFactory<phones, Integer>("id"));
        col_model.setCellValueFactory(new PropertyValueFactory<phones, String>("model"));
        col_condition.setCellValueFactory(new PropertyValueFactory<phones, String>("cond"));
        col_access.setCellValueFactory(new PropertyValueFactory<phones, String>("accessories"));
        col_price.setCellValueFactory(new PropertyValueFactory<phones, String>("price"));

        dataList = DBConnectionPhones.getdataphones();
        table_phones.setItems(dataList);
        FilteredList<phones> filteredData = new FilteredList<>(dataList, b -> true);
        search_box.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(phones -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (phones.getModel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches model
                } else if (phones.getCond().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches condition
                } else if (phones.getAccessories().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches accessories
                } else // Does not match.
                    if (String.valueOf(phones.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches price
                } else return String.valueOf(phones.getId()).indexOf(lowerCaseFilter) != -1;// Filter matches price
            });
        });
        SortedList<phones> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_phones.comparatorProperty());
        table_phones.setItems(sortedData);
    }


    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;





    //////// methode select phones ///////
    @Override
    @FXML
    public void getSelected(MouseEvent event) {
        index = table_phones.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        id_box.setText(col_id.getCellData(index).toString());
        model_box.setText(col_model.getCellData(index));
        condition_box.setText(col_condition.getCellData(index));
        accessories_box.setText(col_access.getCellData(index));
        price_box.setText(col_price.getCellData(index));

    }





    @Override
    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<phones, Integer>("id"));
        col_model.setCellValueFactory(new PropertyValueFactory<phones, String>("model"));
        col_condition.setCellValueFactory(new PropertyValueFactory<phones, String>("cond"));
        col_access.setCellValueFactory(new PropertyValueFactory<phones, String>("accessories"));
        col_price.setCellValueFactory(new PropertyValueFactory<phones, String>("price"));

        listM = DBConnectionPhones.getdataphones();
        table_phones.setItems(listM);


    }

    public void setUsername(String user){
        this.welcome.setText("Welcome, "+user);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//      initialize();
        UpdateTable();
        setUsername(Login.getInstance().username());

    }
    public void signOutAction (ActionEvent event) throws IOException {
        PhoneFarmMain.changeScene("Login.fxml");
    }





    }

