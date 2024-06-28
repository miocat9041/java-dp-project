import java.sql.*;
//Arraylist是一個動態陣列要實現list所以兩個都要導入
import java.util.ArrayList;
import java.util.List;

public class StudentdaoImpl implements StudentDAO{
    private Connection conn;

    //建構子
    public StudentdaoImpl(){
        //取得資料庫連線
        this.conn = DatabaseConnection.getConnection();
    }
    @Override
    public void addStudents(Students stud) throws SQLDataException{
        //有幾個數值要回傳後面預載就要有幾個問號
        String sqlStr ="insert into Students( name, sid, email,age,major)values (?,?,?,?,?);";
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setString(0, sqlStr);
        }
    }
    
}
