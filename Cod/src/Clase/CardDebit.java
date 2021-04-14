package Clase;

public class CardDebit extends Card{
    protected
    final Boolean contactelss=true;
    CardDebit(){super();}
    CardDebit(String name,String lastname,Integer pin,String valute,Double currentValue) {
        super(name,lastname,pin,valute,currentValue);
    }

    public Transaction makePayment(String receiver, Double value){
        Transaction tran=new Transaction();
        tran.setSumaTranzac(value);
        String str=String.format("Destinatar:"+ receiver +", suma tranzactionata:%s",value)+"\n"+"Status tranzactie:aprobata.";

        return tran;
    }
}
