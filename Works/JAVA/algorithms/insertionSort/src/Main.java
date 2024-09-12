import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) {

        int[] arr = {5, 4, 2, 6, 3};
        int n = arr.length;
        sort(arr, n);
        System.out.println("Sorted Array" + "\n" + Arrays.toString(arr));
    }

    public static int[] sort(int[] a, int n){

        for (int i = 1; i < n+1; i++) {
            int key = a[i-1];
            int j = i - 1;
            while(j > 0 && a[j-1] > key ){
                a[j] = a[j-1];
                j = j - 1;
            }
            a[j] = key;
            System.out.println(Arrays.toString(a));
        }

        return a;
    }
}