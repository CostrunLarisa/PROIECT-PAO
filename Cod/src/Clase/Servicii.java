package Clase;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;



public class Servicii {
    public static void main(String[] args) {

        Utilizator utiliz = new Utilizator();
        utiliz.autentificare();
  /*
        if(utiliz.isLogged()==true)
        {
            PaginaNoua pagina=new PaginaNoua(utiliz.getConturi(),utiliz);
            System.out.println(pagina);             //Din pagina de autentificare se va deschide pagina cu alegerea contului
        }
        System.out.println(utiliz.isLogged());
        JFrame frame=new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        Object lock = new Object();
        Thread t = new Thread() {
            public void run() {
                synchronized(lock) {
                    while (frame.isVisible())
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    System.out.println("Working now");
                }
            }
        };
        t.start();

        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent arg0) {
                synchronized (lock) {
                    frame.setVisible(false);
                    lock.notify();
                }
            }

        });

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


   */
    }
}
