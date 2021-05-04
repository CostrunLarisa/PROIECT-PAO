package Laborator3.Exemplul4;

import java.util.Objects;

//import java.Util;
public class City {
    private String name;
    private String district;
    private int inhabitants;
    public City(String name,String district, int inhabitants)
    {
        this.name=name;
        this.district=district;
        this.inhabitants=inhabitants;
    }
    @Override
    public boolean equals(Object object){
        if(this==object)return true;
        if(object==null || object.getClass()!=this.getClass())return false;
        City obj=(City)object;
        if(this.name!=obj.name)return false;
        if(this.inhabitants!=obj.inhabitants)return false;
        if(this.district!=obj.district)return false;
        return true;
    }
    public int hashCode(){
        return Objects.hash(name,district,inhabitants);
    }
}
