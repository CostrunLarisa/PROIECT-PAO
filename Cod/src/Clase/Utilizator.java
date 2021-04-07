package Clase;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Utilizator {

    private String id;          //Id-ul de logare va reprezenta numele si prenumenele posesorului concatenate
    private String parola;
    private Set<Cont> conturi=new HashSet<Cont>();
    private Cont contulMeu;
    private boolean logged;


    Utilizator(){
        Card card=new CardDebit("Costrun","Larisa",1234,"RON");
        Cont ct=new ContEconomii(500.0,0.0,100.0,0.0,card);
        this.conturi.add(ct);
        this.logged=false;
    }
    public void autentificare(){
        UsersiParole useri=new UsersiParole();
        PaginaPrincipala p=new PaginaPrincipala(useri.getLoginInfo(),this);
        System.out.println(p);

        /*
        if(this.isLogged()==true){

            PaginaNoua pagina=new PaginaNoua();
            System.out.println(pagina);

        }*/
    }

    public double verificareSold(){

        return contulMeu.getSoldDisponibil();
    }

    public HashMap<String,String> vizualizeazaTranzactii(){
        HashMap<String,String> tranzactiiCard=new HashMap<String,String>();
        for(Card c :contulMeu.getCarduri())
        {
            for(Tranzactie tran:c.getTranzactii())
            {
                tranzactiiCard.put(c.getNumarCard(),tran.getTranzactie());
            }
        }
        return tranzactiiCard;
        /*PaginaNoua pagina=new PaginaNoua(istoric);
        System.out.println(pagina);
        for(Tranzactie tran:istoric){
            System.out.println(tran.getTranzactie());
        }
        */

    }
    public void adaugaSuma(){

    }
    public void efectueazaPlata(String destinatar,double suma){

    }
    public Set<Card> cardurileMele(){
            return this.getContulMeu().getCarduri();
    }
    public Set<Cont> conturileMele(){
        return this.getConturi();
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

    public Set<Cont> getConturi() {
        return conturi;
    }

    public void setConturi(Set<Cont> conturi) {
        this.conturi = conturi;
    }

    public Cont getContulMeu() {
        return contulMeu;
    }

    public void setContulMeu(Cont contulMeu) {
        this.contulMeu = contulMeu;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public boolean isLogged() {
        return this.logged;
    }
    public void AdaugaCardNou(Card c){
        this.getContulMeu().AdaugaCard(c);
    }

    //Metoda valabila doar pentru cardurile Visa, care pot fi alimentate din conturi
    public void alimenteazaCard(Card c,Double suma){
        
    }

}
