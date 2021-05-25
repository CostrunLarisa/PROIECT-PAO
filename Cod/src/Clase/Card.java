package Clase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class Card {
    protected String emissionDate;
    protected String expirationDate;
    protected String name;
    protected String lastname;
    protected String cardNumber;
    protected Integer securityCode;
    protected Boolean contactelss;
    protected Double comision=0.0;
    protected Integer pin;
    protected String valute;
    protected Double currentValue=0.0;
    private List<Transaction> transactions=new ArrayList<Transaction>();
    Card(){
        SimpleDateFormat formatter2=new SimpleDateFormat("YYYY-MM-DD");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        LocalDate date = LocalDate.now();

        this.emissionDate=String.valueOf(date);
        LocalDate expr=date.plusYears(4);
        this.expirationDate=String.valueOf(expr);
        this.name="";
        this.lastname="";
        this.currentValue=0.0;
        Random random= new Random();
        this.securityCode=random.nextInt(900)+100;
    }
    Card(String name,String lastname,Integer pin,String valute,Double currentValue){
        this.name=name;
        this.lastname=lastname;
        this.pin=pin;
        SimpleDateFormat formatter=new SimpleDateFormat("YYYY-MM-DD");
        Date date=new Date(System.currentTimeMillis());;
        this.emissionDate=formatter.format(date);
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR,4);
        this.expirationDate=formatter.format(c.getTime());
        this.valute=valute;
        this.currentValue=currentValue;
    }
    public abstract String makePayment(String destinatar, Double suma,String details);
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
        List<Transaction> trans=new ArrayList<Transaction>(transactions);
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                String s1=t1.getData();
                String s2=t2.getData();
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
        });
        this.transactions=trans;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
