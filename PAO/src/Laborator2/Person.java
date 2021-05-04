package Laborator2;

public class Person {
    public String name;
    public String job;
    private String religion;
    int age;

    void initialize(){
        religion="orthodox";
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
    protected void showInformation(){
        System.out.println("jjjj");
    }
}
