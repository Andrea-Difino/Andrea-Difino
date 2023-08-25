import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Meteor extends JLabel{
    int size;
    int x;
    int y;
    Thread movingAnimation;
    public static boolean endCondition;
    MyFrame frame;
    public static LinkedList<Meteor> meteors = new LinkedList<>();
    ImageIcon meteor = new ImageIcon("B:/lavoro/GITHUB 2/LostInSpace/img/meteor.png");
    Meteor(MyFrame frame){
        this.frame = frame;
        this.size = 30 + (int)((Math.floor(Math.random() * ((100 - 30)+ 1)) ));

        Image image = meteor.getImage();
        Image newimg = image.getScaledInstance(this.size, this.size,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        meteor = new ImageIcon(newimg);  // transform it back
        this.setIcon(meteor);

        this.x = (int)((Math.floor(Math.random() * ((frame.getWidth()-50)+ 1)) ));
        this.y = -50;
        this.setBounds(this.x, this.y, this.size, this.size);

        meteors.add(this);
        frame.add(this);
        frame.getContentPane().repaint();

        moveMeteor(this, this.x);
    }

    void moveMeteor(Meteor meteorToMove, int randomX){
        movingAnimation = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    meteorToMove.setLocation(randomX,  meteorToMove.y);
                    meteorToMove.y += 1;
                    checkExplosion();
                    if(meteorToMove.y >= 500){
                        for (Meteor m: meteors) {
                            if(m.y >= 500){
                                frame.remove(m);
                            }
                        }
                    }
                    try
                    {
                        if(endCondition){
                            movingAnimation.stop();
                        }

                        Thread.sleep(10);
                    }catch (Exception e)
                    {
                        System.out.println("Exceptin");
                    }
                }
            }
        });
        movingAnimation.start();
    }

    public void checkExplosion(){

        int xPointDRocket = MyFrame.label.getX() + MyFrame.label.getWidth();
        int xPointLRocket = MyFrame.label.getX();

        for (Meteor m: meteors) {
            if(m.y + m.size == 390 && ( ( (m.x + m.size) >= xPointLRocket && (m.x + m.size) <= xPointDRocket) || (m.x <= xPointDRocket && m.x >= xPointLRocket)))
            {
                endCondition = true;
                for (Meteor m1: meteors) {
                    frame.remove(m1);
                }
            }
        }
    }

}
