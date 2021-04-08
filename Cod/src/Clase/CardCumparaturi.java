package Clase;

import java.util.Random;

public class CardCumparaturi extends Card{
    protected
    Double dobanda;
    Double sumaMinimaPlata;
    Double sumaLimitaAlimentare;
    CardCumparaturi(String nume,String prenume,Integer pin,String valuta,Double sumaCurenta) {
        super(nume,prenume,pin,valuta,sumaCurenta);
        String cifre="0123456789";
        int size=15;
        Random random=new Random();
        Random random1=new Random();
        int nxt=random.nextInt(10);
        while(nxt==5 || nxt==4 || nxt==0)
        {
            nxt=random.nextInt(10);
        }
        this.numarCard=String.format("%s",nxt);
        for(int i=0;i<size;i++){
            int next=random.nextInt(cifre.length());
            char cifraString=cifre.charAt(next);
            this.numarCard=this.numarCard+cifraString;
        }
    }

    public Double getDobanda() {
        return dobanda;
    }

    public void setDobanda(Double dobanda) {
        this.dobanda = dobanda;
    }

    public Double getSumaMinimaPlata() {
        return sumaMinimaPlata;
    }

    public void setSumaMinimaPlata(Double sumaMinimaPlata) {
        this.sumaMinimaPlata = sumaMinimaPlata;
    }

    public Double getSumaLimitaAlimentare() {
        return sumaLimitaAlimentare;
    }

    public void setSumaLimitaAlimentare(Double sumaLimitaAlimentare) {
        this.sumaLimitaAlimentare = sumaLimitaAlimentare;
    }
    public Tranzactie efectueazaTranzactie(String destinatar,Double suma){
        Tranzactie tran=new Tranzactie();
        return tran;
    }
}
