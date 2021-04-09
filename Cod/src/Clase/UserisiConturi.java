package Clase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.*;

public class UserisiConturi {
    HashMap<String,List<String>>  contInfo=new HashMap<String,List<String>>();

    UserisiConturi(){
        try{
            File conturi=new File("ConturiSiUseri.txt");
            Scanner reader=new Scanner(conturi);
            //Citesc informatiile din fisier linie cu linie
            while(reader.hasNextLine()){
                String[] id=reader.nextLine().split(",");
                //Daca user-ul nu deja se afla in HashMap atunci adaug o cheia noua impreuna
                //cu IBAN-ul contului
                if(!this.contInfo.containsKey(id[0]))
                {
                    List<String> aux=new ArrayList<String>();
                    aux.add(id[1]);
                    this.contInfo.put(id[0],aux);

                }
                else{
                    this.contInfo.get(id[0]).add(id[1]);
                }
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Eroare la citirea datelor din fisier!");
        }
    }

    public HashMap<String, List<String>> getContInfo() {
        return contInfo;
    }
}
