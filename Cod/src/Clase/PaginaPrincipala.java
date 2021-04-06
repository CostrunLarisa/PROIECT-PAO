package Clase;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class PaginaPrincipala implements ActionListener{
    //Butoanele de logare si resetare
    //Field-urile pentru userID si parola
    HashMap<String,String> loginInfo=new HashMap<String,String>();
    JFrame frame=new JFrame();
    JButton loginButton=new JButton(("Login"));
    JButton resetButton=new JButton(("Reset"));
    JTextField userID= new JTextField();
    JPasswordField parolaUser=new JPasswordField();
    JLabel userIDLabel=new JLabel("ID utilizator");
    JLabel parolaUserLabel=new JLabel("Parola");
    JLabel messageLabel= new JLabel();
    Utilizator utiliz;
    //HashMap<String,String> login
    PaginaPrincipala(HashMap<String,String> loginInfoOriginal,Utilizator utiliz){
        loginInfo.putAll(loginInfoOriginal);
        this.utiliz=utiliz;
        userIDLabel.setBounds(50,100,75,25);
        userID.setBounds(125,100,200,25);
        parolaUserLabel.setBounds(50,150,200,25);
        parolaUser.setBounds(125,150,200,25);
        messageLabel.setBounds(125,250,250,35);

        frame.add(userIDLabel);
        frame.add(userID);
        frame.add(parolaUser);
        frame.add(parolaUserLabel);
        frame.add(messageLabel);
        frame.add(loginButton);
        frame.add(resetButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    @Override
    public void actionPerformed(ActionEvent e){
            if(e.getSource()==resetButton){
                userID.setText("");
                parolaUser.setText("");
            }
            if(e.getSource()==loginButton){
                String loginUser=userID.getText();
                String loginPassword=String.valueOf(parolaUser.getPassword());

                if(loginInfo.containsKey(loginUser))
                {
                    this.utiliz.setId(loginUser);
                    if(loginInfo.get(loginUser).equals(loginPassword)){
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Bine ati venit!");
                        this.utiliz.setParola(loginPassword);
                        this.utiliz.setLogged();
                        PaginaNoua pagina=new PaginaNoua(this.utiliz.getConturi(),this.utiliz);
                        System.out.println(pagina);             //Din pagina de autentificare se va deschide pagina cu alegerea contului
                    }
                    else{
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Parola incorecta!");
                    }
                }
            }
    }
}
