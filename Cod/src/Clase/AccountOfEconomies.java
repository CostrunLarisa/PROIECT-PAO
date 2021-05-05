package Clase;

public class AccountOfEconomies extends Account {
    private Integer accountLimit=10000000;
    final Integer minValue=500;
    AccountOfEconomies(String valute, Double availableDeposit, Double unauthorizedDeposit, Double blockedValue, Double interest, Integer accountLimit, String name, String lastname){
        super(valute,availableDeposit,unauthorizedDeposit,blockedValue,interest,name,lastname);
        this.setType("Economies");
        this.accountLimit=accountLimit;
    }
    AccountOfEconomies(){super();}
    public String withdrawMoney(Integer suma){
        return "You can withdraw money from the account only with a request!";
    }
    public String addMoney(Double Suma){

        if(Suma< this.minValue)return String.format("You can add a value of minimum %s" + this.minValue + " %s" + this.getValute() +" in this account.");
        if(Suma > this.getAccountLimit() || (Suma+this.getAvailableDeposit()>this.getAccountLimit()))
            return String.format("This value is over the limit of the account %s" + this.getAccountLimit()+" %s" +this.getValute());
        this.setAvailableDeposit(this.getAvailableDeposit()+Suma);
        return String.format("Value succesfully added!New available deposit: %s" + (this.getAvailableDeposit()+Suma) + " %s" + this.getValute());
    }


    public Integer getAccountLimit() {
        return accountLimit;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setAccountLimit(Integer accountLimit) {
        this.accountLimit = accountLimit;
    }
}
