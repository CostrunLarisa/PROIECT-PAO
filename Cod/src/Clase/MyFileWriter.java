package Clase;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MyFileWriter {
    MyFileWriter(String action){
        try {
            FileWriter myWriter = new FileWriter("Audit.csv",true);
            String separator=",";
            Date data=new java.util.Date();
            String line[]={action,String.valueOf(data)};
            myWriter.append(action);
            myWriter.append(separator);
            myWriter.append(String.valueOf(data));
            myWriter.append("\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    MyFileWriter(String action,String thr){
        try {
            FileWriter myWriter = new FileWriter("Audit.csv",true);
            String separator=",";
            Date data=new java.util.Date();
            String line[]={action,String.valueOf(data)};
            myWriter.append(action);
            myWriter.append(separator);
            myWriter.append(String.valueOf(data));
            myWriter.append(separator);
            myWriter.append(thr);
            myWriter.append("\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    MyFileWriter(String path, List<String> items){
        try {
            FileWriter myWriter = new FileWriter(path,true);
            String separator=",";
            for(String item:items) {
                myWriter.append(item);
                if(!item.equals(items.get(items.size()-1)))myWriter.append(separator);
            }
            myWriter.append("\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
