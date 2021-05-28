package Clase;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class OptionPage implements ActionListener {
    private Set<Account> Accounts;
    JFrame frame=new JFrame();
    JLabel messageLabel= new JLabel();
    private User utiliz;
    OptionPage(Set<Account> Accounts, User utiliz){
        messageLabel.setBounds(100,30,250,35);
        messageLabel.setText("Alegeti un cont pentru urmatoarele actiuni");
        frame.add(messageLabel);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        this.utiliz=utiliz;
        int dimY=0;
        for(Account ct : Accounts){
            JButton button=new JButton(ct.getIban());
            button.setBounds(125,80+dimY,180,25);
            button.addActionListener(this);
            button.setFocusPainted(true);
            dimY=dimY+30;
            frame.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                   utiliz.setMyAccount(ct);
                   //System.out.println(utiliz.getContulMeu().getIban());
                }
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        frame.dispose();
        Services services =new Services(this.utiliz);
    }
}
