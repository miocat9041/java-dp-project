import java.util.Scanner;
public class MenuTool{
    private static Scanner sc= new Scanner(System.in);
    public static void displayMenu(){
        System.out.println("===========================");
        System.out.println("1.新增學生資料");
        System.out.println("2.修改學生資料");
        System.out.println("3.實際刪除學生資料");
        System.out.println("4.查詢學生資料(by_學號)");
        System.out.println("5.查詢學生資料(by_姓名)");
        System.out.println("6.標記刪除學生資料");
        System.out.println("7.新增學生成績");
        System.out.println("8.修改學生成績");
        System.out.println("9.查詢學生成績");
        System.out.println("10.退出");
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
    public static Students getDetails2(){
        sc.nextLine();//因為前一個畫面是輸入選單數字，所以要設一個緩衝區
        System.out.println("請輸入要查詢的學號：");
        String sid =sc.nextLine();
        System.out.println("輸入欲更新姓名：");
        String name =sc.nextLine();
        System.out.println("輸入欲更新信箱：");
        String email =sc.nextLine();
        System.out.println("輸入欲更新主修科目：");
        String major =sc.nextLine();
        System.out.println("輸入欲更新年齡：");
        int age =sc.nextInt();
        return new Students(name,sid,email,major,age);
    }
    public static String getSid(){
        sc.nextLine();
        System.out.println("輸入學號：");
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
    public static Students getScore(){
        sc.nextLine();
        System.out.println("請輸入要新增成績的學生學號：");
        String sid =sc.nextLine();
        System.out.println("請輸入要新增的科目：");
        String subject =sc.nextLine();
        System.out.println("請輸入該科目成績：");
        Double score =sc.nextDouble();
        return new Students(sid,subject,score);
        }
    public static Students getScore2(){
        sc.nextLine();
        System.out.println("請輸入要查詢的學號：");
        String sid =sc.nextLine();
        System.out.println("請輸入要修改的科目：");
        String subject =sc.nextLine();
        System.out.println("請輸入更新後的成績：");
        Double score =sc.nextDouble();
        return new Students(sid,subject,score);
}
}