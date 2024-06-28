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
    public void addStudents(Students stud) throws SQLException{
        //有幾個數值要回傳後面預載就要有幾個問號
        String sqlStr ="insert into Students( name, sid, email,age,major)values (?,?,?,?,?);";
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setString(1, stud.getName());
            st.setString(2, stud.getSid());
            st.setString(3, stud.getEmail());
            st.setInt(4, stud.getAge());
            st.setString(5, stud.getMajor());
            //insert,update,delete都用excuteUpdate
            st.executeUpdate();
        }
    }
    @Override
    public void updateStudents(Students stud)throws SQLException{
        String sqlStr ="update Students set name =?, sid=?,email=?,age=?,major=? where no=?;";
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setString(1, stud.getName());
            st.setString(2, stud.getSid());
            st.setString(3, stud.getEmail());
            st.setInt(4, stud.getAge());
            st.setString(5, stud.getMajor());
            st.setInt(6, stud.getNo());
            //insert,update,delete都用excuteUpdate
            st.executeUpdate();
        }
    }
    @Override
    public void deleteStudents(int argNo) throws SQLException{
        String sqlStr = "delete from students where no=?;";
        try (PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setInt(1, argNo);
            //insert,update,delete都用excuteUpdate
            st.executeUpdate();
        }
    }
    //利用學號查詢資料
@Override
public Students getStudentById(String argSid)throws SQLException{
    String sqlStr ="select * from students where sid=? and is_deleted=FALSE";
    //上面and 要記得空格
    try(PreparedStatement st = conn.prepareStatement(sqlStr)){
        st.setString(1, argSid);
        ResultSet rs =st.executeQuery();
        if(rs.next()){
            //Students stud = new Students(sqlStr, argSid, argSid, 0, sqlStr);
            //return stud;
            //上面那兩列可替代下面這段，但下面這段比較完整
            String tmpName = rs.getString("name");
            String tampSid = rs.getString("sid");
            String tmpEmail =rs.getString("email");
            String tmpMajor =rs.getString("major");
            int tmpAge = rs.getInt("age");
            return new Students(tmpName,tampSid,tmpEmail,tmpMajor,tmpAge);
            }
        }
        return null;//確保如果上面都沒傳到正確數值可以回傳一個空值提醒
    }
    //利用學生姓名查詢資料
    @Override
    public List<Students> getStudentByName(String argName)throws SQLException{
        String sqlStr ="select * from students where name=? and is_deleted=FALSE";
        //因為此方法要回傳list 物件，所以在這裡先宣告一個
        List<Students> students = new ArrayList<>();
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setString(1, argName);
            ResultSet rs =st.executeQuery();
            while (rs.next()) {
                //利用students arrarylist的add方法，把資料集集成新的學生物件，指定給students arrarylist作為新的元素。
                students.add(new Students(rs.getString("name"), 
                            rs.getString("sid"),
                            rs.getString("email"),
                            rs.getString("major"),
                            rs.getInt("age")
                            ));
                            }
                        }
                        return null;
                    }
                }
