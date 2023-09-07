import javax.swing.*;

public class Main {

    public static void main(String[] args){

        MyFrame form = new MyFrame();
        JFrame frame = new JFrame("Calcolatrice");
        // frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/icon.png"));
        frame.setContentPane(form.panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }

}