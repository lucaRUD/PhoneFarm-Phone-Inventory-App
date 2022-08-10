package DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
            Class.forName("com.myslq.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc.mysql://localhost:3306/phonestore" , "root","1234");
            System.out.println("Connected to Database");
    }

}
