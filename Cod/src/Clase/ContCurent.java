package Clase;

public class ContCurent extends Cont{
    ContCurent(String iban,String valuta,Double soldDisponibil,Integer soldContabil,Integer soldNeautorizat,Integer sumaBlocata,Integer dobanda,Card card){
        super(iban,valuta,soldDisponibil,soldContabil,soldNeautorizat,sumaBlocata,dobanda,card);
    }
    public String extrageSuma(Integer suma){
        return "Aveti permisiunea de a extrage sume din acest cont doar printr-o cerere!";
    }
    public String adaugaSuma(Double Suma){
        this.setSoldDisponibil(this.getSoldDisponibil()+Suma);
        return String.format("Suma a fost adaugata cu succes!Noul sold disponibil este de %s" + (this.getSoldDisponibil()+Suma) + " %s" + this.getValuta());
    }
}
