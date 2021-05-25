package Clase;

import Database.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public class LoginPage implements ActionListener{

    //The login and reset buttons
    HashMap<String,String> loginInfo=new HashMap<String,String>();
    JFrame frame=new JFrame();
    JButton loginButton=new JButton(("Login"));
    JButton resetButton=new JButton(("Reset"));
    JTextField userID= new JTextField();
    JPasswordField passwordUser=new JPasswordField();
    JLabel userIDLabel=new JLabel("ID user");
    JLabel passwordUserLabel=new JLabel("Password");
    JLabel messageLabel= new JLabel();
    boolean logged=false;
    WindowListener winLis=new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
            Frame frame = (Frame) e.getSource();

        }

    };

    User utiliz;
    //HashMap<String,String> login
    LoginPage(HashMap<String,String> loginInfoOriginal, User utiliz){
        this.loginInfo.putAll(loginInfoOriginal);
        this.utiliz=utiliz;
        userIDLabel.setBounds(50,100,75,25);
        userID.setBounds(125,100,200,25);
        passwordUserLabel.setBounds(50,150,200,25);
        passwordUser.setBounds(125,150,200,25);
        messageLabel.setBounds(125,250,250,35);

        frame.add(userIDLabel);
        frame.add(userID);
        frame.add(passwordUser);
        frame.add(passwordUserLabel);
        frame.add(messageLabel);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.addWindowListener(winLis);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

        loginButton.setBounds(125,200,100,25);
        loginButton.addActionListener(this);
        loginButton.setFocusPainted(true);
        resetButton.setBounds(225,200,100,25);
        resetButton.addActionListener(this);
        resetButton.setFocusPainted(true);

    }

    public void setConturi(String id){
        Set<String> conturiUser=Server.getAccounts(id);
        Set<Account> conturiUserCreate=new HashSet<Account>();
        for(String cont:conturiUser) {

            //The first attribute indicates the username
            //The  second attribute will represent the type of the account:standard or of economies
            //Data is stored as: user and account information
            //Obtaining the list of cards


            //Obtaining the information about the account
            String[] values = cont.split(",");
            Integer size = values.length;

            //Take the third attribute in order to check the type of the card
            String tip = values[2];
            Account CurrentAccount;
            String iban=values[1];
            String valuta=values[3];
            Double soldDisponibil=Double.parseDouble(values[4]);
            Double soldContabil=Double.parseDouble(values[5]);
            Double soldNeautorizat=Double.parseDouble(values[6]);
            Double sumaBlocata=Double.parseDouble(values[7]);
            Double dobanda=Double.parseDouble(values[8]);

            Set<Card> carduri=getCarduri(iban);
            if(tip.equals("Standard")){

                String nume=values[9];
                String prenume=values[10];
                CurrentAccount =new CurrentAccount(valuta,soldDisponibil,soldNeautorizat,sumaBlocata,dobanda,nume,prenume);
                CurrentAccount.setIban(iban);

            }
            else{
                Integer limitaCont=Integer.parseInt(values[11]);
                String nume=values[9];
                String prenume=values[10];
                CurrentAccount =new AccountOfEconomies(valuta,soldDisponibil,soldNeautorizat,sumaBlocata,dobanda,limitaCont,nume,prenume);
                CurrentAccount.setIban(iban);
            }
            if(carduri.size()!=0){
                CurrentAccount.setCards(carduri);
            }
            conturiUserCreate.add(CurrentAccount);
        }
        utiliz.setAccounts(conturiUserCreate);
    }
    public Set<String> getConturi(String id){

        FileReader data=new FileReader("Accounts.csv");
        Set<String> info=data.getInfo();
        Set<String> conturi=new HashSet<>();
        for(String cnt : info) {
            cnt=cnt.replaceAll("\"","");
            String[] features = cnt.split(",");
            if (features[0].equals(id)) {
                conturi.add(cnt);
            }
        }
        return conturi;
    }

    public Set<Card> getCarduri(String iban)
    {
        //FileReader data=new FileReader("Cards.csv");
        //Set<String> info=data.getInfo();
        Set<String> info =Server.getCards(iban);
        Set<Card> foundCards=new HashSet<Card>();
        /*for(String item:info)
        {
            item=item.replaceAll("\"","");
            String[] features = item.split(",");
            if(features.length >1){
            String Word = features[1].trim();
            String aux=features[0];
            if(iban.equalsIgnoreCase(aux)) {
                //Information about cards are stored in a file, each line containing data for another card
                //System.out.println((Word.charAt(1)));
                if ((Word.charAt(0)) == '4') {
                    Card cardAux = new MasterCard(features[2],features[3], Integer.parseInt(features[4]),features[5], Double.parseDouble(features[6]));
                    cardAux.setCardNumber(Word);
                    cardAux.setEmissionDate(features[7]);
                    cardAux.setExpirationDate(features[8]);
                    cardAux.setSecurityCode(Integer.parseInt(features[9]));
                    //Getting the list of transactions of each card by passing the card number
                    List<Transaction> tran=this.getTran(Word);
                    cardAux.setTransactions(tran);
                    foundCards.add(cardAux);
                    continue;
                }
                //Visa has a special column regarding the commision, hence card[10]

                else if ((Word.charAt(0)) == '5') {
                    Card cardAux = new Visa(features[2], features[3], Integer.parseInt(features[4]), features[5], Double.parseDouble(features[6]), Double.parseDouble(features[10]));
                    cardAux.setCardNumber(Word);
                    cardAux.setEmissionDate(features[7]);
                    cardAux.setExpirationDate(features[8]);
                    cardAux.setSecurityCode(Integer.parseInt(features[9]));
                    cardAux.setComision(Double.parseDouble(features[10]));
                    //Getting the list of transactions of each card by passing the card number
                    List<Transaction> tran=this.getTran(Word);
                    cardAux.setTransactions(tran);
                    foundCards.add(cardAux);
                    continue;
                }
                //Declaring the object we will create
                Card cardAux = new CardShopping(features[2], features[3], Integer.parseInt(features[4]), features[5], Double.parseDouble(features[6]), Double.parseDouble(features[10]), Double.parseDouble(features[11]), Double.parseDouble(features[12]));
                cardAux.setCardNumber(Word);
                cardAux.setEmissionDate(features[7]);
                cardAux.setExpirationDate(features[8]);
                cardAux.setSecurityCode(Integer.parseInt(features[9]));
                //Getting the list of transactions of each card by passing the card number
                List<Transaction> tran=this.getTran(Word);
                cardAux.setTransactions(tran);
                foundCards.add(cardAux);
            }
            }
        }
        return foundCards;
        */
        for(String item:info)
        {

            String[] features = item.split(",");
            if(features.length >1) {
                String Word = features[1].trim();
                String aux = features[0];

                //Information about cards are stored in a file, each line containing data for another card
                //System.out.println((Word.charAt(1)));
                if ((Word.charAt(0)) == '4') {
                    Card cardAux = new MasterCard(features[2], features[3], Integer.parseInt(features[4]), features[5], Double.parseDouble(features[6]));
                    cardAux.setCardNumber(Word);
                    cardAux.setEmissionDate(features[7]);
                    cardAux.setExpirationDate(features[8]);
                    cardAux.setSecurityCode(Integer.parseInt(features[9]));
                    //Getting the list of transactions of each card by passing the card number
                    List<Transaction> tran = this.getTran(Word);
                    cardAux.setTransactions(tran);
                    foundCards.add(cardAux);
                    continue;
                }
                //Visa has a special column regarding the commision, hence card[10]

                else if ((Word.charAt(0)) == '5') {
                    Card cardAux = new Visa(features[2], features[3], Integer.parseInt(features[4]), features[5], Double.parseDouble(features[6]), Double.parseDouble(features[10]));
                    cardAux.setCardNumber(Word);
                    cardAux.setEmissionDate(features[7]);
                    cardAux.setExpirationDate(features[8]);
                    cardAux.setSecurityCode(Integer.parseInt(features[9]));
                    cardAux.setComision(Double.parseDouble(features[10]));
                    //Getting the list of transactions of each card by passing the card number
                    List<Transaction> tran = this.getTran(Word);
                    cardAux.setTransactions(tran);
                    foundCards.add(cardAux);
                    continue;
                }
                //Declaring the object we will create
                Card cardAux = new CardShopping(features[2], features[3], Integer.parseInt(features[4]), features[5], Double.parseDouble(features[6]), Double.parseDouble(features[10]), Double.parseDouble(features[11]), Double.parseDouble(features[12]));
                cardAux.setCardNumber(Word);
                cardAux.setEmissionDate(features[7]);
                cardAux.setExpirationDate(features[8]);
                cardAux.setSecurityCode(Integer.parseInt(features[9]));
                //Getting the list of transactions of each card by passing the card number
                List<Transaction> tran = this.getTran(Word);
                cardAux.setTransactions(tran);
                foundCards.add(cardAux);
            }
        }
        return foundCards;
    }

    public List<Transaction> getTran(String cardNumber)
    {
        //FileReader data=new FileReader("Transactions.csv");
        //Set<String> info=data.getInfo();
        Set<String> info = Server.getTransactions(cardNumber);
        /*
        List<Transaction> transactions=new ArrayList<Transaction>();

        for(String tr : info){
                String[] features=tr.replaceAll("\"","").split(",");
                Transaction tran=new Transaction();
                String aux=features[0];
                System.out.println(aux);
                //System.out.println(features[1]);
                //System.out.println(features[1]);
                //System.out.println(Arrays.toString(aux.toCharArray()));
                //System.out.println(Arrays.toString(cardNumber.toCharArray()));
                //System.out.println(aux.equalsIgnoreCase(cardNumber));
                if(aux.equalsIgnoreCase(cardNumber))
                {
                    tran=new Transaction(Double.parseDouble(features[2]),features[3],Boolean.parseBoolean(features[4]));
                    tran.setData(features[1].substring(1,features[1].length()-1));
                    transactions.add(tran);
                }
        }
        return transactions;

         */
        List<Transaction> transactions=new ArrayList<Transaction>();

        for(String tr : info){
            String[] features=tr.replaceAll("\"","").split(",");
            Transaction tran=new Transaction();
            String aux=features[0];
            System.out.println(aux);
            //System.out.println(features[1]);
            //System.out.println(features[1]);
            //System.out.println(Arrays.toString(aux.toCharArray()));
            //System.out.println(Arrays.toString(cardNumber.toCharArray()));
            //System.out.println(aux.equalsIgnoreCase(cardNumber));
            tran=new Transaction(Double.parseDouble(features[2]),features[3],Boolean.parseBoolean(features[4]));
            tran.setData(features[1]);
            transactions.add(tran);

        }
        return transactions;
    }
    @Override
    public void actionPerformed(ActionEvent e){
            if(e.getSource()==resetButton){
                userID.setText("");
                passwordUser.setText("");
            }
            if(e.getSource()==loginButton){
                String loginUser=userID.getText();
                String loginPassword=String.valueOf(passwordUser.getPassword());
                System.out.println(loginInfo.get("Admin="));
                System.out.println(loginInfo.containsKey(loginUser));
                System.out.println(loginInfo.keySet().toArray()[1]);
                if(loginInfo.containsKey(loginUser))
                {
                    System.out.println(loginUser);
                    this.utiliz.setId(loginUser);
                    setConturi(userID.getText());
                    if(loginInfo.get(loginUser).equals(loginPassword)){
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Welcome!");
                        this.utiliz.setPassword(loginPassword);
                        this.utiliz.setLogged();
                        this.logged=true;
                        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        frame.dispose();
                        OptionPage pagina=new OptionPage(utiliz.getAccounts(),utiliz);
                        System.out.println(pagina); //From the Login Page will pe opened a new window where the user will chose the main account
                    }
                    else{
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Incorrect password!");
                    }
                }
            }
    }

    public boolean isLogged() {
        return logged;
    }


}
