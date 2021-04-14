package Clase;

import java.awt.*;
import java.util.*;
import java.util.List;

//Clasa utilizator care implementeaza toate operatiile unui cont bancar
public class User implements OperationsBankAccount {

    private String id;          //Id-ul de logare va reprezenta namele si lastnamenele posesorului concatenate
    private String password;
    private Set<Account> accounts=new HashSet<Account>();
    private Account myAccount;
    private boolean logged;


    User(){
        this.logged=false;
    }
    public boolean login(){
        UsersAndPasswords useri=new UsersAndPasswords();
        LoginPage p=new LoginPage(useri.getLoginInfo(),this);
        System.out.println(p);
        //this.accounts=accounts[this.getId()];
        if(this.isLogged()==true ){

            return true;
        }
        return false;
    }

    public double checkDeposit(){

        return myAccount.getAvailableDeposit();
    }

    public HashMap<String,String> showTransactions(){

        HashMap<String,String> tranzactiiCard=new HashMap<String,String>();
        for(Card c :this.myAccount.getCards())
        {
            for(Transaction tran:c.getTransactions())
            {
                tranzactiiCard.put(c.getCardNumber(),tran.getTranzactie());
            }
        }
        return tranzactiiCard;
        /*OptionPage pagina=new OptionPage(istoric);
        System.out.println(pagina);
        for(Transaction tran:istoric){
            System.out.println(tran.getTranzactie());
        }
        */

    }
    public void addValue(){

    }
    public void payment(String receiver,double value){

    }
    public Set<Card> myCards(){
            return this.getMyAccount().getCards();
    }
    public Set<Account> myAccounts(){
        return this.getAccounts();
    }
    public void setLogged(){
        this.logged=true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public void setMyAccount(Account myAccount) {
        this.myAccount = myAccount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Verific daca utilizatorul este logat
    public boolean isLogged() {
        return this.logged;
    }

    //Metoda valabila doar pentru cardurile Visa, care pot fi alimentate din accounts
    public String chargeCard(Card c,Double value){
        Card crd=new Visa();
        //Programul verifica daca am un card de tip Visa; numai acest tip de card poate fi alimentat dintr-un cont
        if(c.getClass()==crd.getClass()){
            try{
                //In cazul in care value pe care vreau sa o alimentez este mai mare decat value mea curenta arunc o eroare
                if(value>this.getMyAccount().getAvailableDeposit())throw new ArithmeticException("Fonduri insuficiente");
                else{
                    //Updatez value noua din card
                    c.setCurrentValue(c.getCurrentValue()+value);
                    //Extrag din cont value*comision cu care am alimentat cardul
                    this.getMyAccount().setAvailableDeposit(this.getMyAccount().getAvailableDeposit()-value*c.getComision());
                    String detalii=String.format("Alimentare card%s",c.getCardNumber());
                    Transaction tr=new Transaction(value,detalii,true);
                    Set<Card>carduri=this.myAccount.getCards();
                    //Adaug tranzactia la istoricul cardului
                    for(Card cr:carduri)
                    {
                        //Caut cardul printre cardurile asociate contului
                        if(cr.equals(c)){
                            List<Transaction> tranzactii=cr.getTransactions();
                            tranzactii.add(tr);
                            cr.setTransactions(tranzactii);
                            break;
                        }
                    }
                    return String.format("Alimentare efectuata cu succes!Noua suma:%s",c.getCurrentValue());
                }
            }catch(Exception e){
                    System.out.print(e);
            }
        }
        //Pentru orice alta clasa nu pot alimenta cardul
        return "You cannot charge the card from the current account!";
    }
    //Metoda care adauga atat in baza de date cat si in profilul utilizatorului un cont nou
    public void openNewAccount(){

    }
    //Metoda care adauga atat in baza de date cat si in profilul utilizatorului un card nou
    public void addNewCard(Card c){
        this.getMyAccount().addCard(c);
    }
}
