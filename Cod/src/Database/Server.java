package Database;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Server {

    public static HashMap<String, String> getUsers(){
        HashMap<String,String> loginInfo=new HashMap<String,String>();
        try{
            Connection myConn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","server2021");
            Statement myStmt=myConn.createStatement();
            ResultSet myRs=myStmt.executeQuery("select * from user");

            while(myRs.next()){
                loginInfo.put(myRs.getString("username"),myRs.getString(("password")));
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return loginInfo;
    }
    public static Set<String> getAccounts(String username){
        Set<String> accounts = new HashSet<>();
        try{
            Connection myConn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","server2021");
            Statement myStmt=myConn.createStatement();
            ResultSet myRs=myStmt.executeQuery("select * from currentaccount");

            ResultSetMetaData rsmd = myRs.getMetaData();
            int colNum = rsmd.getColumnCount();

            while(myRs.next()){
                String row="";
                if((myRs.getString("user")).equals(username)){

                    row = row + myRs.getString("user") +"," + myRs.getString("idaccount")+","+
                            myRs.getString("type")+","+myRs.getString("valute")+","+
                            myRs.getString("availableDeposit")+","+myRs.getString("accountableDeposit")+","+
                            myRs.getString("unauthorizedDeposit")+","+myRs.getString("blockedValue")+","+
                            myRs.getString("interest")+","+myRs.getString("name")+","+myRs.getString("last-name");

                    if(!myRs.getString("type").equals("Standard")) {

                        Statement myStmtAux=myConn.createStatement();
                        String statement = "select * from accountofeconomies where idaccountofeconomies="+ "\""+myRs.getString("idAccount") +"\"";
                        ResultSet myRsAux=myStmtAux.executeQuery(statement);

                        while(myRsAux.next()){
                        row = row +","+myRsAux.getString("accountlimit");
                        }
                    }
                    accounts.add(row);
                }
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return accounts;
    }
    public static Set<String> getCards(String iban){
        Set<String> cards = new HashSet<>();
        try{
            Connection myConn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","server2021");
            Statement myStmt=myConn.createStatement();
            String statement = "select * from card where idaccount="+"\""+iban+"\"";
            ResultSet myRs=myStmt.executeQuery(statement);
            while(myRs.next()){
                String info="";
                Statement myStmtAux=myConn.createStatement();
                String statementAux = "select * from currentaccount where idAccount="+"\""+iban+"\"";
                ResultSet myRsAux=myStmtAux.executeQuery(statementAux);
                String Word = myRs.getString("idcard");
                System.out.println(myRs.getString("currentValue"));
                if(myRsAux.next()){
                info = info + myRs.getString("idaccount") +","+myRs.getString("idcard")+","+
                        myRsAux.getString("name") + ","+ myRsAux.getString("last-name")+","+
                        myRs.getString("pin")+","+myRs.getString("valute")+","+
                        myRs.getString("currentValue")+","+myRs.getString("emissionDate")+
                        ","+myRs.getString("expirationDate")+","+myRs.getString("securityCode");
                }
               if ((Word.charAt(0)) == '5') {
                   Statement stmt2=myConn.createStatement();
                   String statement2= "select * from visa where idvisa="+"\'"+Word+"\'";
                   ResultSet rs2=stmt2.executeQuery(statement2);
                   if(rs2.next()){
                       info=info+","+rs2.getString("comission");
                   }
               }
               else if ((Word.charAt(0)) != '5' && (Word.charAt(0)) != '4'){
                   Statement stmt3=myConn.createStatement();
                   String statement3="select * from cardshopping where idcardShopping="+"\""+Word+"\"";
                   ResultSet rs3=stmt3.executeQuery(statement3);
                   if(rs3.next()){
                       info=info+","+rs3.getString("interest")+","+rs3.getString("minimumPayment")+","+rs3.getString("minimumCharge");
                   }
               }
               System.out.println(info);
               cards.add(info);
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return cards;
    }
    public static Set<String> getTransactions(String cardNumber){
        Set<String> transactions=new HashSet<>();
        try{
            Connection myConn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","server2021");
            Statement myStmt=myConn.createStatement();
            String statement = "select * from transaction where idcard="+"\""+cardNumber+"\"";
            ResultSet myRs=myStmt.executeQuery(statement);

            while(myRs.next()){
                String info = "";
                info = info + cardNumber +","+myRs.getString("data")+","+myRs.getString("value")+","+
                        myRs.getString("details")+","+myRs.getString("charged");
                transactions.add(info);
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return transactions;
    }
    public static void insert(String statement){
        try{
            Connection mycn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","server2021");
            Statement stmt = mycn.createStatement();
            stmt.executeUpdate(statement);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void update(String statement){
        try{
            Connection mycn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","server2021");
            Statement stmt = mycn.createStatement();
            stmt.executeUpdate(statement);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
