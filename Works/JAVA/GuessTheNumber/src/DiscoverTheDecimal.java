import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;
public class DiscoverTheDecimal extends JFrame implements ActionListener{
    JButton control;
    ImageIcon controlIcon;
    JTextField insertNumberField;
    JLabel numberToGuess;
    Thread discoverNumber;
    JLabel finalResult;
    JButton startTheGame;
    DiscoverTheDecimal(){
        this.setTitle("Guess The Decimal Number");
        this.setSize(400, 200);
        this.setCursor(Cursor.HAND_CURSOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(true);
        this.getContentPane().setBackground(Color.WHITE);

        //startGame button style
        startTheGame = new JButton("START");
        startTheGame.setBounds(310, 130, 80, 30);
        startTheGame.setHorizontalAlignment(JButton.CENTER);
        startTheGame.setFocusPainted(false);
        startTheGame.setBorderPainted(false);
        startTheGame.setContentAreaFilled(false);
        startTheGame.setBackground(Color.WHITE);
        startTheGame.addActionListener(this);

        //container of our number
        insertNumberField = new JTextField();
        insertNumberField.setBounds(50, 60, 100, 30);
        insertNumberField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        insertNumberField.setHorizontalAlignment(JTextField.CENTER);
        insertNumberField.setFont(new Font(Font.SANS_SERIF, PLAIN, 15));


        control = new JButton();
        control.addActionListener(this);


        //icon resized
        controlIcon = new ImageIcon("img/check.png");
        Image newScaledControlImage = controlIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon controlIcon2 = new ImageIcon(newScaledControlImage);
        control.setIcon(controlIcon2);

        //control container
        control.setBounds(185, 60, 30, 30);
        control.setBackground(Color.WHITE);
        control.setFocusPainted(false);

        //Container of the number to guess
        numberToGuess = new JLabel();
        numberToGuess.setBounds(250, 60, 100, 30);
        numberToGuess.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        numberToGuess.setHorizontalAlignment(JLabel.CENTER);
        numberToGuess.setFont(new Font(Font.SANS_SERIF, PLAIN, 15));
        numberToGuess.setText("");

        //answer box
        finalResult = new JLabel();
        finalResult.setBounds(125, 20, 150, 30);
        finalResult.setVisible(false);
        finalResult.setFont(new Font(Font.SANS_SERIF, PLAIN, 15));
        finalResult.setHorizontalAlignment(JLabel.CENTER);

        this.add(startTheGame);
        this.add(finalResult);
        this.add(numberToGuess);
        this.add(insertNumberField);
        this.add(control);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startTheGame){

            finalResult.setVisible(false);
            numberToGuess.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            numberToGuess.setText(randomDecimalNumber() + "");
        }

        try{
            if(e.getSource() == control){

                String bNumber = Integer.toBinaryString(Integer.parseInt(numberToGuess.getText()));
                int trueBinaryNumber = Integer.parseInt(bNumber);
                int myNumber = Integer.parseInt(insertNumberField.getText());

                //if the answer is correct
                if(myNumber == trueBinaryNumber){
                    numberToGuess.setText("");

                    //animation to show the correct number
                    discoverNumber = new Thread(() -> {
                        while(true){
                            for (int i = 0; i < bNumber.length(); i++) {
                                numberToGuess.setText(numberToGuess.getText() + bNumber.charAt(i));
                                try
                                {

                                    Thread.sleep(1000);
                                }catch (Exception e12)
                                {
                                    System.out.println("Exception");
                                }

                            }
                            numberToGuess.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
                            finalResult.setText("WELL DONE");
                            finalResult.setForeground(Color.GREEN);
                            finalResult.setVisible(true);
                            discoverNumber.stop();

                        }

                    });
                    discoverNumber.start();
                }else{
                    numberToGuess.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    finalResult.setText("WRONG ANSWER");
                    finalResult.setForeground(Color.RED);
                    finalResult.setVisible(true);
                }

            }
        }catch (NumberFormatException e1){
            System.out.println("You must press start and type a number");
        }

    }

    public int randomDecimalNumber(){

        return (int)Math.floor(Math.random() * (1000 + 1));

    }

}
