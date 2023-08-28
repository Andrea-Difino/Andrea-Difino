import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scegliModalita = new Scanner(System.in);

        System.out.print("Per indovinare il numero decimale dal binario digiti 1 " + "O" + " ");
        System.out.println("2 per indovinare il numero binario dal decimale");
        System.out.print("Modalità: ");

        if(scegliModalita.nextInt() == 1){
            new DiscoverTheBinary();
        }else{
            new DiscoverTheDecimal();
        }

    }
}