//import java.sql.*;(這個導入的可以一行帶入全部sql的工具，但會輸出比較費時費空間)
//常用sql工具如下：
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "db.properties";
    public static void main(String[] args) {
        //step1:嘗試連線到資料庫，確認驅動程式是否存在
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("DBConnErr01: 專案中的jdbc 驅動程式未尋獲，或著是不存在，請檢查相關路徑");
            //e.printStackTrace();
            return;//中斷程式讓使用者可重新連線
        }
        //讀取db.properties
        Properties prop = new Properties();
        try(InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)){
            if(inputStream == null){
                System.out.println("參數檔不存在，或是空的內容。");
                return;
            }
            prop.load(inputStream);
        } catch (IOException e){
            System.out.println("資料庫參數讀取失敗");
            //列印錯誤堆疊
            e.printStackTrace();
            return ;
        }
        String url = prop.getProperty("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");

        //設定連線
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("資料庫已成功連線");
            // 使用情境讀取全部並回傳
            Statement st = conn.createStatement();//建立語法物件給 st
            String sqlStr = " select *  from students";//設定查詢語法
            //使用st 來執行ql語法，並把資料及指定給rs
            ResultSet rs = st.executeQuery(sqlStr);
            //rs.next()會去讀取下一筆記錄指標，如果沒有下一筆就會是false
            while (rs.next()){
                String colName = rs.getString("name");
                String colSid = rs.getString("sid");
                String colEmail = rs.getString("email");
                System.out.printf("Name:%s    Sid: %s     Email:%s \n",colName,colSid,colEmail);

            }

            conn.close();
        } catch (SQLException e){
            System.out.println("資料庫連線失敗，請檢查連線資訊");
            //列印錯誤堆疊
            e.printStackTrace();
        }
        
        }
    }
