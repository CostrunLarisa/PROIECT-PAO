package Clase;

public class CurrentAccount extends Account {
    CurrentAccount(String valute, Double availableDeposit, Double unauthorizedDeposit, Double blockedValue, Double interest,String name, String lastname){
        super(valute,availableDeposit,unauthorizedDeposit,blockedValue,interest,name,lastname);
        this.setType("Standard");
    }
    public String withdrawMoney(Integer suma){
        return "Aveti permisiunea de a extrage sume din acest cont doar printr-o cerere!";
    }
}
