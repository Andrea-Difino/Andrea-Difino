import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] arr = {5, 4, 2, 6, 3};
        int[] new_arr = sort(arr);
        System.out.println("Sorted Array" + "\n" + Arrays.toString(new_arr));
    }

    public static int[] sort(int[] a){

        int[] new_arr = new int[a.length + 1];
        new_arr[0] = 0;
        for (int j = 0; j < a.length; j++) {
            new_arr[j+1] = a[j];
        }
        //{0, 5, 4, 2, 6, 3}
        int n = new_arr.length;

        for (int i = 2; i < n; i++) {
            int key = new_arr[i];
            int j = i - 1;
            while(new_arr[j] > key && j > 0){
                new_arr[j+1] = new_arr[j];
                j = j - 1;
            }
            new_arr[j+1] = key;
        }

        return new_arr;
    }
}