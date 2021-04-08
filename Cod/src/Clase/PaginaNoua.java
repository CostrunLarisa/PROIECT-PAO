package Clase;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PaginaNoua implements ActionListener {
    private Set<Cont> Conturi;
    JFrame frame=new JFrame();
    JLabel messageLabel= new JLabel();
    PaginaNoua(Set<Cont>Conturi,Utilizator utiliz){
        messageLabel.setBounds(125,250,250,35);
        messageLabel.setText("Alegeti un cont pentru urmatoarele actiuni");
        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);


        for(Cont ct :Conturi){
            JButton button=new JButton(ct.getIban());
            button.setBounds(125,200,100,25);
            button.addActionListener(this);
            button.setFocusPainted(true);
            frame.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                   utiliz.setContulMeu(ct);
                   //System.out.println(utiliz.getContulMeu().getIban());
                }
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {



    }
}
