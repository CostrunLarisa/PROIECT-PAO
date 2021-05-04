package Clase;
import java.util.*;

public interface OperationsBankAccount {
    boolean login();
    double checkDeposit();
    HashMap<String,Set<Transaction>> showTransactions();
    void addValue();
    void payment(String receiver,double value);
    Set<Card> myCards();
    Set<Account> myAccounts();
    void addNewCard(Card c);
    String chargeCard(Card c,Double value);
    void openNewAccount();
}
