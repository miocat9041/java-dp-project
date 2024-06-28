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
      //step1:嘗試連線到資料庫，確認驅動程式是否存在
    static{
      try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
        System.out.println("DBConnErr01: 專案中的jdbc 驅動程式未尋獲，或著是不存在，請檢查相關路徑");
        //e.printStackTrace();
        }
    }
    /* 建構子練習：public DatabaseConnection (){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e){
            System.out.println("DBConnErr01: 專案中的jdbc 驅動程式未尋獲，或著是不存在，請檢查相關路徑");
            //e.printStackTrace();
            }
    } */
    //取得連線資源回傳
    public static Connection getConnection(){
        //讀取db.properties
        Properties prop = new Properties();
        try(InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)){
            if(inputStream == null){
                System.out.println("參數檔不存在，或是空的內容。");
                return null; //因為接收到空值要回傳空資源給主類別呼叫此方法時的空值處理。
            }
            //載入參數檔內容
            prop.load(inputStream);
        } catch (IOException e){
            System.out.println("資料庫參數讀取失敗");
            //列印錯誤堆疊
            e.printStackTrace();
            return null;//因為要回傳空資源給主類別呼叫此方法時的資源空值處理。
        }
        String url = prop.getProperty("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");

        //設定連線
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("資料庫已成功連線");
        } catch (SQLException e){
            System.out.println("資料庫連線失敗，請檢查連線資訊");
            //列印錯誤堆疊
            e.printStackTrace();
        }
        return conn;//把成功取得連線資源的變數“conn"回傳給呼叫方
        
    }

    }
