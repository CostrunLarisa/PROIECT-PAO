package Clase;

import javax.print.SimpleDoc;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BooleanSupplier;

public abstract class Card {
    protected
    String dataEmitere;
    String nume;
    String prenume;
    String numarCard;
    Integer codSecuritate;
    Boolean contactelss;
    Integer comision;
    Integer pin;
    String valuta;
    Card(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        this.dataEmitere=formatter.format(date);
        this.nume="";
        this.prenume="";
    }
    Card(String nume,String prenume,Integer pin,String valuta){
        this.nume=nume;
        this.prenume=prenume;
        this.pin=pin;
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());;
        this.dataEmitere=formatter.format(date);
        this.valuta=valuta;
    }
    public abstract String efectueazaTranzactie(String destinatar,Integer suma);
    public String getDataEmitere() {
        return dataEmitere;
    }

    public void setDataEmitere(String dataEmitere) {
        this.dataEmitere = dataEmitere;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNumarCard() {
        return numarCard;
    }

    public void setNumarCard(String numarCard) {
        this.numarCard = numarCard;
    }

    public Integer getCodSecuritate() {
        return codSecuritate;
    }

    public void setCodSecuritate(Integer codSecuritate) {
        this.codSecuritate = codSecuritate;
    }

    public Boolean getContactelss() {
        return contactelss;
    }

    public void setContactelss(Boolean contactelss) {
        this.contactelss = contactelss;
    }

    public Integer getComision() {
        return comision;
    }

    public void setComision(Integer comision) {
        this.comision = comision;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }
}
