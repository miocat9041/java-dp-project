import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //具體化studentDAOImpl來實現studentDao的介面
        StudentDAO studDAO = new StudentdaoImpl();
        boolean exit = false;//宣告一個flag來定義離開的方法
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
                    Students updateStud =MenuTool.getDetails2();
                    try{
                        studDAO.updateStudents(updateStud);
                        System.out.println("更新成功");
                    } catch (SQLException e){
                        System.out.println("更新學生資料時發生錯誤"+ e.getMessage());
                    }
                    break;
                case 3 :
                    int delNo= MenuTool.getNo();
                    try{
                        studDAO.deleteStudents(delNo);
                        System.out.println("資料刪除成功");//完全刪除，正式業務時比較少會完全刪除資料的，會用標記刪除方式刪除
                    }catch(SQLException e){
                        System.out.println("實際刪除學生資料時發生錯誤"+e.getMessage());
                    } 
                    break;
                case 4 :
                    String sidStr = MenuTool.getSid();
                    try{
                        Students stud = studDAO.getStudentById(sidStr);
                        //判斷stud資料集是否有回傳資料，因為系統可能抓回0筆資料,但在系統不算錯誤
                        if(stud!=null){
                            System.out.println("學號："+stud.getSid());
                            System.out.println("姓名："+stud.getName());
                            System.out.println("電郵："+stud.getEmail());
                            System.out.println("年齡："+stud.getAge());
                            System.out.println("主修："+stud.getMajor());
                        }else{
                            System.out.println("找不到對應的學生資料");
                        }
                    }catch (SQLException e){
                        System.out.println("查詢學生資料時發生錯誤"+e.getMessage());
                    }
                    break;
                case 5 :
                     String nameStr = MenuTool.getName();
                    try{
                        List <Students> students = studDAO.getStudentByName(nameStr);
                        //清單判斷是否為空值有固定用法『is Empty』
                    if( !students.isEmpty()){
                        for(Students studData :students){
                            System.out.println("學號："+studData.getSid());
                            System.out.println("姓名："+studData.getName());
                            System.out.println("電郵："+studData.getEmail());
                            System.out.println("年齡："+studData.getAge());
                            System.out.println("主修："+studData.getMajor());
                            System.out.println("=============================");
                        }
                    }else{
                        System.out.println("找不到對應的學生資料");
                    }
                }catch (SQLException e){
                    System.out.println("查詢學生資料時發生錯誤"+e.getMessage());
                }
                    break;
                case 6 :
                    String mySid = MenuTool.getSid();
                    try{
                        studDAO.updateStudentsFlag(mySid);
                        System.out.println("資料已標記為刪除狀態");
                    } catch( SQLException e) {
                        System.out.println("標記刪除學生資料時發生錯誤"+e.getMessage());
                    }
                    break;
                case 7 :
                     Students newScore =MenuTool.getScore();
                    try{
                        studDAO.addScore(newScore);
                        System.out.println("新增成功");
                    } catch (SQLException e){
                        System.out.println("新增成績資料時發生錯誤"+ e.getMessage());
                    }
                    break;
                case 8 :
                    Students UpdateScore =MenuTool.getScore2();
                try{
                    studDAO.updateScore(UpdateScore);
                    System.out.println("更新成功");
                } catch (SQLException e){
                    System.out.println("更新成績資料時發生錯誤"+ e.getMessage());
                }
                    break;
                case 9 :
                    String sidScore = MenuTool.getSid();
                    try{
                        List <Students> studScores = studDAO.getScoreBySid(sidScore);
                        if( !studScores.isEmpty()){
                            for(Students studscoreData : studScores){
                                System.out.println("學號："+studscoreData.getSid());
                                System.out.println("科目："+studscoreData.getSubject());
                                System.out.println("分數："+studscoreData.getScore());
                                System.out.println("=============================");
                            }
                        }else{
                            System.out.println("找不到對應的學生資料");
                        }
                    }catch (SQLException e){
                        System.out.println("查詢學生資料時發生錯誤"+e.getMessage());
                    }
                    break;
                case 10 :
                exit = true;
                System.out.println("感謝使用本功能");
                    break;
                default :
                    break;
            }
        }
        
    }
}
