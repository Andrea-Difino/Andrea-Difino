import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static final String tooth = "\uD83E\uDDB7" ;
    public static final String shit = "\uD83D\uDCA9" ;
    public static final String pumpkin = "\uD83C\uDF83" ;
    public static final String yummy = "\uD83D\uDE0B" ;
    public static final String brain = "\uD83E\uDDE0" ;
    public static final String umbrella = "\u2602\uFE0F";
    private static int myPoints = 0;
    private static int enemyPoints = 0;
    public static String[] isEqual = new String[12];
    public static int turn = 0; // 0 equals Player 1 - 1 equals Player 2
    public static String[][] field = {
                                        {"⭕️" , "⭕️" , "⭕️" , "⭕️"},
                                        {"⭕️" , "⭕️" , "⭕️" , "⭕️"},
                                        {"⭕️" , "⭕️" , "⭕️" , "⭕️"}
                                     };

    public static void main(String[] args) {

        ArrayList<String> cards = new ArrayList<String>();
        cards.add(tooth);
        cards.add(shit);
        cards.add(pumpkin);
        cards.add(yummy);
        cards.add(brain);
        cards.add(umbrella);
        System.out.println("🖼" + "MEMORI" + "🖼");
        drawField();
        cardPosition(cards);
        //drawField();  disegna le carte nascoste
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n");
            if(turn == 0){
                System.out.println("Player 1 Gioca");
            }else{
                System.out.println("Player 2 Gioca");
            }
            System.out.println("Posizione da scoprire x e a capo y:");
            System.out.println("Prima:");
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            while(x >= field[0].length || y >= field.length){
                System.out.println("Questi numeri non sono ammessi, scegline altri due (x, y)");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            }
            System.out.println("Seconda:");
            int x1 = sc.nextInt() - 1;
            int y1 = sc.nextInt() - 1;
            if(x1 >= field[0].length || y1 >= field.length){
                System.out.println("Questi numeri non sono ammessi, scegline altri due (x, y)");
                x1 = sc.nextInt() - 1;
                y1 = sc.nextInt() - 1;
            }
            drawCards(x, y, x1, y1);
            System.out.println("Player 1" + "  " + "Player 2");
            System.out.println(myPoints + "   " + " - " + "   " + enemyPoints);


            if(end(isEqual)){
                if(myPoints > enemyPoints){
                    System.out.println("Player 1 won");
                    return;
                } else {
                    System.out.println("Player 2 won");
                    return;
                }
            }
        }

    }

    public static void drawCards(int x, int y, int x1, int y1){

        String[] equal = {
                            " ", " "
                         };

        int signal = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                    if( (x == j && y == i) || (x1 == j && y1 == i) ){

                        if(equal[0].equals(" ")){
                            equal[0] = field[i][j];
                        }else{
                            equal[1] = field[i][j];
                        }

                        if(i == 0){
                            isEqual[j] = field[i][j]; // da 0 a 3 compreso
                            System.out.print(isEqual[j]);
                        } else if(i == 1){
                            isEqual[j+4] = field[i][j]; // da 4 a 7 compreso
                            System.out.print(isEqual[j+4]);
                        } else if(i == 2){
                            isEqual[j+8] = field[i][j]; // da 8 a 11 compreso
                            System.out.print(isEqual[j+8]);
                        }

                    } else {

                        if(i == 0){
                            if(isEqual[j] != null){
                                System.out.print(isEqual[j]);
                            } else {
                                System.out.print("⭕️");
                            }
                        } else if(i == 1){
                            if(isEqual[j+4] != null){
                                System.out.print(isEqual[j+4]);
                            } else {
                                System.out.print("⭕️");
                            }
                        } else if(i == 2){
                            if(isEqual[j+8] != null){
                                System.out.print(isEqual[j+8]);
                            } else {
                                System.out.print("⭕️");
                            }
                        }

                    }
            }
            System.out.print("\n");
        }

        if(equal[0].equals(equal[1])){
            signal++;
        } else {
            replaceElement(isEqual, equal[0]);
            replaceElement(isEqual, equal[1]);
        }

        if(signal == 1 && turn == 0){
            myPoints++;

        } else if(signal == 1 && turn == 1){
            enemyPoints++;
        } else {
            if(turn == 0){
                turn++;
            } else {
                turn--;
            }
        }

        equal[0] = " ";
        equal[1] = " ";

    }

    public static void replaceElement(String[] arr, String element){

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != null && arr[i].equals(element)){
                arr[i] = null;
            }
        }
    }

    public static void drawField(){

        for (int i = 0; i < field.length; i++) {
            for ( String card : field[i]) {
                System.out.print(card);
            }
            System.out.print("\n");
        }

    }

    private static void cardPosition(ArrayList<String> cards){

        for (int i = 0;  i < 2; i++){
            for(int j = 0; j < cards.size(); j++){
                int[] position = randPosition();

                while (field[position[0]][position[1]] != "⭕️"){
                    position = randPosition();

                }

                field[position[0]][position[1]] = cards.get(j);

            }
        }
    }

    public static int[] randPosition(){

        int x = (int)Math.floor(Math.random() * 3);
        int y = (int)Math.floor(Math.random() * 4);
        int[] position = new int[2];
        position[0] = x;
        position[1] = y;

        return position;
    }

    private static boolean end(String[] arr){
        boolean finish = false;

        for (int i = 0; i < isEqual.length; i++) {
            if(isEqual[i] != null){
                finish = true;
            }else{
                finish = false;
            }
        }

        return finish;
    }
}