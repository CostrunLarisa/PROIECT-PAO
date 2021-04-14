package Clase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Card {
    protected String emissionDate;
    protected String expirationDate;
    protected String name;
    protected String lastname;
    protected String cardNumber;
    protected Integer securityCode;
    protected Boolean contactelss;
    protected Double comision;
    protected Integer pin;
    protected String valute;
    protected Double currentValue=0.0;
    private List<Transaction> transactions=new ArrayList<Transaction>();
    Card(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        this.emissionDate=formatter.format(date);
        this.name="";
        this.lastname="";
        this.currentValue=0.0;
    }
    Card(String name,String lastname,Integer pin,String valute,Double currentValue){
        this.name=name;
        this.lastname=lastname;
        this.pin=pin;
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());;
        this.emissionDate=formatter.format(date);
        this.valute=valute;
        this.currentValue=currentValue;
    }
    public abstract Transaction makePayment(String destinatar, Double suma);
    public String getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(String emissionDate) {
        this.emissionDate = emissionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
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

    public String getvalute() {
        return valute;
    }

    public void setvalute(String valute) {
        this.valute = valute;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
