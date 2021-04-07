package Clase;
import java.util.*;

public interface OperatiiContBancar {
    boolean autentificare();
    double verificareSold();
    HashMap<String,String> vizualizeazaTranzactii();
    void adaugaSuma();
    void efectueazaPlata(String destinatar,double suma);
    Set<Card> cardurileMele();
    Set<Cont> conturileMele();
    void adaugaCardNou(Card c);
    void alimenteazaCard(Card c,Double suma);
}
