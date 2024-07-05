import java.util.Scanner;
public class MenuTool{
    private static Scanner sc= new Scanner(System.in);
    public static void displayMenu(){
        System.out.println("===========================");
        System.out.println("1.新增職員資料");
        System.out.println("2.修改職員資料");
        System.out.println("3.標記刪除職員資料");
        System.out.println("4.查詢職員資料(by_員工編號)");
        System.out.println("5.職員資料查詢（視窗版）");
        System.out.println("6.退出");
        System.out.println("===========================");
    }
    public static int funcNum(){
        System.out.println("請選擇1~6使用功能");
        return sc.nextInt();
    }
    public static Staff getDetails(){
        sc.nextLine();
        System.out.println("輸入教職員編號：");
        String tid =sc.nextLine();
        System.out.println("輸入姓名：");
        String name =sc.nextLine();
        System.out.println("輸入年齡：");
        int age =sc.nextInt();
        System.out.println("輸入薪水：");
        int salary =sc.nextInt();
        sc.nextLine();
        System.out.println("輸入指導科目：");
        String tsubject =sc.nextLine();
        System.out.println("輸入管理部門（若無管理請填無）：");
        String mdept =sc.nextLine();
        System.out.println("請輸入職員職稱：");
        String type =sc.nextLine();

        
        return new Staff(name,tid,age,tsubject,salary,mdept,type);
    }
    public static Staff getDetails2(){
        sc.nextLine();
        System.out.println("輸入要查詢的職員編號：");
        String tid =sc.nextLine();
        System.out.println("輸入欲更新姓名：");
        String name =sc.nextLine();
        System.out.println("輸入要更新的年齡：");
        int age =sc.nextInt();
        System.out.println("輸入要更新的薪水：");
        int salary =sc.nextInt();
        sc.nextLine();
        System.out.println("輸入要更新的指導科目：");
        String tsubject =sc.nextLine();
        System.out.println("輸入要更新的管理部門（若無管理請填無）：");
        String mdept =sc.nextLine();
        System.out.println("輸入要更新的職員職稱：");
        String type =sc.nextLine();
        return new Staff(name,tid,age,tsubject,salary,mdept,type);
    }
    public static String getTid(){
        sc.nextLine();
        System.out.println("輸入員工編號：");
        return sc.nextLine();
    }
    public static String getName(){
        sc.nextLine();
        System.out.println("輸入姓名：");
        return sc.nextLine();
    }
    public static int getNo(){
        System.out.println("輸入流水號：");
        return sc.nextInt();
    }
}