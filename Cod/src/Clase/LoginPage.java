package Clase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
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
        loginInfo.putAll(loginInfoOriginal);
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

        Set<String> conturiUser=getConturi(id);
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
        Set<String>conturi=new HashSet<>();
        try{
            File content=new File("Accounts.csv");
            Scanner reader=new Scanner(content);
            //Reading the information from the file line by line
            while(reader.hasNextLine()){
                String cnt=reader.nextLine();
                String[] features=cnt.split(",");
                if(features[0].equals(id))
                {
                    conturi.add(cnt);
                }
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Error while reading the data from the file!");
        }
        return conturi;
    }

    public Set<Card> getCarduri(String iban)
    {
        Set<Card> carduriGasite=new HashSet<Card>();
        try{

            //Having the list of cards numbers I will search for them in the "Cards" file
            //We will take all the cards of the user and their data, transform them into Card objects
            // and place them in a collection that it will be added to the current account

            File carduri=new File("Cards.csv");
            Scanner reader=new Scanner(carduri);

            //Reading the information from the file line by line
            while(reader.hasNextLine()){
                String[] card=reader.nextLine().split(",");
                String Word = card[1].trim();
                String aux=card[0].substring(1,card[0].length());
                //System.out.println(Arrays.toString(iban.toCharArray()));
                //System.out.println(Arrays.toString(aux.toCharArray()));
                //System.out.println(iban.equalsIgnoreCase(aux));

                if(iban.equalsIgnoreCase(aux)) {
                    //Information about cards are stored in a file, each line containing data for another card
                    //System.out.println((Word.charAt(1)));
                    if ((Word.charAt(0)) == '4') {
                        Card cardAux = new MasterCard(card[2], card[3], Integer.parseInt(card[4]), card[5], Double.parseDouble(card[6]));
                        cardAux.setCardNumber(Word);
                        cardAux.setEmissionDate(card[7]);
                        cardAux.setExpirationDate(card[8]);
                        cardAux.setSecurityCode(Integer.parseInt(card[9]));
                        //Getting the list of transactions of each card by passing the card number
                        List<Transaction> tran=this.getTran(Word);
                        cardAux.setTransactions(tran);
                        carduriGasite.add(cardAux);
                        continue;
                    }
                    //Visa has a special column regarding the commision, hence card[10]

                    else if ((Word.charAt(0)) == '5') {
                        Card cardAux = new Visa(card[2], card[3], Integer.parseInt(card[4]), card[5], Double.parseDouble(card[6]), Double.parseDouble(card[10]));
                        cardAux.setCardNumber(Word);
                        cardAux.setEmissionDate(card[7]);
                        cardAux.setExpirationDate(card[8]);
                        cardAux.setSecurityCode(Integer.parseInt(card[9]));
                        //Getting the list of transactions of each card by passing the card number
                        List<Transaction> tran=this.getTran(Word);
                        cardAux.setTransactions(tran);
                        carduriGasite.add(cardAux);
                        continue;
                    }
                    //Declaring the object we will create
                        Card cardAux = new CardShopping(card[2], card[3], Integer.parseInt(card[4]), card[5], Double.parseDouble(card[6]), Double.parseDouble(card[10]), Double.parseDouble(card[10]), Double.parseDouble(card[11]));
                        cardAux.setCardNumber(Word);
                        cardAux.setEmissionDate(card[7]);
                        cardAux.setExpirationDate(card[8]);
                        cardAux.setSecurityCode(Integer.parseInt(card[9]));
                    //Getting the list of transactions of each card by passing the card number
                    List<Transaction> tran=this.getTran(Word);
                    cardAux.setTransactions(tran);
                    carduriGasite.add(cardAux);
                }

            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Error while reading the data from the file!");
        }
        return carduriGasite;
    }

    public List<Transaction> getTran(String cardNumber)
    {
        List<Transaction> transactions=new ArrayList<Transaction>();
        try{

            File content=new File("Transactions.csv");
            Scanner reader=new Scanner(content);
            //Reading the information from the file line by line
            while(reader.hasNextLine()){
                String[] features=reader.nextLine().split(",");
                Transaction tran=new Transaction();
                String aux=features[0].substring(1,features[0].length());

                //System.out.println(Arrays.toString(aux.toCharArray()));
                //System.out.println(Arrays.toString(cardNumber.toCharArray()));
                //System.out.println(aux.equalsIgnoreCase(cardNumber));
                if(aux.equalsIgnoreCase(cardNumber))
                {
                    tran=new Transaction(Double.parseDouble(features[2]),features[3],Boolean.parseBoolean(features[4]));
                    tran.setData(features[1]);
                    transactions.add(tran);
                }
            }
            reader.close();

        }catch(FileNotFoundException e){
            System.out.println("Error while reading the data from the file!");
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

                if(loginInfo.containsKey(loginUser))
                {
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
