package Clase;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardShopping extends Card{
    protected
    Double interest=0.02;
    Double minimumPaymentVal=200.0;
    Double minimumChargeVal=1200.0;
    CardShopping(){super();
        String cifre="0123456789";
        int size=15;
        Random random=new Random();
        Random random1=new Random();
        int nxt=random.nextInt(10);
        while(nxt==5 || nxt==4 || nxt==0)
        {
            nxt=random.nextInt(10);
        }
        this.cardNumber=String.format("%s",nxt);
        for(int i=0;i<size;i++){
            int next=random.nextInt(cifre.length());
            char cifraString=cifre.charAt(next);
            this.cardNumber=this.cardNumber+cifraString;
        }}
    CardShopping(String name, String lastname, Integer pin, String valute, Double currentValue, Double interest, Double minimumPaymentVal, Double minimumChargeVal) {
        super(name,lastname,pin,valute,currentValue);
        this.interest=interest;
        this.minimumChargeVal=minimumChargeVal;
        this.minimumPaymentVal=minimumPaymentVal;
        String cifre="0123456789";
        int size=15;
        Random random=new Random();
        Random random1=new Random();
        int nxt=random.nextInt(10);
        while(nxt==5 || nxt==4 || nxt==0)
        {
            nxt=random.nextInt(10);
        }
        this.cardNumber=String.format("%s",nxt);
        for(int i=0;i<size;i++){
            int next=random.nextInt(cifre.length());
            char cifraString=cifre.charAt(next);
            this.cardNumber=this.cardNumber+cifraString;
        }
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getMinimumPaymentVal() {
        return minimumPaymentVal;
    }

    public void setMinimumPaymentVal(Double minimumPaymentVal) {
        this.minimumPaymentVal = minimumPaymentVal;
    }

    public Double getMinimumChargeVal() {
        return minimumChargeVal;
    }

    public void setMinimumChargeVal(Double minimumChargeVal) {
        this.minimumChargeVal = minimumChargeVal;
    }
    public String makePayment(String receiver, Double value,String details){
        if(value > this.getCurrentValue() ) return "Fonduri insuficiente!";
        if(value < this.getMinimumChargeVal()) return String.format("Suma minima pentru plati: %s",this.getMinimumChargeVal());
        else{
            Transaction tran=new Transaction();
            tran.setSumaTranzac(value);
            String str="Destinatar "+ receiver +" - " +details;
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
