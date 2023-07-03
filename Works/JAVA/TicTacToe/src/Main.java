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
            System.out.println("Inserisci riga e colonna:");

            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;

            uploadField(r, c);
            createField();
            moves++;

            if(isWin()){
                System.out.println("Hai vinto!");
                return;
            };

            if(isLose()){
                System.out.println("Hai perso!");
                return;
            };

            if(moves == 9){
                System.out.println("Pareggio");
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
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (j == 1) {
                    System.out.print(" | " + field[i][j] + " | ");
                } else {
                    System.out.print(field[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void uploadField(int r, int c) {

        if (turn == 0) {
            turn++;
            if (field[r][c] == x || field[r][c] == O) {
                System.out.println("Casella già occupata");
                turn--;
            } else {
                field[r][c] = x;
            }
        } else {
            turn--;
            if (field[r][c] == x || field[r][c] == O) {
                System.out.println("Casella già occupata");
                turn++;
            } else {
                field[r][c] = O;
            }
        }
    }

    public static boolean isWin(){

        //ROWS CONTROL
        for (int i = 0; i < 3; i++) {
            boolean tipo = true;
            for (int j = 0; j < field[i].length; j++){
                if(field[i][j] == x){

                } else{
                    tipo = false;
                }
            }
            if(tipo){
                return true;
            }
        }

        //COLUMN CONTROL
        for (int i = 0; i < 3; i++) {
            boolean tipo = true;
            for (int j = 0; j < field[i].length; j++){
                if(field[j][i] == x ){

                } else{
                    tipo = false;
                }
            }
            if(tipo){
                return true;
            }
        }

        //CONTROLLO DIAGONALE
        for (int i = 0; i < 3; i++) {
            int tipo = 0;

            int j = i;
            if(field[j][i] == x){
                tipo++;
            }

            if(tipo == 3){
                return true;
            }
        }

        return false;
    }

    public static boolean isLose(){

        //ROWS CONTROL
        for (int i = 0; i < 3; i++) {
            boolean tipo = true;
            for (int j = 0; j < field[i].length; j++){
                if(field[i][j] == O){

                } else{
                    tipo = false;
                }
            }
            if(tipo){
                return true;
            }
        }

        //COLUMN CONTROL
        for (int i = 0; i < 3; i++) {
            boolean tipo = true;
            for (int j = 0; j < field[i].length; j++){
                if(field[j][i] == O){

                } else{
                    tipo = false;
                }
            }
            if(tipo){
                return true;
            }
        }

        //CONTROLLO DIAGONALE
        for (int i = 0; i < 3; i++) {
            int tipo = 0;

            int j = i;
            if(field[j][i] == O){
                tipo++;
            }

            if(tipo == 3){
                return true;
            }
        }

        return false;
    }

}