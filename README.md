# Bank-Account Java Application (Starting Date: 6.04.2021 - Expected finishing date: 28.05.2021)
<br/>

> # Classes

<ol>
 <li>Account
 <ul>
  <li>Iban</li>
  <li>Valute</li>
  <li>AvailableDeposit</li>
  <li>AccountableDeposit</li>
  <li>UnauthorizedDeposit</li>
  <li>BlockedValue</li>
  <li>Interest</li>
  <li>Type</li>
  <li>Name</li>
  <li>LastName</li>
  <li>Cards</li>
 </ul>
 </li>
 <li>AccountOfEconomies
  <br/>Derived class from class Account, specific features:
 <ul>
  <li>AccountLimit</li>
  <li>MinValue</li>
 </ul>
 </li>
 <li>CurrentAccount
 <br/>Derived class from class Account, it has the same features, but an object Account cannot be instantiated due to the abstract class.
 </li>
 <li>Card
  <ul>
   <li>CardNumber</li>
   <li>EmissionDate</li>
   <li>ExpirationDate</li>
   <li>Name</li>
   <li>LastName</li>
   <li>Pin</li>
   <li>Comission</li>
   <li>Valute</li>
   <li>CurrentValue</li>
   <li>SecurityCode</li>
   <li>Contactelss</li>
  </ul>
 </li>
 <li>CardDebit</li>
  <br/>Derived class from class Card, it has the same features.
 <li>CardShopping</li>
  <br/>Derived class from class Card, it has the same features.
 <li>MasterCard</li>
  <br/>Derived class from class CardDebit, it has the same features.
 <li>Visa</li>
  <br/>Derived class frm class CardDebit, it has the same features. In addition to them:
 <ul>
  <li>comisionPaypal=0.2</li>
 </ul>
 <li>User</li>
 <ul>
   <li>Id</li>
   <li>Password</li>
   <li>Accounts</li>
   <li>MyAccount</li>
   <li>Logged</li>
 </ul>
 <li>UserAndPasswords</li>
 <ul>
  <li>loginInfo</li>
 </ul>
 <li>Transaction</li>
 <ul>
   <li>data</li>
   <li>sumaTranzac</li>
    <li>detalii</li>
    <li>decontat</li>
 </ul>
 <li>LoginPage</li>
  <li>loginInfo</li>
   <li>frame</li>
   <li>loginButton</li>
   <li>resetButton</li>
    <li>userID</li>
    <li>passwordUse</li>
    <li>userIDLabel</li>
    <li>passwordUserLabel</li>
    <li>messageLabel</li>
    <li>logged</li>
 <li>OptionPage</li>
 <ul>
  <li>Accounts</li>
  <li>frame</li>
   <li>messageLabel</li>
   <li>utiliz</li>
 </ul>
 <li>OperationsBankAccount</li>
 <li>Program</li>
 <li>Services</li>
</ol>

> # Stages

## Part 1 - 14.04.2021
The program reads all the relevant data from .csv files("Transactions.csv","Cards.csv","UsersAndPasswords.cv","Accounts.csv") and creates the bank-account which one user holds. 
When the application is opened a Login window will be displayed where the user has to introduce his user ID and password in order to proceed operations in his account.

The user ID is searched in the "UsersAndPasswords.csv" file and if found, then the password is checked. If the program has succesfully reached this point, then all the information about the bank account is searched in files followed by proper objects instantiations.

E.g : All objects from the Card class are created when an account is chosen. Hence, they will be searched in the "Cards.csv" file by how it follows:
For MasterCards:
   Account IBAN -> Card Number -> Name -> Last Name -> Pin -> Valute-> Current Money Amount -> Emission Date -> Expiration Date -> Security Code
   
For Visa:
  Account IBAN -> Card Number -> Name -> Last Name -> Pin -> Valute-> Current Money Amount -> Emission Date -> Expiration Date -> Security Code -> Commission Paypal
  
For CardShopping
   Account IBAN -> Card Number -> Name -> Last Name -> Pin -> Valute-> Current Money Amount -> Valute -> Emission Date -> Expiration Date -> Security Code -> Interest -> Minimum Payment Value (if the Card is not added by the user, otherwise by default it's value = 200.0) -> Minimum Charging Value if the Card is not added by the user, otherwise by default it's value = 300.0)
   When one object is created, it's current money amount is set to the minimum charging value (provided by the bank). 
  
 For each card there will be a list of transactions which will be read from the "Transactions.csv" as it follows:
  Card Number -> Date -> Mouney Amount -> Details -> Charged

Services
 After all data is read, the user can 
 
