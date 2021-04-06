package Clase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tranzactie implements Comparable<Tranzactie> {
    private String data;
    private Integer sumaTranzac;
    private String detalii="";
    private boolean decontat;
    Tranzactie(String data,Integer sumaTranzac,String detalii,Boolean decontat){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());;
        this.data=formatter.format(date);
        this.detalii=detalii;
        this.sumaTranzac=sumaTranzac;
        this.decontat=decontat;
    }

    public String getDetalii() {
        return detalii;
    }
    public String getTranzactie(){
        return "Data:"+this.getData()+"\n"+"Suma:"+String.valueOf(this.getSumaTranzac())+"\n"+this.getDetalii();
    }
    public String getData() {
        return data;
    }

    public Integer getSumaTranzac() {
        return sumaTranzac;
    }
    @Override
    public int compareTo(Tranzactie t){
        return this.data.compareTo(t.getData());
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSumaTranzac(Integer sumaTranzac) {
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
