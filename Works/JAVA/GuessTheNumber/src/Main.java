import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner chooseModality = new Scanner(System.in);

        System.out.print("To guess the decimal number from the binary type 1 " + "OR" + " ");
        System.out.println("2 to guess the binary number from the decimal");
        System.out.print("Modality: ");

        if(chooseModality.nextInt() == 1){
            new DiscoverTheBinary();
        }else{
            new DiscoverTheDecimal();
        }

    }
}