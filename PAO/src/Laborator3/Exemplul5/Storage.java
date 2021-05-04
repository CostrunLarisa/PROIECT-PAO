package Laborator3.Exemplul5;

public abstract  class Storage {
    protected int size;
    abstract void save();
    abstract void delete();
    public void open(){
        System.out.println("Open storage");
    }
}
