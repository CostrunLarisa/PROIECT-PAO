package Clase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;


public class Services implements ActionListener{
    private User user;

    JFrame frame=new JFrame();
    JLabel messageLabel= new JLabel();
    JButton checkIncome =new JButton("Verifica sold");
    JButton payment =new JButton("Efectueaza plata");
    JButton myCards=new JButton("Cardurile mele");
    JButton showTransactions =new JButton("Istoric Tranzactii");
    JButton newAccount =new JButton("Deschide cont");
    JButton chargeCard =new JButton("Alimenteaza un card");
    JButton addCard =new JButton("Adauga card");
    JButton myAccounts =new JButton("Conturile mele");
    JTextArea showArea=new JTextArea();
    JLabel moneyAmountLabel=new JLabel();
    JTextField moneyAmount=new JTextField();
    JLabel notification=new JLabel();
    JButton button=new JButton();
    JScrollPane scroller=new JScrollPane(showArea);
    JLabel name=new JLabel("Prenume");
    JTextField nameField=new JTextField();
    JLabel lastName=new JLabel("Nume");
    JTextField lastNameField=new JTextField();
    JLabel pin=new JLabel("Pin");
    JTextField pinField=new JTextField();
    JLabel valute=new JLabel("Valuta");
    JButton submitButton=new JButton("Adauga card");
    JLabel type=new JLabel();
    JComboBox<String> options;
    JLabel warning=new JLabel();
    JComboBox<String> optionsValute;
    JLabel optionsValLabel=new JLabel("Valuta");
    JComboBox<String> optionsCard;
    JComboBox<String> optionsAcc;
    Services(User user) {

        this.user = user;


        type.setText("Tip ");
        type.setBounds(250,100,45,25);
        String[] choices={"MasterCard","Visa","Card de cumparaturi"};
        String[] choicesValute={"RON","EUR","USD","NZD","DKK","GCP"};
        optionsValute=new JComboBox<>(choicesValute);
        options=new JComboBox<String>(choices);
        options.setBounds(320,100,200,25);
        //options.setVisible(true);
        //type.setVisible(true);
        String[] cardOptions = new String[(this.user.getMyAccount().getCards()).size()];
        Set<Card> cards=this.user.getMyAccount().getCards();
        Integer index=0;
        for(Card c:cards){
            cardOptions[index]=c.getCardNumber();
            index+=1;
        }

        String[] accounts= {"Standard","De economii"};
        optionsAcc=new JComboBox<>(accounts);
        optionsCard=new JComboBox<>(cardOptions);
        optionsCard.setBounds(320,90,200,25);
        optionsAcc.setBounds(320,100,200,25);
        frame.add(optionsCard);
        frame.add(optionsAcc);


        frame.add(type);
        frame.add(options);


        name.setBounds(250,130,80,25);
        nameField.setBounds(320,130,200,25);

        lastName.setBounds(250,170,80,25);
        lastNameField.setBounds(320,170,200,25);

        pin.setBounds(250,200,80,25);
        pinField.setBounds(320,200,200,25);
        submitButton.setBounds(320,260,200,25);
        optionsValute.setBounds(320,240,200,25);
        //optionsValute.setVisible(true);


        frame.add(optionsValute);
        frame.add(optionsValLabel);
        frame.add(name);
        frame.add(nameField);
        frame.add(lastName);
        frame.add(lastNameField);
        frame.add(pin);
        frame.add(pinField);
        frame.add(submitButton);
        frame.add(warning);
        frame.add(button);

        messageLabel.setBounds(80,30,250,35);
        messageLabel.setText(String.format("Welcome,%s %s!", user.getMyAccount().getName(), user.getMyAccount().getLastname()));
        frame.add(messageLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,420);
        frame.setLayout(null);
        frame.setVisible(true);


        frame.add(addCard);
        frame.add(newAccount);
        frame.add(myAccounts);
        frame.add(myCards);
        frame.add(chargeCard);
        frame.add(showTransactions);
        frame.add(checkIncome);
        frame.add(payment);
        frame.add(moneyAmount);
        frame.add(moneyAmountLabel);
        frame.add(notification);
        frame.add(button);
        frame.add(scroller);

        frame.add(showArea);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,420);
        frame.setLayout(null);
        frame.setVisible(true);

        checkIncome.setBounds(90,100,150,25);
        checkIncome.addActionListener(this);
        checkIncome.setFocusPainted(true);

        showTransactions.setBounds(90,130,150,25);
        showTransactions.addActionListener(this);
        showTransactions.setFocusPainted(true);

        payment.setBounds(90,160,150,25);
        payment.addActionListener(this);
        payment.setFocusPainted(true);

        chargeCard.setBounds(90,190,150,25);
        chargeCard.addActionListener(this);
        chargeCard.setFocusPainted(true);

        myCards.setBounds(90,220,150,25);
        myCards.addActionListener(this);
        myCards.setFocusPainted(true);

        myAccounts.setBounds(90,250,150,25);
        myAccounts.addActionListener(this);
        myAccounts.setFocusPainted(true);

        newAccount.setBounds(90,280,150,25);
        newAccount.addActionListener(this);
        newAccount.setFocusPainted(true);

        addCard.setBounds(90,310,150,25);
        addCard.addActionListener(this);
        addCard.setFocusPainted(true);

        this.clearField();
    }

  /*
        if(utiliz.isLogged()==true)
        {
            OptionPage pagina=new OptionPage(utiliz.getConturi(),utiliz);
            System.out.println(pagina);             //Din pagina de autentificare se va deschide pagina cu alegerea contului
        }
        System.out.println(utiliz.isLogged());
        JFrame frame=new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        Object lock = new Object();
        Thread t = new Thread() {
            public void run() {
                synchronized(lock) {
                    while (frame.isVisible())
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    System.out.println("Working now");
                }
            }
        };
        t.start();

        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent arg0) {
                synchronized (lock) {
                    frame.setVisible(false);
                    lock.notify();
                }
            }

        });

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


   */
    public void clearField(){
        showArea.setText("");
        moneyAmount.setVisible(false);
        moneyAmountLabel.setVisible(false);
        showArea.setVisible(false);
        notification.setText("");
        button.setVisible(false);
        pinField.setVisible(false);
        pin.setText("");
        nameField.setVisible(false);
        name.setText("");
        lastNameField.setVisible(false);
        lastName.setText("");
        type.setVisible(false);
        options.setVisible(false);
        submitButton.setVisible(false);
        warning.setVisible(false);
        warning.setText("");
        optionsValLabel.setVisible(false);
        optionsValute.setVisible(false);
        optionsCard.setVisible(false);
        optionsAcc.setVisible(false);
    }
  @Override
  public void actionPerformed(ActionEvent e) {

        if(e.getSource()==showTransactions)
        {
            MyFileWriter wr=new MyFileWriter("Afiseaza tranzactii, cont "+this.user.getMyAccount().getIban());
            HashMap<String, Set<Transaction>> transactions=this.user.showTransactions();
            JPanel panel=new JPanel();
            clearField();
            showArea.setVisible(true);
            //showArea.setText("");
            for(HashMap.Entry<String,Set<Transaction>> entry:transactions.entrySet())
            {
                Set<Transaction> totalTrans=entry.getValue();
                for(Transaction elem:totalTrans) {
                    showArea.setBounds(280, 100, 250, 250);
                    String det=elem.getTranzactie();
                    String message = "Card Number:" + String.format("%s", entry.getKey()) + "\n" + det+"\n";
                    //showArea.setText(message);
                    showArea.append(message);
                    //showArea.append(newLine);
                    showArea.setVisible(true);
                    showArea.setEditable(false);
                /*JScrollPane scroller=new JScrollPane(textinfo);
                scroller.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setPreferredSize(new Dimension(250, 250));
                panel.add(scroller);
*/
                   // System.out.println(entry.getKey() + elem);
                }

            }
            //frame.add(panel);
        }
      if(e.getSource()==myCards)
      {
          MyFileWriter wr=new MyFileWriter("Afiseaza carduri, cont "+this.user.getMyAccount().getIban());
          clearField();
          showArea.setVisible(true);
          Account account=this.user.getMyAccount();
          Set<Card> cards=account.getCards();
          for(Card c:cards)
          {
              showArea.setBounds(280, 100, 250, 250);
              String message=c.getCardNumber()+"\n";
              showArea.append(message);
              //showArea.append(newLine);
              showArea.setVisible(true);
              showArea.setEditable(false);
          }
      }
      if(e.getSource()==myAccounts)
      {
          MyFileWriter wr=new MyFileWriter("Afiseaza conturi, cont "+this.user.getMyAccount().getIban());
          clearField();
          showArea.setVisible(true);
          Set<Account> accounts=this.user.getAccounts();
          for(Account account:accounts)
          {
              showArea.setBounds(280, 100, 250, 250);
              String message=account.getIban() + "-" +"Cont "+account.getType()+"\n";
              showArea.append(message);
              //showArea.append(newLine);
              showArea.setVisible(true);
              showArea.setEditable(false);
          }
      }
      if(e.getSource()==checkIncome)
      {
          MyFileWriter wr=new MyFileWriter("Verificare sold, cont "+this.user.getMyAccount().getIban());
          moneyAmount.setVisible(false);
          moneyAmountLabel.setVisible(false);
          clearField();
          showArea.setVisible(true);
          Account account= this.user.getMyAccount();
          showArea.setBounds(280, 100, 250, 250);
          String message="Sold contabil:"+account.getAccountableDeposit()+" "+account.getValute() + "\n" +
                  "Sold curent:"+account.getAvailableDeposit()+" "+account.getValute()+"\n"+"Sold blocat:"+account.getBlockedValue()+" "+account.getValute();
          showArea.append(message);
          //showArea.append(newLine);
          showArea.setVisible(true);
          showArea.setEditable(false);
      }

      if(e.getSource()==chargeCard)
      {
          clearField();

          Integer increment=0;
          optionsCard.setVisible(true);
          //The changing dimensions in order to place the buttons for selecting a card
          Integer dim=0;

          //The value for charging the card
          moneyAmount.setVisible(true);
          moneyAmountLabel.setVisible(true);
          moneyAmountLabel.setBounds(280,140,75,25);
          moneyAmount.setBounds(320,140,200,25);
          moneyAmountLabel.setText("Suma");


          button.setVisible(true);
          button.setText("Alimenteaza card");
          button.setBounds(320,180,200,25);
          button.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e)
                  {
                      String myamount =(String)moneyAmount.getText();
                      Double amount=Double.parseDouble(myamount);
                      String selectedCard=(String)optionsCard.getSelectedItem();
                      String message="";
                      System.out.println(amount);
                      Set<Card> cards=user.getMyAccount().getCards();
                      for(Card c:cards){
                          if(selectedCard.equals(c.getCardNumber())){
                              message=user.chargeCard(c,amount);
                              break;
                          }
                      }
                      notification.setVisible(true);
                      notification.setBounds(280,290,500,60);
                      notification.setText(message);
                      moneyAmount.setText("");
                  }
              });

      }
      if(e.getSource()==payment)
      {
          clearField();

          optionsCard.setVisible(true);
          optionsValLabel.setVisible(true);
          optionsValLabel.setText("Card platitor");
          optionsValLabel.setBounds(245,90,100,25);
          name.setText("Destinatar");
          name.setVisible(true);
          nameField.setVisible(true);
          lastName.setVisible(true);
          lastName.setText("Detalii");
          lastNameField.setVisible(true);
          lastNameField.setBounds(320,170,200,50);
          pin.setText("Suma");
          pin.setVisible(true);
          pin.setBounds(250,220,100,25);
          pinField.setBounds(320,230,100,25);
          pinField.setVisible(true);
          submitButton.setVisible(true);
          submitButton.setText("Efectueaza plata");
          frame.add(optionsCard);
          submitButton.addActionListener(new Action() {
              @Override
              public Object getValue(String key) {
                  return null;
              }

              @Override
              public void putValue(String key, Object value) {

              }

              @Override
              public void setEnabled(boolean b) {

              }

              @Override
              public boolean isEnabled() {
                  return false;
              }

              @Override
              public void addPropertyChangeListener(PropertyChangeListener listener) {

              }

              @Override
              public void removePropertyChangeListener(PropertyChangeListener listener) {

              }

              @Override
              public void actionPerformed(ActionEvent e) {
                    String amount=(String)pinField.getText();
                    String details=(String)lastNameField.getText();
                    String receiver=(String)nameField.getText();
                    String selectedCard=(String)optionsCard.getSelectedItem();
                    String message="";
                    System.out.println(amount);
                    Set<Card> cards=user.getMyAccount().getCards();
                    for(Card c:cards){
                        if(selectedCard.equals(c.getCardNumber())){
                            message=c.makePayment(receiver,Double.parseDouble(amount),details);
                            break;
                        }
                    }
                    notification.setVisible(true);
                    notification.setBounds(280,290,500,60);
                    notification.setText(message);
                    System.out.println(message);
                    lastNameField.setText("");
                    nameField.setText("");
                    pinField.setText("");
              }
          });
          MyFileWriter wr= new MyFileWriter("Plateste o factura"+this.user.getMyAccount().getIban());
      }
      if(e.getSource()==addCard)
      {
          MyFileWriter wr=new MyFileWriter("Adaugare card, cont "+this.user.getMyAccount().getIban());
          List<String> features=new ArrayList<>();
         // optionsValLabel.setVisible(true);
          optionsValLabel.setBounds(300,240,200,25);
          clearField();
          pinField.setVisible(true);
          pin.setText("Pin");
          nameField.setVisible(true);
          name.setText("Prenume");
          lastNameField.setVisible(true);
          lastName.setText("Nume");
          type.setVisible(true);
          type.setText("Tip");
          options.setVisible(true);
          submitButton.setVisible(true);

          submitButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String userLastName=lastNameField.getText();
                  String userName=nameField.getText();
                  String userPin=pinField.getText();
                  String type=(String)options.getSelectedItem();

                  //Card card = new CardDebit();
                  if(type.equals("Visa")) {
                      Visa card=new Visa();
                      card.setPin(Integer.parseInt(userPin));
                      card.setName(userName);
                      card.setLastname(userLastName);
                      card.setCurrentValue(0.0);

                      card.setvalute(user.getMyAccount().getValute());
                      features.add(String.valueOf(user.getMyAccount().getIban()));
                      features.add(card.getCardNumber());
                      features.add(userName);
                      features.add(userLastName);
                      features.add(userPin);
                      features.add(card.getvalute());

                      features.add(String.valueOf(0.0));
                      features.add(card.getEmissionDate());
                      features.add(card.getExpirationDate());
                      features.add(String.valueOf(card.getSecurityCode()));
                      Set<Card> cards=user.getMyAccount().getCards();
                      cards.add(card);
                      user.getMyAccount().setCards(cards);
                  }
                  else if(type.equals("MasterCard")) {
                    MasterCard card = new MasterCard();
                      card.setPin(Integer.parseInt(userPin));
                      card.setName(userName);
                      card.setLastname(userLastName);
                      card.setCurrentValue(0.0);
                      //card.setvalute((String)optionsValute.getSelectedItem());
                      //System.out.println((String)optionsValute.getSelectedItem());
                      card.setvalute(user.getMyAccount().getValute());
                      features.add(String.valueOf(user.getMyAccount().getIban()));
                      features.add(card.getCardNumber());
                      features.add(userName);
                      features.add(userLastName);
                      features.add(userPin);
                      features.add(card.getvalute());
                      // features.add(user.getMyAccount());
                      features.add(String.valueOf(0.0));
                      features.add(card.getEmissionDate());
                      features.add(card.getExpirationDate());
                      features.add(String.valueOf(card.getSecurityCode()));
                      if(type.equals("Visa")) {
                          features.add(String.valueOf(card.getComision()));
                      }

                      Set<Card> cards=user.getMyAccount().getCards();
                      cards.add(card);
                      user.getMyAccount().setCards(cards);
                  }
                  else {
                      CardShopping card=new CardShopping();
                      card.setPin(Integer.parseInt(userPin));
                      card.setName(userName);
                      card.setLastname(userLastName);
                      card.setCurrentValue(0.0);
                      //card.setvalute((String)optionsValute.getSelectedItem());
                      //System.out.println((String)optionsValute.getSelectedItem());
                      card.setvalute(user.getMyAccount().getValute());
                      features.add(String.valueOf(user.getMyAccount().getIban()));
                      features.add(card.getCardNumber());
                      features.add(userName);
                      features.add(userLastName);
                      features.add(userPin);
                      features.add(card.getvalute());
                      // features.add(user.getMyAccount());
                      features.add(String.valueOf(0.0));
                      features.add(card.getEmissionDate());
                      features.add(card.getExpirationDate());
                      features.add(String.valueOf(card.getSecurityCode()));

                      features.add(String.valueOf(card.getInterest()));
                      features.add(String.valueOf(card.getMinimumPaymentVal()));
                      features.add(String.valueOf(card.getMinimumChargeVal()));
                      Set<Card> cards=user.getMyAccount().getCards();
                      cards.add(card);
                      user.getMyAccount().setCards(cards);
                  }
                  if (userPin.length()>3 || userPin.length()<3 ){

                      warning.setBounds(320,290,200,25);
                      warning.setText("Lungimea admisa pentru pin: 3 cifre!");
                      warning.setVisible(true);
                      warning.setForeground(Color.red);
                      pinField.setText("");
                  }

                  MyFileWriter wr=new MyFileWriter("Cards.csv",features);
              }
          });
      }
      if(e.getSource()==newAccount)
      {
          clearField();
          MyFileWriter wr=new MyFileWriter("Adaugare cont, cont "+this.user.getMyAccount().getIban());
          List<String> features=new ArrayList<>();
          features.add(this.user.getId());
          // optionsValLabel.setVisible(true);
          optionsValLabel.setBounds(250,240,200,25);

          nameField.setVisible(true);
          name.setText("Nume");
          lastNameField.setVisible(true);
          lastName.setText("Prenume");
          type.setVisible(true);
          type.setText("Tip");
          submitButton.setBounds(320,280,200,25);
          submitButton.setText("Adauga cont");
          submitButton.setVisible(true);
          optionsValLabel.setVisible(true);
          optionsValute.setVisible(true);
          optionsAcc.setVisible(true);
          submitButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String userLastName=lastNameField.getText();
                  String userName=nameField.getText();
                  String userPin=pinField.getText();
                  String type=(String)optionsAcc.getSelectedItem();
                  String valute=(String)optionsValute.getSelectedItem();

                  if(type.equals("Standard")) {
                      CurrentAccount account=new CurrentAccount();
                      features.add(account.getIban());
                      features.add(type);
                      features.add(valute);
                      features.add(String.valueOf(account.getAvailableDeposit()));
                      features.add(String.valueOf(account.getUnauthorizedDeposit()));
                      features.add(String.valueOf(account.getBlockedValue()));
                      features.add(String.valueOf(account.getInterest()));
                      features.add(userName);
                      features.add(userLastName);
                      Set<Account> accounts=user.getAccounts();
                      accounts.add(account);
                      user.setAccounts(accounts);
                  }
                  else  {

                      AccountOfEconomies account=new AccountOfEconomies();
                      Integer val=account.getMinValue();
                      account.setAvailableDeposit(Double.valueOf(val));
                      features.add(String.valueOf(user.getMyAccount().getIban()));
                      features.add(type);
                      features.add(valute);
                      features.add(String.valueOf(account.getAvailableDeposit()));
                      features.add(String.valueOf(account.getUnauthorizedDeposit()));
                      features.add(String.valueOf(account.getBlockedValue()));
                      features.add(String.valueOf(account.getAccountLimit()));
                      features.add(String.valueOf(account.getInterest()));
                      features.add(userName);
                      features.add(userLastName);

                      Set<Account> accounts=user.getAccounts();
                      accounts.add(account);
                      user.setAccounts(accounts);
                  }

                  MyFileWriter wr=new MyFileWriter("Accounts.csv",features);
              }
          });
      }
  }
}
