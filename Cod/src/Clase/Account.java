package Clase;
import java.util.*;

public abstract class Account {
    private String iban;
    private String valute;
    private Double availableDeposit=0.0;
    private Double accountableDeposit=0.0;
    private Double unauthorizedDeposit=0.0;
    private Double blockedValue=0.0;
    private Double interest;
    private Set<Card> cards=new HashSet<Card>();
    private String type;
    private String name;
    private String lastname;
    Account(String valute, Double availableDeposit, Double unauthorizedDeposit, Double blockedValue, Double interest, String name, String lastname){

        Random rand=new Random();
        int int_random=rand.nextInt(10);
        int int_random2=rand.nextInt(10);
        int int_random3=10000+rand.nextInt(90000);
        int int_random4=10000+rand.nextInt(90000);
        int int_random5=10000+rand.nextInt(90000);
        int int_random6=10000+rand.nextInt(90000);

        this.iban=String.format("RO%s%s",int_random,int_random2)+String.format("CPAO%s%s%s%s",int_random3,int_random4,int_random5,int_random6);
        this.valute=valute;
        this.availableDeposit=availableDeposit;
        this.blockedValue=blockedValue;
        this.accountableDeposit=this.availableDeposit+this.blockedValue;
        this.unauthorizedDeposit=unauthorizedDeposit;
        this.name=name;
        this.lastname=lastname;
    }
    public abstract String withdrawMoney(Integer suma);
    public String addMoney(Double Suma){
        this.setAvailableDeposit(this.getAvailableDeposit()+Suma);
        return String.format("Value succesfully added!New available deposit:%s" + this.getAvailableDeposit());
    }
    public void addCard(Card c){
        try{
            if(this.getCards().contains(c)==false){
                Set<Card> cr=this.getCards();
                cr.add(c);
                this.setCards(cr);
            }
            else throw new ArithmeticException("This card is already added!");
        }catch(Exception e){
                System.out.println(e);
        }
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setvalute(String valute) {
        this.valute = valute;
    }

    public void setAvailableDeposit(Double availableDeposit) {
        this.availableDeposit = availableDeposit;
    }

    public void setAccountableDeposit(Double accountableDeposit) {
        this.accountableDeposit = accountableDeposit;
    }


    public void setUnauthorizedDeposit(Double unauthorizedDeposit) {
        this.unauthorizedDeposit = unauthorizedDeposit;
    }

    public void setBlockedValue(Double blockedValue) {
        this.blockedValue = blockedValue;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }


    public String getIban() {
        return iban;
    }

    public String getValute() {
        return valute;
    }

    public Double getAvailableDeposit() {
        return availableDeposit;
    }

    public Double getAccountableDeposit() {
        return accountableDeposit;
    }


    public Double getUnauthorizedDeposit() {
        return unauthorizedDeposit;
    }

    public Double getBlockedValue() {
        return blockedValue;
    }

    public Double getInterest() {
        return interest;
    }

    public Set<Card> getCards() {
        return cards;
    }

    /*
    @Override
    public String toString(){
        return String.format(iban,valute,accountableDeposit,availableDeposit,unauthorizedDeposit,limitaCont);
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
