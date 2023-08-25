import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;


public class MyFrame extends JFrame implements KeyListener, ActionListener{

    static JLabel label;
    ImageIcon shuttle;
    JLabel scoreLabel;
    static int score = 0;
    Meteor meteor;
    JButton startButton;
    Thread rocksGeneration;
    Thread scoreAnimation;
    File gameOverFile = new File("B:/lavoro/GITHUB 2/LostInSpace/audio/gameOver.wav");
    Clip clip;

    MyFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setResizable(true);
        this.setTitle("SPACEBATTLE");

        startButton = new JButton("START");

        startButton.setBounds(190, this.getHeight()/2 - 50, 90, 50);
        startButton.setFocusable(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(this);
        startButton.setContentAreaFilled(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setFont(new Font("Raleway", BOLD, 18));

        this.add(startButton);

        shuttle = new ImageIcon("B:/lavoro/GITHUB 2/LostInSpace/img/rocket.png");
        Image image = shuttle.getImage();
        Image shuttle1 = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        shuttle = new ImageIcon(shuttle1);  // transform it back
        System.out.println(this.getHeight());

        label = new JLabel();
        label.setBounds(this.getWidth()/2 - 40, this.getHeight()-110, 45, 70);
        label.setIcon(shuttle);
        label.setHorizontalAlignment(JLabel.CENTER);


        scoreLabel = new JLabel(score + "");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 100, 35);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
        scoreLabel.setFont(new Font("Tahoma", PLAIN, 19));

        AudioInputStream gameOver = AudioSystem.getAudioInputStream(gameOverFile);
        clip = AudioSystem.getClip();
        clip.open(gameOver);


        this.add(scoreLabel);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startButton){

            Meteor.endCondition = false;
            startButton.setVisible(false);
            this.getContentPane().repaint();
            rocksGeneration = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        meteor = new Meteor(MyFrame.this);
                        try
                        {
                            if(Meteor.endCondition){
                                clip.setMicrosecondPosition(0);
                                clip.start();
                                startButton.setVisible(true);
                                Meteor.meteors.clear();
                                rocksGeneration.stop();
                            }

                            Thread.sleep(1900);

                        }catch (Exception e)
                        {
                            System.out.println("Exception");
                        }
                    }
                }
            });
            rocksGeneration.start();

            scoreAnimation = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        score++;
                        scoreLabel.setText(score + "");
                        try
                        {
                            if(Meteor.endCondition){
                                System.out.println("Punteggio: " + score);
                                score = 0;
                                scoreLabel.setText(score + "");
                                scoreAnimation.stop();
                            }

                            Thread.sleep(100);

                        }catch (Exception e)
                        {
                            System.out.println("Exception");
                        }
                    }
                }
            });
            scoreAnimation.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a' -> {
                label.setLocation(label.getX() - 10, label.getY());
                if (label.getX() < 0) {
                    label.setLocation(-15, label.getY());
                }
            }
            case 'd' -> {
                label.setLocation(label.getX() + 10, label.getY());
                if (label.getX() > 430) {
                    label.setLocation(430, label.getY());
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
