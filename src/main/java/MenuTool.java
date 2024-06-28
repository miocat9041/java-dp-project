import java.util.Scanner;
public class MenuTool{
    private static Scanner sc= new Scanner(System.in);
    public static void displayMenu(){
        System.out.println("===========================");
        System.out.println("1.新增學生資料");
        System.out.println("2.修改學生資料");
        System.out.println("3.標記刪除學生資料");
        System.out.println("4.查詢學生資料(by_學號)");
        System.out.println("5.查詢學生資料(by_姓名)");
        System.out.println("6.退出");
        System.out.println("===========================");
    }
    public static int funcNum(){
        System.out.println("請選擇1~6使用功能");
        return sc.nextInt();
    }
    public static Students getDetails(){
        sc.nextLine();//因為前一個畫面是輸入選單數字，所以要設一個緩衝區
        System.out.println("輸入學號：");
        String sid =sc.nextLine();
        System.out.println("輸入姓名：");
        String name =sc.nextLine();
        System.out.println("輸入信箱：");
        String email =sc.nextLine();
        System.out.println("輸入主修科目：");
        String major =sc.nextLine();
        System.out.println("輸入年齡：");
        int age =sc.nextInt();
        return new Students(name,sid,email,major,age);
    }
    public static String getSid(){
        System.out.println("輸入學號：");
        return sc.nextLine();
    }
    public static String getName(){
        System.out.println("輸入姓名：");
        return sc.nextLine();
    }
    public static int getNo(){
        System.out.println("輸入流水號：");
        return sc.nextInt();
    }
}
