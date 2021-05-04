package Laborator3.Exemplu2;

public class Medic {
    protected String grad;
    protected String specializare;
    public Medic(String grad){
        this.grad=grad;
        this.specializare="generalist";
    }
    protected void scrieRetete(){
        System.out.println("Reteta a fost scrisa");
    }
    protected static void planificaConsultatie(){
        System.out.println("Consultatie la medic generalist");
    }
    protected String obtineInfo(){
        return "Medic"+grad;
    }
}
