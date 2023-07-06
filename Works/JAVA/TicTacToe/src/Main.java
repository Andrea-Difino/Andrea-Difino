import java.util.Scanner;

public class Main {
    public static int ROWS = 3;
    public static int COLUMNS = 3;
    public static char x = '❌';
    public static char O= '⭕';
    public static int turn = 0;
    public static int moves = 0;
    public static char[][] field = new char[ROWS][COLUMNS];

    public static void main(String[] args) {
        System.out.println("TIC TAC TOE");
        System.out.println("   " + x + " " + O);

        initializeField();
        createField();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Type row and column");

            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;

            uploadField(r, c);
            createField();
            moves++;

            if(isWin()){
                System.out.println("Player 1 won!");
                return;
            }

            if(isLose()){
                System.out.println("Player 2 won!");
                return;
            }

            if(moves == 9){
                System.out.println("Draw!");
                return;
            }
        }

    }


    public static void initializeField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = '0'; // 0 == empty
            }
        }
    }

    public static void createField() {
        for (char[] chars : field) {
            for (int j = 0; j < field[0].length; j++) {
                if (j == 1) {
                    System.out.print(" | " + chars[j] + " | ");
                } else {
                    System.out.print(chars[j]);
                }
            }
            System.out.println();
        }
    }

    private static void uploadField(int r, int c) {

        if (turn == 0) {
            turn++;
            if (field[r][c] == x || field[r][c] == O) {
                System.out.println("Box already occupied");
                turn--;
            } else {
                field[r][c] = x;
            }
        } else {
            turn--;
            if (field[r][c] == x || field[r][c] == O) {
                System.out.println("Box already occupied");
                turn++;
            } else {
                field[r][c] = O;
            }
        }
    }

    public static boolean isWin(){

        //ROWS CONTROL
        for (int i = 0; i < 3; i++) {
            boolean type = true;
            for (int j = 0; j < field[i].length; j++){
                if(field[i][j] != x){
                    type = false;
                }
            }
            if(type){
                return true;
            }
        }

        //COLUMN CONTROL
        for (int i = 0; i < 3; i++) {
            boolean type = true;
            for (int j = 0; j < field[i].length; j++){
                if(field[j][i] != x ){
                    type = false;
                }
            }

            if(type){
                return true;
            }
        }

        //DIAGONAL CONTROL
        for (int i = 0; i < 3; i++) {
            int type = 0;

            int j = i;
            if(field[j][i] == x){
                type++;
            }

            if(type == 3){
                return true;
            }
        }

        return false;
    }

    public static boolean isLose(){

        //ROWS CONTROL
        for (int i = 0; i < 3; i++) {
            boolean type = true;
            for (int j = 0; j < field[i].length; j++){
                if(field[i][j] != O){
                    type = false;
                }
            }

            if(type){
                return true;
            }
        }

        //COLUMN CONTROL
        for (int i = 0; i < 3; i++) {
            boolean type = true;
            for (int j = 0; j < field[i].length; j++){
                if(field[j][i] != O){
                    type = false;
                }
            }
            if(type){
                return true;
            }
        }

        //DIAGONAL CONTROL
        for (int i = 0; i < 3; i++) {
            int type = 0;

            int j = i;
            if(field[j][i] == O){
                type++;
            }

            if(type == 3){
                return true;
            }
        }

        return false;
    }

}