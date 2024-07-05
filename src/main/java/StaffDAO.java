import java.sql.SQLException;
import java.util.List;

public interface StaffDAO {
    
    void addStaff(Staff SingleStaff ) throws SQLException;
    void updateStaff(Staff SingleStaff ) throws SQLException;
    void updateStaffFlag(String ardTid) throws SQLException;//標記刪除資料用的語法
    Staff getStaffById(String argTid)throws SQLException;
        
}
