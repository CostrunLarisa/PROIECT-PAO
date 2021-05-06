package Clase;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//The goal of this class is to obtain the connection data for each user
public class UsersAndPasswords {
    HashMap<String,String> loginInfo=new HashMap<String,String>();
    UsersAndPasswords(){

        try{
            File useri=new File("Users.csv");
            Scanner reader=new Scanner(useri);
            while(reader.hasNextLine()){
                String[] id=reader.nextLine().replaceAll("\"","").split(",");
                id[0].replaceAll("\"","");
                id[1].replaceAll("\"","");
                loginInfo.put(id[0],id[1]);
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Error while reading the data from the file!");
        }
    }

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }
    public void addUser(String ID,String password){
        loginInfo.put(ID,password);
    }
}
