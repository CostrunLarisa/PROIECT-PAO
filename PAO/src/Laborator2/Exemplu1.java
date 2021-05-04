package Laborator2;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Exemplu1 {
    public static void main(String[] args){
        String[] cities={"Bucuresti","Timisoara","Constanta","Pitesti"};
        System.out.println(cities.length);
        Arrays.sort(cities);
        System.out.println(Arrays.toString(cities));
        cities[1]="Brasov";
        Arrays.sort(cities,Collections.reverseOrder());
        System.out.println(Arrays.toString(cities));
        Arrays.sort(cities, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                   return (o1.toString().charAt(1)<o2.toString().charAt(1)) ? -1 : 1;
            }
        });
        System.out.println(Arrays.toString(cities));
    }
}
