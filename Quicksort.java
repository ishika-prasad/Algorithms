/*
 * Quicksort.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is sorting implementaion
 * based on quicksort algorithm
 *
 * @author  Ishika Prasad
 */
import java.io.FileWriter;
import java.io.IOException;

public class Quicksort {
    /**
     *
     * @param A Input Array
     * @param l start index
     * @param r end index
     * @return sorted index of pivot element in array
     */

    public int partition(int A[], int l, int r) {
        int x = A[l], i = l, j = r;
        while (true) {
            while (i < j) {
                while ((i <= r) && (A[i] <= x)) {
                    i += 1;
                }
                while ((j >= l) && (A[j] > x)) {
                    j -= 1;
                }
                if (i < j) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
            int temp = A[l];
            A[l] = A[j];
            A[j] = temp;
            return j;
        }
    }
    /**
     *
     * @param A Input array
     * @param l start index
     * @param r end index
     * This method sort the array using quick sort method
     */
    public void qsort(int A[], int l, int r) {
        if (l < r) {
            int q = partition(A, l, r);
            qsort(A, l, q - 1);
            qsort(A, q + 1, r);
        }
    }
    /**
     *
     * @param n Total count
     * @param writer FileWriter object to write generated array in file
     * @return Array of randomly generated data of size n
     * @throws IOException
     */
    public static int[] set1(int n,FileWriter writer) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 1000);
            System.out.print(arr[i] + " ");
            writer.write(arr[i] + " ");
        }
        return arr;
    }
    /**
     *
     * @param n total count
     * @return random element
     */
    public static int set2(int n) {
        int lambda = n / 2;
        int k = 1;
        double p = 1;
        double l = Math.exp(-lambda);
        do {
            k = k + 1;
            p = p * Math.random();
        } while (p > l);
        return k - 1;
    }
    /**
     *
     * @param k input array
     * @param writer FileWriter object to write generated array in file
     * @throws IOException
     */
    public void sortedArrayDisplay(int k[], FileWriter writer) throws IOException {
        int arrlength = k.length;
        for (int i = 0; i < arrlength; i++) {
            System.out.print(k[i] + " ");
            writer.write(k[i] + " ");
        }
        System.out.println();
    }

    /**
     * The main method is to generate the report in the specified file path
     * which includes unsorted and sorted data for set1 and set2
     * @param args no command line argument
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //set specified file path
        FileWriter writer = new FileWriter("C:\\quicksort\\Quicksort_output.txt");
        Quicksort sort = new Quicksort();

        int[] random_list = {1000, 10000, 50000, 100000, 500000};
        int arr_set[];

        for (int i = 0; i < random_list.length; i++) {
            int n = random_list[i];
            int[] poisson_val = new int[n];
            //for data set 1
            System.out.println("\nSet1 unsorted data for " + random_list[i] + " data sets:\n");
            writer.write("\nSet1 unsorted data for " + random_list[i] + " data sets:\n");
            arr_set = Quicksort.set1(n,writer);
            System.out.println("\nSet1 sorted data for " + random_list[i] + " data sets:\n");
            writer.write("\nSet1 sorted data for " + random_list[i] + " data sets:\n");
            // Sort the array using quick sort
            long startTime = System.currentTimeMillis();
            sort.qsort(arr_set, 0, arr_set.length - 1);
            long endTime = System.currentTimeMillis();
            // Write the sorted array in file and console
            sort.sortedArrayDisplay(arr_set,writer);
            long totalTime = endTime - startTime;
            System.out.println("\nCPU time in quicksort for Set 1: " + totalTime + " ms\n");
            writer.write("\nCPU time in quicksort for Set 1: " + totalTime + " ms\n");

            //for data set 2
            System.out.println("\nSet2 unsorted data for " + random_list[i] + " data sets:\n");
            writer.write("\nSet2 unsorted data for " + random_list[i] + " data sets:\n");
            for (int j = 0; j < n; j++) {
                poisson_val[j] = Quicksort.set2(n);
                System.out.print(poisson_val[j] + " ");
                writer.write(poisson_val[j] + " ");
            }
            System.out.println("\nSet2 sorted data for " + random_list[i] + " data sets:\n");
            writer.write("\nSet1 sorted data for " + random_list[i] + " data sets:\n");
            // Sort the array using quick sort
            long startTime1 = System.currentTimeMillis();
            sort.qsort(poisson_val, 0, poisson_val.length - 1);
            long endTime1 = System.currentTimeMillis();
            // Write the sorted array in file and console
            sort.sortedArrayDisplay(poisson_val,writer);
            long totalTime1 = endTime1 - startTime1;
            System.out.println("\nCPU time in quicksort for Set 2: " + totalTime1 + " ms\n");
            writer.write("\nCPU time in quicksort for Set 2: " + totalTime1 + " ms\n");
        }
        writer.close();
    }

}
