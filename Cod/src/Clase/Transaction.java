package Clase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Comparable<Transaction> {
    private String data;
    private Double sumaTranzac;
    private String detalii="";
    private boolean decontat;
    Transaction(Double sumaTranzac, String detalii, Boolean decontat){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        this.data=formatter.format(date);
        this.detalii=detalii;
        this.sumaTranzac=sumaTranzac;
        this.decontat=decontat;
    }

    Transaction() {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        this.data=formatter.format(date);
        this.decontat=true;
        this.sumaTranzac=0.0;
    }

    public String getDetalii() {
        return detalii;
    }
    public String getTranzactie(){
        return "Data:"+this.getData()+"\n"+"Suma:"+String.valueOf(this.getSumaTranzac())+"\n"+"Detalii:"+this.getDetalii()+"\n"+"Decontat:"+String.valueOf(this.decontat);
    }
    public String getData() {
        return data;
    }

    public Double getSumaTranzac() {
        return sumaTranzac;
    }
    @Override
    public int compareTo(Transaction t){
        return this.data.compareTo(t.getData());
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSumaTranzac(Double sumaTranzac) {
        this.sumaTranzac = sumaTranzac;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public boolean isDecontat() {
        return decontat;
    }

    public void setDecontat(boolean decontat) {
        this.decontat = decontat;
    }
}
