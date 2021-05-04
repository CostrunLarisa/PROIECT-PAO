package Clase;

import java.util.Arrays;
import java.util.List;

public class CardDebit extends Card{
    protected
    final Boolean contactelss=true;
    CardDebit(){super();}
    CardDebit(String name,String lastname,Integer pin,String valute,Double currentValue) {
        super(name,lastname,pin,valute,currentValue);
    }

    public String makePayment(String receiver, Double value,String details){
        if(value > this.getCurrentValue()) return "Fonduri insuficiente!";
        else{
            this.setCurrentValue(this.getCurrentValue()-value);
            Transaction tran=new Transaction();
            tran.setSumaTranzac(value);
            String str="Destinatar "+ receiver +" - "+details;
            tran.setDetalii(str);
            tran.setDecontat(false);
            List<String> lista= Arrays.asList(this.getCardNumber(),tran.getData(),String.valueOf(value),str,"false");
            MyFileWriter wr=new MyFileWriter("Transactions.csv",lista);
            List<Transaction>transac=this.getTransactions();
            transac.add(tran);
            this.setTransactions(transac);
            return "Tranzactie efectuata cu succes!";
        }
    }
}
