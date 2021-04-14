package Clase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class Services implements ActionListener{
    private User user;

    JFrame frame=new JFrame();
    JLabel messageLabel= new JLabel();
    JButton verificareSold =new JButton("Verifica sold");
    JButton adaugaSuma =new JButton("Adauga suma in cont");
    JButton efectueazaPlata =new JButton("Efectueaza plata");
    JButton cardurileMele =new JButton("Cardurile mele");
    JButton showTransactions =new JButton("Istoric Tranzactii");
    JButton deschideContNou =new JButton("Deschide cont");
    JButton alimenteazaCard =new JButton("Alimenteaza un card");
    JButton adaugaCard =new JButton("Adauga card");
    JButton conturileMele =new JButton("Conturile mele");


    Services(User user) {

        this.user = user;

        messageLabel.setBounds(80,30,250,35);
        messageLabel.setText(String.format("Welcome,%s %s!", user.getMyAccount().getName(), user.getMyAccount().getLastname()));
        frame.add(messageLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,420);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(adaugaCard);
        frame.add(adaugaSuma);
        frame.add(deschideContNou);
        frame.add(conturileMele);
        frame.add(cardurileMele);
        frame.add(alimenteazaCard);
        frame.add(showTransactions);
        frame.add(verificareSold);
        frame.add(efectueazaPlata);


        verificareSold.setBounds(90,100,150,25);
        verificareSold.addActionListener(this);
        verificareSold.setFocusPainted(true);

        showTransactions.setBounds(90,130,150,25);
        showTransactions.addActionListener(this);
        showTransactions.setFocusPainted(true);

        efectueazaPlata.setBounds(90,160,150,25);
        efectueazaPlata.addActionListener(this);
        efectueazaPlata.setFocusPainted(true);

        alimenteazaCard.setBounds(90,190,150,25);
        alimenteazaCard.addActionListener(this);
        alimenteazaCard.setFocusPainted(true);

        cardurileMele.setBounds(90,220,150,25);
        cardurileMele.addActionListener(this);
        cardurileMele.setFocusPainted(true);

        conturileMele.setBounds(90,250,150,25);
        conturileMele.addActionListener(this);
        conturileMele.setFocusPainted(true);

        deschideContNou.setBounds(90,280,150,25);
        deschideContNou.addActionListener(this);
        deschideContNou.setFocusPainted(true);

        adaugaSuma.setBounds(90,310,150,25);
        adaugaSuma.addActionListener(this);
        adaugaSuma.setFocusPainted(true);

        adaugaCard.setBounds(90,340,150,25);
        adaugaCard.addActionListener(this);
        adaugaCard.setFocusPainted(true);
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
  @Override
  public void actionPerformed(ActionEvent e) {

        if(e.getSource()==showTransactions)
        {

            HashMap<String,String> transactions=this.user.showTransactions();
            System.out.println(transactions);
            JPanel panel=new JPanel();
            for(HashMap.Entry<String,String> entry:transactions.entrySet())
            {
                JTextArea textinfo=new JTextArea(20,20);
                textinfo.setBounds(300,70,120,120);
                textinfo.append(("Card Number:"+entry.getKey()+"\n"+entry.getValue()));
                textinfo.setVisible(true);
                textinfo.setEditable(false);
                JScrollPane scroller=new JScrollPane(textinfo);
                scroller.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setPreferredSize(new Dimension(250, 250));
                panel.add(textinfo);
                //System.out.println(entry.getKey()+entry.getValue());

            }
            frame.add(panel);

        }

  }

}
