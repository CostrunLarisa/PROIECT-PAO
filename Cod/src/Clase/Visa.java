package Clase;

import java.util.Random;

public class Visa extends CardDebit{
    private double comisionPaypal;
    Visa(){
        super();
    }
    Visa(String name,String lastname,Integer pin,String valute,Double sumaCurenta,Double comisionPaypal){
        super(name,lastname,pin,valute,sumaCurenta);
        this.comisionPaypal=comisionPaypal;
        String cifre="0123456789";
        int size=15;
        Random random=new Random();
        this.cardNumber="4";
        for(int i=0;i<size;i++){
            int next=random.nextInt(cifre.length());
            char cifraString=cifre.charAt(next);
            this.cardNumber=this.cardNumber+cifraString;
        }
    }
   /* public String efectueazaTranzactie(String destinatar,Double suma){
        return "";
    }*/

    public double getComisionPaypal() {
        return comisionPaypal;
    }

    public void setComisionPaypal(double comisionPaypal) {
        this.comisionPaypal = comisionPaypal;
    }
}
