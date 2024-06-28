import java.sql.*;
import java.util.List;

public class Main {
    private static Connection conn;
    public static void main(String[] args) {
        conn= DatabaseConnection.getConnection();

        
    }
}
