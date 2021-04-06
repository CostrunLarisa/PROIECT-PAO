package Clase;

public class MasterCard extends Card{
    MasterCard(String nume,String prenume,Integer pin,String valuta){
        super(nume,prenume,pin,valuta);
    }
    public String efectueazaTranzactie(String destinatar,Integer suma){
        return "ceva";
    }
}
