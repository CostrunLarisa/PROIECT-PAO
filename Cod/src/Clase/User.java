package Clase;

import java.util.*;

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

    public HashMap<String,Set<Transaction>> showTransactions(){
        HashMap<String,Set<Transaction>> transactions=new HashMap<String,Set<Transaction>>();
        for(Card c :this.myAccount.getCards())
        {
            Set<String> aux=new HashSet<>();
            List<Transaction> sortedItem=new ArrayList<>();
            for(Transaction tran:c.getTransactions()) {

                //Set<String> auxx=tranzactiiCard.get(c.getCardNumber());
                aux.add(tran.getTranzactie());
                sortedItem.add(tran);
                //tranzactiiCard.put(c.getCardNumber(), auxx);
            }
            //Collections.sort(sortedItem);
            Set<Transaction> finalTran=new HashSet<Transaction>(sortedItem);
            transactions.put(c.getCardNumber(), finalTran);
        }/*
        for(HashMap.Entry<String,Set<String>> entry:tranzactiiCard.entrySet())
        {
            Set<String> totalTrans=entry.getValue();
            for(String elem:totalTrans) {


                 System.out.println(entry.getKey() + elem);
            }

        }*/
        return transactions;

    }
    public void addValue(){

    }
    public void payment(String receiver,double value){
        String detalii="Plata catre: " + receiver+"\n"+"Din contul:"+this.getMyAccount().getIban()+"\n";
        Transaction tran=new Transaction(value,detalii,true);
        List<String> lista= Arrays.asList(String.valueOf(value),detalii,"true");
        MyFileWriter wr=new MyFileWriter("Transactions.csv",lista);
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
        String number=c.getCardNumber();
        //Programul verifica daca am un card de tip Visa; numai acest tip de card poate fi alimentat dintr-un cont
        if((number.charAt(0))=='5'){
            try{
                //In cazul in care value pe care vreau sa o alimentez este mai mare decat value mea curenta arunc o eroare
                if(value>this.getMyAccount().getAvailableDeposit() || (value+value*c.getComision())>this.getMyAccount().getAvailableDeposit())return "Fonduri insuficiente";
                else{
                    //Updatez value noua din card
                    c.setCurrentValue(c.getCurrentValue()+value);
                    //Extrag din cont value*comision cu care am alimentat cardul

                    this.getMyAccount().setAvailableDeposit(this.getMyAccount().getAvailableDeposit()-value*c.getComision()-value);
                    String detalii=String.format("Alimentare card %s din contul %s",c.getCardNumber(),this.getMyAccount().getIban());
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

                    //Calculating the new accountable deposit after paying commission
                    Double val=(this.getMyAccount().getAvailableDeposit()+this.getMyAccount().getBlockedValue());

                    //Setting the new accountable deposit
                    this.getMyAccount().setAccountableDeposit(val);
                    List<String> vals=Arrays.asList(c.getCardNumber(),tr.getData(),String.valueOf(value),detalii,"true");
                    MyFileWriter t=new MyFileWriter("Transactions.csv",vals);
                    MyFileWriter wr=new MyFileWriter("Alimentare card "+c.getCardNumber());
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
