//因為實作資料庫操作介面，所以須捕捉sql的例外情況
import java.sql.SQLException;
import java.util.List;
//DAO（Data Access Object)
public interface StudentDAO {
    //宣告各個資料庫操作的方法，並如果遇到sqlexception的錯誤，要往上一層呼叫方丟出
    void addStudents(Students SingLestudent ) throws SQLException;
    void addScore(Students StudentScore ) throws SQLException;
    void updateStudents(Students SingLestudent ) throws SQLException;
    void updateScore(Students StudentScore ) throws SQLException;
    void updateStudentsFlag(String ardSid) throws SQLException;//標記刪除資料用的語法
    void deleteStudents(int argNo ) throws SQLException;
    Students getStudentById(String argSid)throws SQLException;
    //查詢姓名可能會有零筆或多筆（重覆姓名的可能）所以要回傳資料集要用陣列表去讀取
    List<Students> getStudentByName(String argName)throws SQLException;
    List<Students> getScoreBySid(String argSid) throws SQLException;
        
}
