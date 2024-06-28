public class Students {
    private int no;
    private String name;
    private String sid;
    private String email;
    private int age;
    private String major;
    private boolean isDeleted;
    //建構子
    public Students( String argName, String argSid, String argEmail,String argMajor, int argAge){
        this.name = argName;
        this.sid =argSid;
        this.email =argEmail;
        this.age =argAge;
        this.major = argMajor;
        this.isDeleted = false;

    }
    //設定其他屬性的get&set
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
    public String getSid(){
        return sid;
    }
    public void setSid(String argSid){
        this.sid =argSid;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String argEmail){
        this.email =argEmail;
    }
    public String getMajor(){
        return major;
    }
    public void setMajor(String argMajor){
        this.major =argMajor;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int argAge){
        this.age =argAge;
    }
    public boolean getIsDeleted(){
        return isDeleted;
    }
    public void setIsDeleted(boolean argisDeleted){
        this.isDeleted =argisDeleted;
    }
}

