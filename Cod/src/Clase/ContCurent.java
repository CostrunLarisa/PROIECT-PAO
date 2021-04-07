package Clase;

public class ContCurent extends Cont{
    ContCurent(String valuta,Double soldDisponibil,Double soldNeautorizat,Double sumaBlocata,Double dobanda,Card card){
        super(valuta,soldDisponibil,soldNeautorizat,sumaBlocata,dobanda,card);
    }
    public String extrageSuma(Integer suma){
        return "Aveti permisiunea de a extrage sume din acest cont doar printr-o cerere!";
    }
    public String adaugaSuma(Double Suma){
        this.setSoldDisponibil(this.getSoldDisponibil()+Suma);
        return String.format("Suma a fost adaugata cu succes!Noul sold disponibil este de %s" + (this.getSoldDisponibil()+Suma) + " %s" + this.getValuta());
    }
}
