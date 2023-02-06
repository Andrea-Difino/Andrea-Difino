import java.util.Scanner;

public class EquazioniIIGrado {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Scrivi gli addendi in questo modo. Le lettere (tranne la x) rappresentano numeri");

        System.out.println("ax^n bx c" + "\nDove n rappresenta l'esponente della x");

        System.out.print("Scrivi il numero di addendi: ");
        int n_addendi = scan.nextInt();
        scan.nextLine();
        String[] eqParts = new String[n_addendi];
        for (int i = 0; i < eqParts.length; i++) {
            String addendo = scan.nextLine();
            eqParts[i] = addendo;
        }
        // eqParts = {+4x^2, +5x , +3}
        scan.close();
        int[] addendi = new int[n_addendi];
        double a = 0;
        double b = 0;
        double c = 0;

        for (int j = 0; j < eqParts.length; j++) {
            if (eqParts[j].contains("x^2") || eqParts[j].contains("X^2")) {
                if (eqParts[j].substring(0, eqParts[j].indexOf("x")).equals("")) {
                    addendi[j] += 1;
                    a += addendi[j];
                } else {
                    addendi[j] += Integer.parseInt(eqParts[j].substring(0, eqParts[j].indexOf("x")));
                    a += addendi[j];
                }
            } else if (eqParts[j].contains("x")) {
                if (eqParts[j].substring(0, eqParts[j].indexOf("x")).equals("")) {
                    addendi[j] += 1;
                    b += addendi[j];
                } else {
                    addendi[j] += Integer.parseInt(eqParts[j].substring(0, eqParts[j].indexOf("x")));
                    b += addendi[j];
                }
            } else {
                addendi[j] += Integer.parseInt(eqParts[j]);
                c += addendi[j];
            }
        }

            if (a != 0 && b != 0 && c != 0) {
                double[] risultato = risultatoEquazioneIIGrado(a, b, c);
                System.out.println("Risultati: " + Math.round(risultato[0]*100.0) / 100.0 + " " + Math.round(risultato[1]*100.0) / 100.0);
            } else if (a == 0) {
                // (bx + c = 0) => (bx = -c) => (x = -c/b)
                c = -c;
                double risultato_2 = c / b;
                System.out.println("Risultato: " + Math.round(risultato_2 * 100.0 ) / 100.0);
            } else if (b == 0) {
                if ((c > 0 && a > 0) || (c < 0 && a < 0)) {
                    // (ax^2 + c = 0) => (x^2 = -c/a) (x^2 = c/-a)
                    System.out.println("Risultato: Impossibile");
                } else if (c > 0 && a < 0) {
                    // (-ax^2 + c = 0) => (x^2 = -c/-a)
                    double risultato_3 = Math.sqrt(-c / a);
                    System.out.println("Risultato: " + Math.round(risultato_3 * 100.0 ) / 100.0);
                } else if(c == 0){
                    System.out.println("Risultati: 0 preso due volte");
                }
            } else if (c == 0) {
                // (ax^2 + bx = 0) => (x(ax + b) = 0) => x = 0 || x = -b/a
                double[] risultato_4 = new double[2];
                risultato_4[0] = 0;
                risultato_4[1] = -b / a;
                System.out.println("Risultati: " + risultato_4[1] + " " + risultato_4[0]);
            }

    }

    public static double[] risultatoEquazioneIIGrado(double a, double b, double c) {
        double delta = Math.pow(b, 2) - (4 * a * c);

        double[] risultato = new double[2];
        risultato[0] = (-b + (Math.round(Math.sqrt(delta) * 100.0) / 100.0)) / (2.0 * a);
        risultato[1] = (-b - (Math.round(Math.sqrt(delta) * 100.0) / 100.0)) / (2.0 * a);
        return risultato;
    }

}