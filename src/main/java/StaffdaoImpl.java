import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffdaoImpl implements StaffDAO{
    private Connection conn;

    //建構子
    public StaffdaoImpl(){
        //取得資料庫連線
        this.conn = DatabaseConnection.getConnection();
    }
    //新增資料
    @Override
    public void addStaff(Staff staf) throws SQLException{
        //有幾個數值要回傳後面預載就要有幾個問號
        String sqlStr ="insert into staff( name, tid, age, tsubject, salary, mdept, type)values (?,?,?,?,?,?,?);";
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setString(1, staf.getName());
            st.setString(2, staf.getTid());
            st.setInt(3, staf.getAge());
            st.setString(4, staf.getTsubject());
            st.setInt(5, staf.getSalary());
            st.setString(6, staf.getMdept());
            st.setString(7, staf.getType());

            st.executeUpdate();
        }
    }
    //更新資料
    @Override
    public void updateStaff(Staff staf)throws SQLException{
        String sqlStr ="update staff set name =?, age=?, tsubject=?, salary=?, mdept=?, type=? where tid=?;";
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setString(1, staf.getName());
            st.setInt(2, staf.getAge());
            st.setString(3, staf.getTsubject());
            st.setInt(4, staf.getSalary());
            st.setString(5, staf.getMdept());
            st.setString(6, staf.getType());
            st.setString(7, staf.getTid());

            st.executeUpdate();
        }
    }
    //標記刪除資料用
    @Override
    public void updateStaffFlag(String argTid)throws SQLException{
        String sqlStr ="update staff set is_deleted=?  where tid=?;";
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setInt(1, 1);
            st.setString(2,argTid);
            st.executeUpdate();
        }
    }
    //利用職工編號查詢資料
@Override
    public Staff getStaffById (String argTid)throws SQLException{
    String sqlStr ="select * from staff where tid=? and is_deleted=0";
    try(PreparedStatement st = conn.prepareStatement(sqlStr)){
        st.setString(1, argTid);
        ResultSet rs =st.executeQuery();
        if (rs.next()) {
        String tmpName = rs.getString("name");
        String tampTid = rs.getString("tid");
        int tmpAge = rs.getInt("age");
        String tmpTsubject =rs.getString("tsubject");
        int tmpSalary =rs.getInt("salary");
        String tmpMdept =rs.getString("mdept");
        String tmpType =rs.getString("type");
        return new Staff(tmpName,tampTid,tmpAge,tmpTsubject,tmpSalary,tmpMdept,tmpType);
                        }
                }
        return null;
     }
}
