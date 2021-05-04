package Laborator3;

public class Bike extends Vehicle{
    private String type;
    public Bike(){
        System.out.println("Bike constr noparam");
    }
    public Bike (String type,String model,int wheelsNumber)
    {
        super(model,wheelsNumber);
        this.type=type;
        System.out.println("Bike constr with params");

    }
    public Bike (String type,String model)
    {
        super(model,2);
        this.type=type;
        System.out.println("Bike constr with 2 params");

    }

    public String getType() {
        return type;
    }
}
