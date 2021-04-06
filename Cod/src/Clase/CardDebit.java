package Clase;

public class CardDebit extends Card{
    protected
    final Boolean contactelss=true;
    CardDebit(String nume,String prenume,Integer pin,String valuta) {
        super(nume,prenume,pin,valuta);
    }
    public String efectueazaTranzactie(String destinatar,Integer suma){
        return "ceva";
    }
}
