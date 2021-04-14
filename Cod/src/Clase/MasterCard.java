package Clase;

import java.util.Random;

public class MasterCard extends CardDebit{
    MasterCard(String name,String lastname,Integer pin,String valute,Double sumaCurenta){
        super(name,lastname,pin,valute,sumaCurenta);

        //Generating an unique card number which starts with number 5, specific to Mastercard
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

    public MasterCard() {
        super();
    }
    /*
    public String efectueazaTranzactie(String destinatar,Double suma){
        return "ceva";
    }*/
}
