package Clase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Transaction  {
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
        String message;
        if (this.decontat == true) {
            message = "da";
        }
        else{
            message="nu";
        }
        return "Data:"+this.getData()+"\n"+"Suma:"+String.valueOf(this.getSumaTranzac())+"\n"+"Detalii:"+this.getDetalii()+"\n"+"Decontat:"+message;
    }
    public String getData() {
        return data;
    }

    public Double getSumaTranzac() {
        return sumaTranzac;
    }
/*
    public int compare(Transaction t,Transaction t1){
        String s1=t1.getData();
        String s2=t.getData();
        Date d1=new Date();
        Date d2=new Date();
        try {
             d1=new SimpleDateFormat("MM/dd/yyyy").parse(s1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            d2=new SimpleDateFormat("MM/dd/yyyy").parse(s2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(d2+"-"+d1);
        return d1.compareTo(d2);
    }
*/
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
