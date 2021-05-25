package Clase;

import Database.Server;

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
        //UsersAndPasswords useri=new UsersAndPasswords();
        //useri=new Server();
        //LoginPage p=new LoginPage(useri.getLoginInfo(),this);
        LoginPage p=new LoginPage(Server.getUsers(),this);
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
    public String payment(String receiver,String details,Double value,Card c){
        String detalii=details+"\n"+"Plata catre: " + receiver+"\n"+"Din contul:"+this.getMyAccount().getIban()+"\n";
        Transaction tran=new Transaction(value,detalii,true);
        List<String> lista= Arrays.asList(String.valueOf(value),detalii,"true");
        MyFileWriter wr=new MyFileWriter("Transactions.csv",lista);
        Set<Card>carduri=this.myAccount.getCards();
        for(Card cr:carduri)
        {
            //Searching for the current card
            if(cr.equals(c)){
                if(value>this.getMyAccount().getAvailableDeposit() || (value+value*c.getComision())>this.getMyAccount().getAvailableDeposit())return "Fonduri insuficiente";
                else {
                    //Updating the new value from the card
                    c.setCurrentValue(c.getCurrentValue() + value);
                    //Extract the  value*comision from the account which card made the payment

                    this.getMyAccount().setAvailableDeposit(this.getMyAccount().getAvailableDeposit() - value * c.getComision() - value);
                    List<Transaction> tranzactii = cr.getTransactions();
                    tranzactii.add(tran);
                    cr.setTransactions(tranzactii);


                    //Updating the data from database
                    String stmt1="\'"+c.getCardNumber()+"\'";
                    String statement=String.format("UPDATE `bank`.`card` SET `currentValue`=('%f') WHERE `idcard`=(%s);",c.getCurrentValue()+value,stmt1);
                    //Substract the amount of money from the account
                    String stmt = "\'"+this.getMyAccount().getIban()+"\'";
                    String statement2=String.format("UPDATE `bank`.`currentaccount` SET `availableDeposit`=('%f') WHERE `idAccount`=(%s);",this.getMyAccount().getAvailableDeposit()-value*c.getComision()-value,stmt);

                    //Create a new transaction and add it in the database
                    String stmt2="\'"+detalii+"\'"+","+"\'"+tran.getData()+"\'"+","+"\'"+c.getCardNumber()+"\'"+","+"\'"+"true"+"\'";
                    String statementTran=String.format("INSERT INTO `bank`.`transaction`(`details`,`data`,`idcard`,`charged`,`value`) values(%s,'%f');",stmt2,tran.getSumaTranzac());
                    Server.insert(statementTran);
                    Server.update(statement);
                    Server.update(statement2);

                    Double val=(this.getMyAccount().getAvailableDeposit()+this.getMyAccount().getBlockedValue());
                    String statement3 = String.format("UPDATE `bank`.`currentaccount` SET `accountableDeposit`=('%f') WHERE `idAccount`=(%s);",val,stmt);
                    Server.update(statement3);
                    this.getMyAccount().setAccountableDeposit(val);
                    List<String> vals=Arrays.asList(c.getCardNumber(),tran.getData(),String.valueOf(value),detalii,"true");
                    return "Plata efectuata cu succes!";
                }
            }
        }
        return "You cannot charge the card from the current account!";
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

                    //Updating the data from database
                    String stmt1="\'"+c.getCardNumber()+"\'";
                    String statement=String.format("UPDATE `bank`.`card` SET `currentValue`=('%f') WHERE `idcard`=(%s);",c.getCurrentValue()+value,stmt1);
                    //Substract the amount of money from the account
                    String stmt = "\'"+this.getMyAccount().getIban()+"\'";
                    String statement2=String.format("UPDATE `bank`.`currentaccount` SET `availableDeposit`=('%f') WHERE `idAccount`=(%s);",this.getMyAccount().getAvailableDeposit()-value*c.getComision()-value,stmt);

                    //Create a new transaction and add it in the database
                    String stmt2="\'"+detalii+"\'"+","+"\'"+tr.getData()+"\'"+","+"\'"+c.getCardNumber()+"\'"+","+"\'"+"true"+"\'";
                    String statementTran=String.format("INSERT INTO `bank`.`transaction`(`details`,`data`,`idcard`,`charged`,`value`) values(%s,'%f');",stmt2,tr.getSumaTranzac());
                    Server.insert(statementTran);
                    Server.update(statement);
                    Server.update(statement2);
                    //Adding the transaction to the history of the card
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
                    String statement3 = String.format("UPDATE `bank`.`currentaccount` SET `accountableDeposit`=('%f') WHERE `idAccount`=(%s);",val,this.getMyAccount().getIban());
                    Server.update(statement3);
                    MyFileWriter t=new MyFileWriter("Transactions.csv",vals);
                    MyFileWriter wr=new MyFileWriter("Alimentare card "+c.getCardNumber());
                    return "Plata efectuata cu succes!";
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
