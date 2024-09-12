import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MyFrame implements ActionListener {

    public JPanel panel1;
    private JLabel textArea;
    private JButton equal;
    private JButton add;
    private JButton sub;
    private JButton del;
    private JButton multiply;
    private JButton divide;
    private JButton cancel;
    private JLabel result;
    private JButton add0;
    private JButton add2;
    private JButton add1;
    private JButton add3;
    private JButton add4;
    private JButton add5;
    private JButton add6;
    private JButton add7;
    private JButton add8;
    private JButton add9;
    String finalResult = "";
    Float firstNumber = 0.0f;
    Float secondNumber = 0.0f;
    String typeOfOperation;
    LinkedList<JButton> buttons = new LinkedList<>();
    MyFrame(){

        for (Component c: panel1.getComponents()) {
            if(c.getClass().getSimpleName().equals("JButton")){
                buttons.add((JButton) c);
            }
        }

        for (JButton b: buttons) {
            b.addActionListener(this);
        }

        textArea.setText("");

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (JButton b: buttons) {
            if (e.getSource() == b) {
                switch (b.getText()) {
                    case "0":
                        textArea.setText(textArea.getText() + 0);
                        break;
                    case "1":
                        textArea.setText(textArea.getText() + 1);
                        break;
                    case "2":
                        textArea.setText(textArea.getText() + 2);
                        break;
                    case "3":
                        textArea.setText(textArea.getText() + 3);
                        break;
                    case "4":
                        textArea.setText(textArea.getText() + 4);
                        break;
                    case "5":
                        textArea.setText(textArea.getText() + 5);
                        break;
                    case "6":
                        textArea.setText(textArea.getText() + 6);
                        break;
                    case "7":
                        textArea.setText(textArea.getText() + 7);
                        break;
                    case "8":
                        textArea.setText(textArea.getText() + 8);
                        break;
                    case "9":
                        textArea.setText(textArea.getText() + 9);
                        break;
                    case "C":
                        textArea.setText("");
                        result.setText("");
                        break;
                    case "D":
                        if(textArea.getText().length() != 0){
                            textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
                        }else{
                            System.out.println("Non c'è nulla da cancellare");
                        }

                        break;
                    case "%":
                        firstNumber = Float.parseFloat(textArea.getText());
                        textArea.setText("");
                        typeOfOperation = "%";
                        break;
                    case "=":
                        secondNumber = Float.parseFloat(textArea.getText());
                        makeOperation(typeOfOperation, firstNumber, secondNumber);
                        result.setText(finalResult);
                        break;
                    case "/":
                        firstNumber = Float.parseFloat(textArea.getText());
                        textArea.setText("");
                        typeOfOperation = "/";
                        break;
                    case "x":
                        firstNumber = Float.parseFloat(textArea.getText());
                        textArea.setText("");
                        typeOfOperation = "x";
                        break;
                    case "-":
                        firstNumber = Float.parseFloat(textArea.getText());
                        textArea.setText("");
                        typeOfOperation = "-";
                        break;
                    case "+":
                        firstNumber = Float.parseFloat(textArea.getText());
                        textArea.setText("");
                        typeOfOperation = "+";
                        break;
                    case ",":
                        textArea.setText(textArea.getText() + ".");
                        break;
                    default:
                        System.out.println("Ancora da assegnare");
                }
            }
        }
    }

    private void makeOperation(String type, float a, float b){

        switch (type){
            case "/":
                if(b != 0){
                    finalResult = a/b + "";
                }else{
                    System.out.println("Impossibile dividere per zero");
                }
                break;
            case "x":
                    finalResult = a*b + "";
                    break;
            case "-":
                    finalResult = a-b + "";
                    break;
            case "+":
                    finalResult = a+b + "";
                    break;
            case "%":
                    finalResult = a%b + "";
                    break;
            default:
                    System.out.println("Operazione non conosciuta");
                    break;
        }
    }

}
