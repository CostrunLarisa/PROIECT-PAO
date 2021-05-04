package Clase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileReader {

    public Set<String> info=new HashSet<>();
    FileReader(String path){
        try{
            File content=new File(path);
            Scanner reader=new Scanner(content);
            //Reading the information from the file line by line
            while(reader.hasNextLine()){
                String cnt=reader.nextLine();
                String[] features=cnt.split(",");

                    this.info.add(cnt);

            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Error while reading the data from the file!");
        }
    }

    public Set<String> getInfo() {
        return info;
    }
}
