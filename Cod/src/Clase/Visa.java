package Clase;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Visa extends CardDebit{
    private double comisionPaypal=0.2;
    Visa(){
        super();
        String cifre="0123456789";
        int size=15;
        Random random=new Random();
        this.cardNumber="5";
        for(int i=0;i<size;i++){
            int next=random.nextInt(cifre.length());
            char cifraString=cifre.charAt(next);
            this.cardNumber=this.cardNumber+cifraString;
        }
    }
    Visa(String name,String lastname,Integer pin,String valute,Double sumaCurenta,Double comisionPaypal){
        super(name,lastname,pin,valute,sumaCurenta);
        this.comisionPaypal=comisionPaypal;
        String cifre="0123456789";
        int size=15;
        Random random=new Random();
        this.cardNumber="5";
        for(int i=0;i<size;i++){
            int next=random.nextInt(cifre.length());
            char cifraString=cifre.charAt(next);
            this.cardNumber=this.cardNumber+cifraString;
        }
    }
    public String makePayment(String receiver, Double value,String details){
        if(value > this.getCurrentValue() ) return "Fonduri insuficiente!";
        if((value+value*this.getComisionPaypal()) > this.getCurrentValue()) return "Fonduri insuficiente!";
        else {
            this.setCurrentValue(this.getCurrentValue() - value + value * this.getComisionPaypal());
            Transaction tran = new Transaction();
            tran.setSumaTranzac(value);
            String str="Destinatar "+ receiver +" - "+details;
            tran.setDetalii(str);
            tran.setDecontat(false);
            List<String> lista= Arrays.asList(this.getCardNumber(),tran.getData(),String.valueOf(value),str,"false");
            MyFileWriter wr=new MyFileWriter("Transactions.csv",lista);
            List<Transaction> transac = this.getTransactions();
            transac.add(tran);
            this.setTransactions(transac);
            return "Tranzactie efectuata cu succes!";
        }
    }

    public double getComisionPaypal() {
        return this.comisionPaypal;
    }

    public void setComisionPaypal(double comisionPaypal) {
        this.comisionPaypal = comisionPaypal;
    }
}
