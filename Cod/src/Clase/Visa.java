package Clase;

import java.util.Random;

public class Visa extends CardDebit{
    final double comisionPaypal=2.1;
    Visa(){
        super();
    }
    Visa(String nume,String prenume,Integer pin,String valuta,Double sumaCurenta){
        super(nume,prenume,pin,valuta,sumaCurenta);
        String cifre="0123456789";
        int size=15;
        Random random=new Random();
        this.numarCard="4";
        for(int i=0;i<size;i++){
            int next=random.nextInt(cifre.length());
            char cifraString=cifre.charAt(next);
            this.numarCard=this.numarCard+cifraString;
        }
    }
   /* public String efectueazaTranzactie(String destinatar,Double suma){
        return "";
    }*/
}
