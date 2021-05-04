package Laborator3.Exemplu2;

public class Program {
    public static void main(String[] args){
        Medic medic=new Medic("primar");
        System.out.println(medic.specializare);
        medic.scrieRetete();
        System.out.println(medic.obtineInfo());
        Medic medic1=new Dermatolog("primar");
        medic1.planificaConsultatie();
        System.out.println(medic1.obtineInfo());
        System.out.println("--------------");
        Dermatolog derm=new Dermatolog("rezident");
        derm.planificaConsultatie();
        System.out.println(derm.obtineInfo());
        System.out.println("--------------");
        Medic medic2=new Oftalmolog("Specialist");
        System.out.println(medic2.obtineInfo());
    }
}
