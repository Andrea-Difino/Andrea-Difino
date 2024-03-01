import java.util.Scanner;

public class algoritmodiEuclide {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.print("Dividendo:");
        int a = sc.nextInt(); //dividendo
        System.out.print("Divisore:");
        int b = sc.nextInt(); //divisore

        System.out.println("M.C.D = " + algoritmo(a,b));

    }

    public static int algoritmo(int a,int b){
        int q = (int) Math.floor(a/b);
        int r = a - (b*q);

        if(r == 0){
            int mcd = b;
            return mcd;
        }else{
            a = b;
            b = r;
            return algoritmo(a , b);
        }
    }
}
