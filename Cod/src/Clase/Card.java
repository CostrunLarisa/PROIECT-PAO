package Clase;

import javax.print.SimpleDoc;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BooleanSupplier;

public abstract class Card {
    protected String dataEmitere;
    protected String nume;
    protected String prenume;
    protected String numarCard;
    protected Integer codSecuritate;
    protected Boolean contactelss;
    protected Double comision;
    protected Integer pin;
    protected String valuta;
    protected Double sumaCurenta=0.0;
    private List<Tranzactie> tranzactii=new ArrayList<Tranzactie>();
    Card(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        this.dataEmitere=formatter.format(date);
        this.nume="";
        this.prenume="";
        this.sumaCurenta=0.0;
    }
    Card(String nume,String prenume,Integer pin,String valuta,Double sumaCurenta){
        this.nume=nume;
        this.prenume=prenume;
        this.pin=pin;
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());;
        this.dataEmitere=formatter.format(date);
        this.valuta=valuta;
        this.sumaCurenta=sumaCurenta;
    }
    public abstract Tranzactie efectueazaTranzactie(String destinatar,Double suma);
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

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
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

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    public Double getSumaCurenta() {
        return sumaCurenta;
    }

    public void setSumaCurenta(Double sumaCurenta) {
        this.sumaCurenta = sumaCurenta;
    }

    public void setTranzactii(List<Tranzactie> tranzactii) {
        this.tranzactii = tranzactii;
    }

}
