package Clase;

public class CardDebit extends Card{
    protected
    final Boolean contactelss=true;
    CardDebit(){super();}
    CardDebit(String nume,String prenume,Integer pin,String valuta,Double sumaCurenta) {
        super(nume,prenume,pin,valuta,sumaCurenta);
    }

    public Tranzactie efectueazaTranzactie(String destinatar,Double suma){
        Tranzactie tran=new Tranzactie();
        tran.setSumaTranzac(suma);
        String str=String.format("Destinatar:"+destinatar +", suma tranzactionata:%s",suma)+"\n"+"Status tranzactie:aprobata.";

        return tran;
    }
}
