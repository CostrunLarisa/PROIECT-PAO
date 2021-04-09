package Clase;

public class ContEconomii extends Cont{
    private Integer limitaCont;
    final Integer sumaMinima=500;
    ContEconomii(String valuta,Double soldDisponibil,Double soldNeautorizat,Double sumaBlocata,Double dobanda,Integer limitaCont){
        super(valuta,soldDisponibil,soldNeautorizat,sumaBlocata,dobanda);
        this.setTip("Economii");
        this.limitaCont=limitaCont;

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

    public void setLimitaCont(Integer limitaCont) {
        this.limitaCont = limitaCont;
    }
}
