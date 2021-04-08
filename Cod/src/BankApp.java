import javax.swing.*;

public class BankApp extends JFrame{
    private JPanel panel1;

    public BankApp(String titlu){
        super(titlu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
    }

    public static void main(String[] args){
        JFrame frame = new BankApp("Conturile mele");
        frame.setVisible(true);
    }
}
