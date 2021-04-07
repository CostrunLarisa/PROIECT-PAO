package Clase;

public class CardDebit extends Card{
    protected
    final Boolean contactelss=true;
    CardDebit(String nume,String prenume,Integer pin,String valuta) {
        super(nume,prenume,pin,valuta);
    }

    public String efectueazaTranzactie(String destinatar,Double suma){
        Tranzactie tran=new Tranzactie();
        tran.setSumaTranzac(suma);
        String str=String.format("Destinatar:"+destinatar +", suma tranzactionata:%s",suma)+"\n"+"Status tranzactie:aprobata.";

        return str;
    }
}
