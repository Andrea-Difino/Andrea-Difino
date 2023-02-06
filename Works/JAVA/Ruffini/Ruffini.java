import java.util.Arrays;
import java.util.Scanner;

public class Ruffini {
    public static void main(String[] args) {

        System.out.println("Scrivi gli addendi in questo modo. Le lettere (tranne la x) rappresentano numeri");

        System.out.println("ax^n bx c" + "\nDove n rappresenta l'esponente della x");

        System.out.print("Scrivi il numero di addendi: ");
        Scanner scan = new Scanner(System.in);
        int n_addendi = scan.nextInt();
        scan.nextLine();
        String[] eqParts = new String[n_addendi];
        for (int i = 0; i < eqParts.length; i++) {
            String addendo = scan.nextLine();
            eqParts[i] = addendo;
        }

        scan.close();

        //System.out.println("Monomi dell'equazione: " + Arrays.toString(eqParts));

        int[] addendi = new int[n_addendi];

        int[] gradi = new int[addendi.length];

        for (int j = 0; j < eqParts.length; j++) {
            if (eqParts[j].contains("^")) {
                gradi[j] = Integer
                        .parseInt(eqParts[j].substring(eqParts[j].indexOf("^") + 1, eqParts[j].indexOf("^") + 2));
            } else {
                gradi[j] = 1;
            }
        }

        double a = 0; // coefficienti grado 6
        double b = 0; // coefficienti grado 5
        double c = 0; // coefficienti grado 4
        double d = 0; // coefficienti grado 3
        double e = 0; // coefficienti grado 2
        double f = 0; // coefficienti grado 1
        double g = 0; // termine noto

        for (int j = 0; j < eqParts.length; j++) {
            if (eqParts[j].contains("^6")) {
                if (eqParts[j].substring(0, eqParts[j].indexOf("x")).equals("")) {
                    addendi[j] += 1;
                    a += 1;
                } else {
                    addendi[j] += Integer.parseInt(eqParts[j].substring(0, eqParts[j].indexOf("x")));
                    a += addendi[j];
                }
            } else if (eqParts[j].contains("^5")) {
                if (eqParts[j].substring(0, eqParts[j].indexOf("x")).equals("")) {
                    addendi[j] += 1;
                    b += 1;
                } else {
                    addendi[j] += Integer.parseInt(eqParts[j].substring(0, eqParts[j].indexOf("x")));
                    b += addendi[j];
                }
            } else if (eqParts[j].contains("^4")) {
                if (eqParts[j].substring(0, eqParts[j].indexOf("x")).equals("")) {
                    addendi[j] += 1;
                    c += 1;
                } else {
                    addendi[j] += Integer.parseInt(eqParts[j].substring(0, eqParts[j].indexOf("x")));
                    c += addendi[j];
                }
            } else if (eqParts[j].contains("^3")) {
                if (eqParts[j].substring(0, eqParts[j].indexOf("x")).equals("")) {
                    addendi[j] += 1;
                    d += 1;
                } else {
                    addendi[j] += Integer.parseInt(eqParts[j].substring(0, eqParts[j].indexOf("x")));
                    d += addendi[j];
                }
            } else if (eqParts[j].contains("^2")) {
                if (eqParts[j].substring(0, eqParts[j].indexOf("x")).equals("")) {
                    addendi[j] += 1;
                    e += 1;
                } else {
                    addendi[j] += Integer.parseInt(eqParts[j].substring(0, eqParts[j].indexOf("x")));
                    e += addendi[j];
                }
            } else if (eqParts[j].contains("x")) {
                if (eqParts[j].substring(0, eqParts[j].indexOf("x")).equals("")) {
                    addendi[j] += 1;
                    f += 1;
                } else {
                    addendi[j] += Integer.parseInt(eqParts[j].substring(0, eqParts[j].indexOf("x")));
                    f += addendi[j];
                }
            } else {
                addendi[j] += Integer.parseInt(eqParts[j]);
                g += addendi[j];
            }
        }

        double[] coefficientiX = new double[6];
        for (int i = 0; i < coefficientiX.length; i++) {
            switch (i) {
                case 0:
                    coefficientiX[i] = a;
                    break;
                case 1:
                    coefficientiX[i] = b;
                    break;
                case 2:
                    coefficientiX[i] = c;
                    break;
                case 3:
                    coefficientiX[i] = d;
                    break;
                case 4:
                    coefficientiX[i] = e;
                    break;
                case 5:
                    coefficientiX[i] = f;
                    break;
            }

        }

        double radice = radicePolinomio(g, coefficientiX);
        String polinomio = "";
        if (radice > 0) {
            String polinomioGrado1 = "(x - " + (int) radice + ")";
            polinomio = polinomioGrado1;
            System.out.println("Polinomio di primo grado: " + polinomioGrado1);
        } else if (radice < 0) {
            String polinomioGrado1 = "(x + " + (int) (-radice) + ")";
            polinomio = polinomioGrado1;
            System.out.println("Polinomio di primo grado: " + polinomioGrado1);
        }

        Arrays.sort(gradi); // [1, 1, 2, 3]
        int gradoEquazione = gradi[gradi.length - 1];
        int gradoPolinomioFinal = gradoEquazione - 1;

        String scomposizione = applicaRuffini(gradoEquazione, gradoPolinomioFinal, coefficientiX, g, radice);
        System.out.println("Polinomio scomposto: " + scomposizione + polinomio);

    }

    public static double radicePolinomio(double termineNoto, double[] coefficientiX) {

        double radice = 0;
        int n_divisori = 0;

        if (termineNoto > 0) {
            for (int k = 1; k <= termineNoto; k++) {
                if (termineNoto % k == 0) {
                    n_divisori += 1;
                }
            }
        } else if (termineNoto < 0) {
            for (int k = -1; k >= termineNoto; k--) {
                if (termineNoto % k == 0) {
                    n_divisori += 1;
                }
            }
        }

        // System.out.println(n_divisori);
        int[] divisoriTNoto = new int[n_divisori];
        int index = 0;

        if (termineNoto > 0) {
            for (int i = 0; i < termineNoto; i++) {
                if (termineNoto % i == 0) {
                    index++;
                    divisoriTNoto[divisoriTNoto.length - index] = i;
                } else if (i == 0) {
                    divisoriTNoto[0] = (int) termineNoto;
                }
            }
        } else if (termineNoto < 0) {
            for (int i = 0; i > termineNoto; i--) {
                if (termineNoto % i == 0) {
                    index++;
                    divisoriTNoto[divisoriTNoto.length - index] = (int) i;
                } else if (i == 0) {
                    divisoriTNoto[0] = (int) termineNoto;
                }
            }
        }

        // System.out.println(Arrays.toString(divisoriTNoto));

        int[] divisoriNegativi = new int[n_divisori];

        for (int j = 0; j < divisoriTNoto.length; j++) {
            divisoriNegativi[j] = -divisoriTNoto[j];
        }

        int[] divisoriTotali = new int[divisoriTNoto.length + divisoriNegativi.length];
        System.arraycopy(divisoriTNoto, 0, divisoriTotali, 0, divisoriTNoto.length);
        System.arraycopy(divisoriNegativi, 0, divisoriTotali, divisoriTNoto.length, divisoriNegativi.length);
        //System.out.println("Divisori totali -> " + Arrays.toString(divisoriTotali));

        for (int k = 0; k < divisoriTotali.length; k++) {
            double risultato = ((Math.pow(divisoriTotali[k], 6)) * coefficientiX[0])
                    + ((Math.pow(divisoriTotali[k], 5)) * coefficientiX[1])
                    + ((Math.pow(divisoriTotali[k], 4)) * coefficientiX[2])
                    + ((Math.pow(divisoriTotali[k], 3)) * coefficientiX[3])
                    + ((Math.pow(divisoriTotali[k], 2)) * coefficientiX[4])
                    + (divisoriTotali[k] * coefficientiX[5])
                    + termineNoto;
            if (risultato == 0) {
                radice = divisoriTotali[k];
            }
        }

        return radice;
    }

    public static String applicaRuffini(int gradoMax, int gradoFinale, double[] coefficientiX, double termineNoto,
            double radice) {

        int index = 0;
        double[] numeriTabella = new double[gradoMax + 1];
        numeriTabella[gradoMax] = termineNoto;
        for (int i = 0; i < coefficientiX.length; i++) {
            if (coefficientiX[i] == 0 && coefficientiX[i + 1] != 0 && coefficientiX[i - 1] != 0) {
                numeriTabella[index] = 0;
                index++;
            } else if (coefficientiX[i] == 0) {

            } else {
                numeriTabella[index] = coefficientiX[i];
                index++;
            }
        }
        //System.out.println("Coefficienti dell'equazione iniziale -> " + Arrays.toString(numeriTabella));
        // [2.0, 0.0, -3.0, 2.0, -1.0(termineNoto)]

        int[] numeriConZero = new int[numeriTabella.length];

        for (int k = 0; k < numeriConZero.length - 1; k++) {
            if (k == 0) {
                int monomio = (int) numeriTabella[k];
                numeriConZero[k] = monomio;
            } else {
                if (k != 4) {
                    int monomio = (int) (numeriConZero[k - 1] * radice + numeriTabella[k]);
                    numeriConZero[k] = monomio;
                }
            }
        }

        int[] goodNumbers = new int[numeriConZero.length - 1];
        for (int j = 0; j < numeriConZero.length - 1; j++) {
            goodNumbers[j] = numeriConZero[j];
        }
        String scomposizione = "(";
        int indexGoodNumbers = 0;
        for (int i = gradoFinale; i >= 0; i--) {
            if (i != 0) {
                String monomio = "" + goodNumbers[indexGoodNumbers] + "x^" + i;
                scomposizione = scomposizione + "+" + monomio;
                indexGoodNumbers++;
            } else {
                String monomio = "" + goodNumbers[indexGoodNumbers];
                scomposizione = scomposizione + "+" + monomio;
                indexGoodNumbers++;
            }
        }

        scomposizione = scomposizione + ")";
        scomposizione = scomposizione.replace("+-", "-");
        // System.out.println(Arrays.toString(goodNumbers));
        // System.out.println(scomposizione);

        //////////////////TABELLA
        //PARTE SOPRA
        for (int i = 0; i < numeriTabella.length; i++) {
            if (i == 0) {
                System.out.print(" |" + (int) numeriTabella[i]);
            } else if (i != 0 && i <= numeriTabella.length - 2) {
                System.out.print(" " + (int) numeriTabella[i]);
            } else {
                System.out.print("|" + (int) numeriTabella[i]);
            }
        }
        System.out.println();

        //PARTE IN MEZZO
        for (int i = 0; i < numeriTabella.length; i++) {
            if (i == 0) {
                System.out.print((int)radice + "|_");
            } else if (i != 0 && i <= numeriTabella.length - 2) {
                System.out.print("__");
            } else {
                System.out.print("|_");
            }
        }
        System.out.println();
        //PARTE SOTTO
        for (int k = 0; k < numeriConZero.length; k++) {
            if (k == 0) {
                System.out.print(" |" + (int) numeriConZero[k]);
            } else if (k != 0 && k <= numeriConZero.length - 2) {
                System.out.print(" " + (int) numeriConZero[k]);
            } else {
                System.out.print("|" + (int) numeriConZero[k]);
            }
        }
        System.out.println();
        //////////////////FINE TABELLA

        return scomposizione;
    }
}
