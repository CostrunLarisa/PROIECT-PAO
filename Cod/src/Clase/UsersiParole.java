package Clase;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UsersiParole {
    HashMap<String,String> loginInfo=new HashMap<String,String>();
    UsersiParole(){

        try{
            File useri=new File("Useri.txt");
            Scanner reader=new Scanner(useri);
            while(reader.hasNextLine()){
                String[] id=reader.nextLine().split(",");
                loginInfo.put(id[0],id[1]);
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Eroare la citirea datelor din fisier!");
        }
    }

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }
    public void addUser(String ID,String password){
        loginInfo.put(ID,password);
    }
}
