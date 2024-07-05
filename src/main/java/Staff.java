public class Staff {
    private int no;
    private String name;
    private String tid;
    private int age;
    private String tsubject;
    private int isDeleted;
    private int salary;
    private String mdept;
    private String type;

    public Staff (String argName, String argTid, int argAge,String argTsubject, int argSalary,String argMdept,String argType){
        this.name = argName;
        this.tid =argTid;
        this.age = argAge;
        this.tsubject = argTsubject;
        this.salary =argSalary;
        this.mdept = argMdept;
        this.type = argType;
        this.isDeleted = 0;
    }
    public int getNo(){
        return no;
    }
    public void setNo(int argNo){
        this.no =argNo;
    }
    public String getName(){
        return name;
    }
    public void setName(String argName){
        this.name =argName;
    }
    public String getTid(){
        return tid;
    }
    public void setTid(String argTid){
        this.tid =argTid;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int argAge){
        this.age =argAge;
    }
    public String getTsubject(){
        return tsubject;
    }
    public void setTsubject(String argTsubject){
        this.tsubject =argTsubject;
    }
    public int getSalary(){
        return salary;
    }
    public void setSalary(int argSalary){
        this.salary =argSalary;
    }
    public int getIsDeleted(){
        return isDeleted;
    }
    public void setIsDeleted(int argisDeleted){
        this.isDeleted =argisDeleted;
    }
    public String getMdept(){
        return mdept;
    }
    public void setMdept(String argMdept){
        this.mdept =argMdept;
    }
    public String getType(){
        return type;
    }
    public void setType(String argType){
        this.type =argType;
    }
    
}
