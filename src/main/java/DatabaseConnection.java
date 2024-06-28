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
            //載入參數檔內容
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
                System.out.printf("Name:%20s    Sid: %10s     Email:%s \n",colName,colSid,colEmail);

            }
            //Step2：條件查詢 ,查詢name這個欄位有包含Joanne的資料
            sqlStr = "select * from students where name like '%Joanne%';";
            rs = st.executeQuery(sqlStr);
            System.out.println("查詢name這個欄位,有包含Joanne內容如下:");
            System.out.println("=====================================");
            while (rs.next()){
                String colName = rs.getString("name");
                String colSid = rs.getString("sid");
                String colEmail = rs.getString("email");
                System.out.printf("Name:%s    Sid: %s     Email:%s \n",colName,colSid,colEmail);

            }
            //複數查詢，查詢name有包含的資料，email欄位有包含ssss開頭的 
            sqlStr = "select * from students where name like '%Joanne%' and email like 'ssss%';";
            rs = st.executeQuery(sqlStr);
            System.out.println("查詢name這個欄位,有包含Joanne,email欄位有包含sss開頭的內容如下:");
            System.out.println("=====================================");
            while (rs.next()){
                String colName = rs.getString("name");
                String colSid = rs.getString("sid");
                String colEmail = rs.getString("email");
                System.out.printf("Name:%s    Sid: %s     Email:%s \n",colName,colSid,colEmail);

            }
            System.out.println("=====================================");

            /* step3:新增紀錄
            sqlStr ="insert into students(name, email , sid) values('Marco','A09@myschool.edu','A09'),('Timmy','A10@myschool.edu','A10');";
            //只有查詢資料語法可以用executeQuery，其他如新增、更新、刪除，對應使用都是executeUpedate
            int rowsInserted = st.executeUpdate(sqlStr);
            if(rowsInserted > 0){
                System.out.println("已成功新增記錄");
            }

            System.out.println("=====================================");*/

            //step4:更新資料皆需下“where"的條件句，否則可能會改到整張表單
            sqlStr ="update students set name = 'Olivia', sid ='A11' where name ='Timmy';";
            int updateStatus = st.executeUpdate(sqlStr);
            if(updateStatus > 0){
                System.out.println("已成功更新記錄");
            }

            /*step5:刪除紀錄(但通常不會去設刪除因為刪了救不回來，通常會把物件設成“待刪除狀態就好”)
            sqlStr ="delete from students where name in('Olivia');";
            updateStatus = st.executeUpdate(sqlStr);
            if(updateStatus > 0){
            System.out.println("已成功刪除記錄");
            } */


            conn.close();
        } catch (SQLException e){
            System.out.println("資料庫連線失敗，請檢查連線資訊");
            //列印錯誤堆疊
            e.printStackTrace();
        }
        
        }
    }
