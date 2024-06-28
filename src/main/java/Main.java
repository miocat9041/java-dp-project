import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //具體化studentDAOImpl來實現studentDao的介面
        StudentDAO studDAO = new StudentdaoImpl();
        boolean exit = false;
        while(!exit){
            MenuTool.displayMenu();
            int funcNum = MenuTool.funcNum();
            switch(funcNum){
                case 1:
                    Students newStud =MenuTool.getDetails();
                    try{
                        studDAO.addStudents(newStud);
                        System.out.println("新增成功");
                    } catch (SQLException e){
                        System.out.println("新增學生資料時發生錯誤"+ e.getMessage());
                    }
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    break;
                case 5 :
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
