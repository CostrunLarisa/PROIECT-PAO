package Clase;

public class ContEconomii extends Cont{
    final Integer limitaCont=900000;
    final Integer sumaMinima=500;
    ContEconomii(Double soldDisponibil,Double soldNeautorizat,Double sumaBlocata,Double dobanda,Card card){
        super("RON",soldDisponibil,soldNeautorizat,sumaBlocata,dobanda,card);

    }
    public String extrageSuma(Integer suma){
        return "Aveti permisiunea de a extrage sume din acest cont doar printr-o cerere!";
    }
    public String adaugaSuma(Double Suma){

        if(Suma< this.sumaMinima)return String.format("Puteti adauga o suma de minim %s" + this.sumaMinima + " %s" + this.getValuta() +" in acest cont.");
        if(Suma > this.getLimitaCont() || (Suma+this.getSoldDisponibil()>this.getLimitaCont()))
            return String.format("ACeasta suma depaseste limita contului de %s" + this.getLimitaCont()+" %s" +this.getValuta());
        this.setSoldDisponibil(this.getSoldDisponibil()+Suma);
        return String.format("Suma a fost adaugata cu succes!Noul sold disponibil este de %s" + (this.getSoldDisponibil()+Suma) + " %s" + this.getValuta());
    }


    public Integer getLimitaCont() {
        return limitaCont;
    }

    public Integer getSumaMinima() {
        return sumaMinima;
    }
}
