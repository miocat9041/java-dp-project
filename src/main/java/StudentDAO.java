//因為實作資料庫操作介面，所以須捕捉sql的例外情況
import java.sql.SQLException;
import java.util.List;
//DAO（Data Access Object)
public interface StudentDAO {
    void addStudents(Students SingLestudent ) throws SQLException;
    void updateStudents(Students SingLestudent ) throws SQLException;
    void deleteStudents(int argNo ) throws SQLException;
    Students getStudentById(String argSid)throws SQLException;
    //查詢姓名可能會有零筆或多筆（重覆姓名的可能）所以要回傳資料集要用陣列表去讀取
    List<Students> getStudentByName(String argName)throws SQLException;

        
}
