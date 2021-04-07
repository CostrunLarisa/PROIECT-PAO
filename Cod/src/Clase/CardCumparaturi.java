package Clase;

public class CardCumparaturi extends Card{
    protected
    Integer dobanda;
    Integer sumaMinimaPlata;
    Integer sumaLimitaAlimentare;
    CardCumparaturi(String nume,String prenume,Integer pin,String valuta) {
        super(nume,prenume,pin,valuta);
    }

    public Integer getDobanda() {
        return dobanda;
    }

    public void setDobanda(Integer dobanda) {
        this.dobanda = dobanda;
    }

    public Integer getSumaMinimaPlata() {
        return sumaMinimaPlata;
    }

    public void setSumaMinimaPlata(Integer sumaMinimaPlata) {
        this.sumaMinimaPlata = sumaMinimaPlata;
    }

    public Integer getSumaLimitaAlimentare() {
        return sumaLimitaAlimentare;
    }

    public void setSumaLimitaAlimentare(Integer sumaLimitaAlimentare) {
        this.sumaLimitaAlimentare = sumaLimitaAlimentare;
    }
    public String efectueazaTranzactie(String destinatar,Double suma){
        return "Nu se pot efectua tranzactii in cadrul cardului de cumparaturi";
    }
}
