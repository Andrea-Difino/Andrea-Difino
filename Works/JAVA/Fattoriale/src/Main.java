import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Inserisci numero per ricavarne il fattoriale: ");
        int n = scan.nextInt();
        System.out.println("Processing...");
        System.out.println(solve(n));
    }

    public static long solve(int n){
        if(n == 1){
            return 1;
        }else{
            return n * solve(n-1);
        }
    }

}