package Laborator3;

public class Vehicle {
    protected String model;
    protected int wheelsNumber;

    public Vehicle(){
        System.out.println("Vehicleno params");
        this.model="";
        this.wheelsNumber=4;
    }
    public Vehicle(String model,int wheelsNumber)
    {
        this.model=model;
        this.wheelsNumber=wheelsNumber;
        System.out.println("Vehicle cnstr with param");
    }
    protected void start(){
        System.out.println("Vehicle started");
    }
    protected void stop(){
        System.out.println("Vehicle stopped");
    }
    private void secret(){
        System.out.println("This cannot be accesed");
    }
    protected final void info(){
        System.out.println("Have a nice trip");
    }
}
