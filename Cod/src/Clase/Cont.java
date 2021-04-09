package Clase;
import java.util.*;

public abstract class Cont{
    private String iban;
    private String valuta;
    private Double soldDisponibil=0.0;
    private Double soldContabil=0.0;
    private Double soldNeautorizat=0.0;
    private Double sumaBlocata=0.0;
    private Double dobanda;
    private Set<Card> carduri=new HashSet<Card>();
    private String tip;

    Cont(String valuta,Double soldDisponibil,Double soldNeautorizat,Double sumaBlocata,Double dobanda){

        Random rand=new Random();
        int int_random=rand.nextInt(10);
        int int_random2=rand.nextInt(10);
        int int_random3=10000+rand.nextInt(90000);
        int int_random4=10000+rand.nextInt(90000);
        int int_random5=10000+rand.nextInt(90000);
        int int_random6=10000+rand.nextInt(90000);

        this.iban=String.format("RO%s%s",int_random,int_random2)+String.format("CPAO%s%s%s%s",int_random3,int_random4,int_random5,int_random6);
        this.valuta=valuta;
        this.soldDisponibil=soldDisponibil;
        this.sumaBlocata=sumaBlocata;
        this.soldContabil=this.soldDisponibil+this.sumaBlocata;
        this.soldNeautorizat=soldNeautorizat;

    }
    public abstract String extrageSuma(Integer suma);
    public String adaugaSuma(Double Suma){
        this.setSoldDisponibil(this.getSoldDisponibil()+Suma);
        return String.format("Suma adaugata cu succes!Noul sold disponibil:%s" + this.getSoldDisponibil());
    }
    public void AdaugaCard(Card c){
        try{
            if(this.getCarduri().contains(c)==false){
                Set<Card> cr=this.getCarduri();
                cr.add(c);
                this.setCarduri(cr);
            }
            else throw new ArithmeticException("Acest card este deja adaugat!");
        }catch(Exception e){
                System.out.println(e);
        }
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public void setSoldDisponibil(Double soldDisponibil) {
        this.soldDisponibil = soldDisponibil;
    }

    public void setSoldContabil(Double soldContabil) {
        this.soldContabil = soldContabil;
    }


    public void setSoldNeautorizat(Double soldNeautorizat) {
        this.soldNeautorizat = soldNeautorizat;
    }

    public void setSumaBlocata(Double sumaBlocata) {
        this.sumaBlocata = sumaBlocata;
    }

    public void setDobanda(Double dobanda) {
        this.dobanda = dobanda;
    }

    public void setCarduri(Set<Card> carduri) {
        this.carduri = carduri;
    }


    public String getIban() {
        return iban;
    }

    public String getValuta() {
        return valuta;
    }

    public Double getSoldDisponibil() {
        return soldDisponibil;
    }

    public Double getSoldContabil() {
        return soldContabil;
    }


    public Double getSoldNeautorizat() {
        return soldNeautorizat;
    }

    public Double getSumaBlocata() {
        return sumaBlocata;
    }

    public Double getDobanda() {
        return dobanda;
    }

    public Set<Card> getCarduri() {
        return carduri;
    }

    /*
    @Override
    public String toString(){
        return String.format(iban,valuta,soldContabil,soldDisponibil,soldNeautorizat,limitaCont);
    }*/

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
