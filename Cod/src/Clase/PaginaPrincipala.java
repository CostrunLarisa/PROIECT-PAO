package Clase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.*;
public class PaginaPrincipala implements ActionListener{
    //Butoanele de logare si resetare

    HashMap<String,String> loginInfo=new HashMap<String,String>();
    JFrame frame=new JFrame();
    JButton loginButton=new JButton(("Login"));
    JButton resetButton=new JButton(("Reset"));
    JTextField userID= new JTextField();
    JPasswordField parolaUser=new JPasswordField();
    JLabel userIDLabel=new JLabel("ID utilizator");
    JLabel parolaUserLabel=new JLabel("Parola");
    JLabel messageLabel= new JLabel();
    boolean logged=false;
    WindowListener winLis=new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
            Frame frame = (Frame) e.getSource();

        }

    };

    //Field-urile pentru userID si parola
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
        UserisiConturi ct=new UserisiConturi();
        HashMap<String, List<String>> conturi=ct.getContInfo();
        List<String>conturiTotale=conturi.get(id);
        Set<String> conturiUser=getConturi(conturiTotale);
        Set<Cont> conturiUserCreate=new HashSet<Cont>();
        for(String cont:conturiUser) {
            //Conturile sunt retinute in fisier, informatiile contului fiind despartite prin spatii
            //Primul atribut va reprezenta tipul Contului:standard sau de economii
            //Informatiile contului sunt despartite de lista de carduri printr-o virgula
            String[] linie=cont.split(",");
            System.out.println(linie[0]);
            //Obtin lista de carduri
            String[] listaCarduri=linie[1].split(" ");

            //Obtin informatiile contului
            String[] values = cont.split(" ");
            Integer size = values.length;
            //Iau primul atribut al contului pentru a verifica tipul acestuia
            String tip = values[1];
            Cont contCurent;
            String iban=values[0];
            String valuta=values[2];
            Double soldDisponibil=Double.parseDouble(values[3]);
            Double soldContabil=Double.parseDouble(values[4]);
            Double soldNeautorizat=Double.parseDouble(values[5]);
            Double sumaBlocata=Double.parseDouble(values[6]);
            Double dobanda=Double.parseDouble(values[7]);
            //Al doilea atribut imi confera lista de carduri
            Set<Card> carduri=getCarduri(listaCarduri);
            if(tip.equals("Standard")){


                //Avand lista de id-uri pentru carduri voi cauta in fisierul corespunzator
                //Toate cardurile utilizatorului si voi forma obiecte de tip Card
                //Pe care le voi adauga intr-o colectie
                contCurent=new ContCurent(valuta,soldDisponibil,soldNeautorizat,sumaBlocata,dobanda);
                contCurent.setIban(iban);

            }
            else{
                Integer limitaCont=Integer.parseInt(values[8]);
                contCurent=new ContEconomii(valuta,soldDisponibil,soldNeautorizat,sumaBlocata,dobanda,limitaCont);
                contCurent.setIban(iban);
            }
            if(carduri.size()!=0){
                contCurent.setCarduri(carduri);
            }
            conturiUserCreate.add(contCurent);
        }
        utiliz.setConturi(conturiUserCreate);
    }
    public Set<String> getConturi(List<String> conturiUseri){
        Set<String>conturi=new HashSet<>();
        try{
            File content=new File("Conturi.txt");
            Scanner reader=new Scanner(content);
            //Citesc informatiile din fisier linie cu linie
            while(reader.hasNextLine()){
                String cnt=reader.nextLine();
                conturi.add(cnt);
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Eroare la citirea datelor din fisier!");
        }
        return conturi;
    }
    public Set<Card> getCarduri(String[] carduriUser)
    {
        Set<Card> carduriGasite=new HashSet<Card>();
        try{
            File carduri=new File("Carduri.txt");
            Scanner reader=new Scanner(carduri);
            //Citesc informatiile din fisier linie cu linie
            while(reader.hasNextLine()){
                String[] card=reader.nextLine().split(" ");
                if(Arrays.asList(carduriUser).contains(card[0]))
                {
                    //Cardurile sunt retinute in fisier,pe o line, informatiile cardului fiind despartite prin spatii

                    String Word=card[0];
                    Card cardAux;

                    if((Word.charAt(0))=='4')
                    {
                        cardAux=new MasterCard(card[1],card[2],Integer.parseInt(card[3]),card[4],Double.parseDouble(card[5]));
                        cardAux.setNumarCard(Word);
                        cardAux.setDataEmitere(card[6]);
                        cardAux.setDataExpirare(card[7]);
                        cardAux.setCodSecuritate(Integer.parseInt(card[8]));
                    }
                    else if((Word.charAt(0))=='5')
                    {
                        cardAux=new Visa(card[1],card[2],Integer.parseInt(card[3]),card[4],Double.parseDouble(card[5]),Double.parseDouble(card[9]));
                        cardAux.setNumarCard(Word);
                        cardAux.setDataEmitere(card[6]);
                        cardAux.setDataExpirare(card[7]);
                        cardAux.setCodSecuritate(Integer.parseInt(card[8]));
                    }
                    else{
                        cardAux=new CardCumparaturi(card[1],card[2],Integer.parseInt(card[3]),card[4],Double.parseDouble(card[5]),Double.parseDouble(card[9]),Double.parseDouble(card[10]),Double.parseDouble(card[11]));
                        cardAux.setNumarCard(Word);
                        cardAux.setDataEmitere(card[6]);
                        cardAux.setDataExpirare(card[7]);
                        cardAux.setCodSecuritate(Integer.parseInt(card[8]));

                    }
                    carduriGasite.add(cardAux);
                }

            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Eroare la citirea datelor din fisier!");
        }
        return carduriGasite;
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
                    setConturi(userID.getText());
                    if(loginInfo.get(loginUser).equals(loginPassword)){
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Bine ati venit!");
                        this.utiliz.setParola(loginPassword);
                        this.utiliz.setLogged();
                        this.logged=true;
                        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        frame.dispose();
                        PaginaNoua pagina=new PaginaNoua(utiliz.getConturi(),utiliz);
                        System.out.println(pagina);             //Din pagina de autentificare se va deschide pagina cu alegerea contului
                    }
                    else{
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Parola incorecta!");
                    }
                }
            }
    }

    public boolean isLogged() {
        return logged;
    }


}
