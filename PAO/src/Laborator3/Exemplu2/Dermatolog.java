package Laborator3.Exemplu2;

public class Dermatolog extends Medic{
    public Dermatolog(String grad){
        super(grad);
        this.specializare="dermatologie";
    }

    @Override
    protected void scrieRetete() {
        System.out.println("Reteta la dermatolog");
    }

    protected static void planificaConsultatie(){
        System.out.println("Consultatie la dermatolog");
    }

    @Override
    protected String obtineInfo() {
        return super.obtineInfo()+this.specializare;
    }
}
