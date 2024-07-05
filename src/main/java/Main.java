import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        StaffDAO stafDAO = new StaffdaoImpl();
        boolean exit = false;
        while(!exit){
            MenuTool.displayMenu();
            int funcNum = MenuTool.funcNum();
            switch(funcNum){
                case 1:
                    Staff newStaf =MenuTool.getDetails();
                    try{
                        stafDAO.addStaff(newStaf);
                        System.out.println("新增成功");
                    } catch (SQLException e){
                        System.out.println("新增員工資料時發生錯誤"+ e.getMessage());
                    }
                    break;
                case 2 :
                    Staff updateStaf =MenuTool.getDetails2();
                    try{
                        stafDAO.updateStaff(updateStaf);
                        System.out.println("更新成功");
                    } catch (SQLException e){
                        System.out.println("更新員工資料時發生錯誤"+ e.getMessage());
                    }
                    break;
                case 3 :
                    String myTid = MenuTool.getTid();
                    try{
                        stafDAO.updateStaffFlag(myTid);
                        System.out.println("資料已標記為刪除狀態");
                    } catch( SQLException e) {
                        System.out.println("標記刪除員工資料時發生錯誤"+e.getMessage());
                    }
                    break;
                case 4 :
                    String tidStr = MenuTool.getTid();
                    try{
                        Staff staf= stafDAO.getStaffById(tidStr);
                        
                        if(staf!=null){
                            System.out.println("教職編號："+staf.getTid());
                            System.out.println("姓名："+staf.getName());
                            System.out.println("年齡："+staf.getAge());
                            System.out.println("指導科目："+staf.getTsubject());
                            System.out.println("薪水："+staf.getSalary());
                            System.out.println("管理職責："+staf.getMdept());
                            System.out.println("職稱："+staf.getType());
                        }else{
                            System.out.println("找不到對應的員工資料");
                        }
                    }catch (SQLException e){
                        System.out.println("查詢員工資料時發生錯誤"+e.getMessage());
                    }
                    break;
                case 5 :
                    QueryStaffFrame frame = new QueryStaffFrame(); 
                    frame.setVisible(true);
                    break;
                case 6 :
                    exit = true;
                    System.out.println("感謝使用本功能");
                        break;
                    default :
                        break;
            }
        }
        
    }
}
