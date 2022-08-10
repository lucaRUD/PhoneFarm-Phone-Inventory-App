package DBConnection;

import com.example.phonestore2.Homepage.phones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionPhones {
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/phonestore","root","1234");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
    public static ObservableList<phones>getdataphones(){
        Connection conn = ConnectDb();
        ObservableList<phones> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from phones");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new phones(Integer.parseInt(rs.getString("idPhones")),rs.getString("model"),rs.getString("cond"),rs.getString("accessories"),rs.getString("price")));
            }
        } catch (Exception e) {
        }
        return list;
    }


}
