package Clase;
import java.util.*;

public interface OperationsBankAccount {
    boolean login();
    double checkDeposit();
    HashMap<String,Set<Transaction>> showTransactions();
    void addValue();
    String payment(String details,String receiver,Double value,Card c);
    Set<Card> myCards();
    Set<Account> myAccounts();
    void addNewCard(Card c);
    String chargeCard(Card c,Double value);
    void openNewAccount();
}
