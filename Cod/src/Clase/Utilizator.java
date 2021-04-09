package Clase;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Clasa utilizator care implementeaza toate operatiile unui cont bancar
public class Utilizator implements OperatiiContBancar{

    private String id;          //Id-ul de logare va reprezenta numele si prenumenele posesorului concatenate
    private String parola;
    private Set<Cont> conturi=new HashSet<Cont>();
    private Cont contulMeu;
    private boolean logged;


    Utilizator(){
        /*
        Card card=new CardDebit("Costrun","Larisa",1234,"RON",0.0);
        Cont ct=new ContEconomii(500.0,0.0,100.0,0.0,card);
        this.conturi.add(ct);*/
        this.logged=false;
    }
    public boolean autentificare(){
        UsersiParole useri=new UsersiParole();
        PaginaPrincipala p=new PaginaPrincipala(useri.getLoginInfo(),this);
        System.out.println(p);
        //this.conturi=conturi[this.getId()];
        if(this.isLogged()==true ){

            return true;
        }
        return false;
    }

    public double verificareSold(){

        return contulMeu.getSoldDisponibil();
    }

    public HashMap<String,String> vizualizeazaTranzactii(){
        HashMap<String,String> tranzactiiCard=new HashMap<String,String>();
        for(Card c :this.contulMeu.getCarduri())
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

    //Verific daca utilizatorul este logat
    public boolean isLogged() {
        return this.logged;
    }

    //Metoda valabila doar pentru cardurile Visa, care pot fi alimentate din conturi
    public String alimenteazaCard(Card c,Double suma){
        Card crd=new Visa();
        //Programul verifica daca am un card de tip Visa; numai acest tip de card poate fi alimentat dintr-un cont
        if(c.getClass()==crd.getClass()){
            try{
                //In cazul in care suma pe care vreau sa o alimentez este mai mare decat suma mea curenta arunc o eroare
                if(suma>this.getContulMeu().getSoldDisponibil())throw new ArithmeticException("Fonduri insuficiente");
                else{
                    //Updatez suma noua din card
                    c.setSumaCurenta(c.getSumaCurenta()+suma);
                    //Extrag din cont suma*comision cu care am alimentat cardul
                    this.getContulMeu().setSoldDisponibil(this.getContulMeu().getSoldDisponibil()-suma*c.getComision());
                    String detalii=String.format("Alimentare card%s",c.getNumarCard());
                    Tranzactie tr=new Tranzactie(suma,detalii,true);
                    Set<Card>carduri=this.contulMeu.getCarduri();
                    //Adaug tranzactia la istoricul cardului
                    for(Card cr:carduri)
                    {
                        //Caut cardul printre cardurile asociate contului
                        if(cr.equals(c)){
                            List<Tranzactie> tranzactii=cr.getTranzactii();
                            tranzactii.add(tr);
                            cr.setTranzactii(tranzactii);
                            break;
                        }
                    }
                    return String.format("Alimentare efectuata cu succes!Noua suma:%s",c.getSumaCurenta());
                }
            }catch(Exception e){
                    System.out.print(e);
            }
        }
        //Pentru orice alta clasa nu pot alimenta cardul
        return "Nu se poate alimenta cardul din contul curent!";
    }
    //Metoda care adauga atat in baza de date cat si in profilul utilizatorului un cont nou
    public void deschideContNou(){

    }
    //Metoda care adauga atat in baza de date cat si in profilul utilizatorului un card nou
    public void adaugaCardNou(Card c){
        this.getContulMeu().AdaugaCard(c);
    }
}
