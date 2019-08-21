/*
 * MY_CHOICE_QSORT.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is sorting implementaion
 * based on efficient quicksort algorithm
 * called Qsorte
 *
 * @author  Ishika Prasad
 */
import java.io.FileWriter;
import java.io.IOException;

public class MY_CHOICE_QSORT {

    /**
     *
     * @param k Input Array
     * @param m start index
     * @param n end index
     * @param pivot_loc location of pivot is
     *                  set to be middle position
     */
    public void qsorte(int k[], int m, int n, int pivot_loc) {
        int t, pivot;
        int i, j, size;
        boolean lsorted, rsorted;

        if(m < n) {
            pivot = k[pivot_loc];
            i = m-1;
            j = n+1;
            lsorted = true;
            rsorted = true;

            while(i < j) {
                i = i+1;
                while(k[i] < pivot) {
                    if (lsorted) {
                        if (i > m) {
                            if (k[i] < k[i-1]) {
                                lsorted= false;
                            }
                        }
                    }
                    i = i+1;
                }

                j = j-1;
                while((j >= m) && (k[j] >= pivot)) {
                    if(rsorted) {
                        if(j < n) {
                            if (k[j] > k[j+1]) {
                                rsorted = false;
                            }
                        }
                    }
                    j = j-1;
                }

                if (i < j) {
                    t = k[j];
                    k[j] = k[i];
                    k[i] = t;

                    if (i == pivot_loc) {
                        pivot_loc = j;
                    }
                    if(lsorted) {
                        if (i > m) {
                            if ( k[i] < k[i-1]) {
                                lsorted = false;
                            }
                        }
                    }
                    if(rsorted) {
                        if (j < n) {
                            if (k[j] > k[j+1]) {
                                rsorted = false;
                            }
                        }
                    }
                }

            }
            if(!rsorted) {
                t = k[i];
                k[i] = k[pivot_loc];
                k[pivot_loc] = t;
                i = i+1;
            }

            if(!lsorted) {
                size = j - m + 1;
                if (size > 2) {
                    qsorte(k, m, j, (m + j) / 2);
                } else if (size == 2) {
                    if (k[m] > k[m + 1]) {
                        t = k[m];
                        k[m] = k[m + 1];
                        k[m + 1] = t;
                    }
                }
            }

            if(!rsorted) {
                size = n-i+1;
                if(size > 2) {
                    qsorte(k, i, n, (i + n) / 2);
                }
                else if(size == 2) {
                    if(k[n] < k[n-1]) {
                        t = k[n];
                        k[n] = k[n-1];
                        k[n-1] = t;
                    }
                }
            }
        }
    }
    /**
     *
     * @param n Total count
     * @param writer FileWriter object to write generated array in file
     * @return Array of randomly generated data of size n
     * @throws IOException
     */
    public static int[] set1(int n, FileWriter writer) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 1000);
            //arr[i] = rand.nextInt(i);
            System.out.print(arr[i] + " ");
            writer.write(arr[i] + " ");
        }
        return arr;
    }
    /**
     *
     * @param n total count
     * @return number generated based on poisson distribution
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
        for(int i = 0; i < arrlength; i++ ) {
            System.out.print(k[i] + " ");
            writer.write(k[i] + " ");
        }
    }

    /**
     * The main method is to generate the report in the specified file path
     * which includes unsorted and sorted data for set1 and set2
     * @param args no command line argument
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MY_CHOICE_QSORT sort = new MY_CHOICE_QSORT();
        int[] random_list = {1000, 10000, 50000, 100000, 500000};
        int arr_set[];
        FileWriter writer = new FileWriter("C:\\quicksort\\MY_QUICK_SORT_output.txt");
        for (int i = 0; i < random_list.length; i++) {
            int n = random_list[i];
            int[] poisson_val = new int[n];
            //for data set 1
            System.out.println("\nSet1 unsorted data for " + random_list[i] + " data sets:\n");
            writer.write("\nSet1 unsorted data for " + random_list[i] + " data sets:\n");
            arr_set = MY_CHOICE_QSORT.set1(n, writer);
            System.out.println("\nSet1 sorted data for " + random_list[i] + " data sets:\n");
            writer.write("\nSet1 sorted data for " + random_list[i] + " data sets:\n");
            // Sort the array using my choice quick sort
            long startTime = System.currentTimeMillis();
            sort.qsorte(arr_set, 0, arr_set.length - 1,(0 + arr_set.length - 1)/2);
            long endTime = System.currentTimeMillis();
            // Write the sorted array in file and console
            sort.sortedArrayDisplay(arr_set, writer);
            long totalTime = endTime - startTime;
            System.out.println("\nCPU time in MY_CHOICE_QUICKSORT for Set 1: " + totalTime + " ms\n");
            writer.write("\nCPU time in MY_CHOICE_QUICKSORT for Set 1: " + totalTime + " ms\n");

            //for data set 2
            System.out.println("Set2 unsorted data for " + random_list[i] + " data sets:");
            writer.write("\nSet2 unsorted data for " + random_list[i] + " data sets:\n");
            for (int j = 0; j < n; j++) {
                poisson_val[j] = MY_CHOICE_QSORT.set2(n);
                System.out.print(poisson_val[j] + " ");
                writer.write(poisson_val[j] + " ");
            }
            System.out.println("\nSet2 sorted data for " + random_list[i] + " data sets:\n");
            writer.write("\nSet2 sorted data for " + random_list[i] + " data sets:\n");
            // Sort the array using my choice quick sort
            long startTime1 = System.currentTimeMillis();
            sort.qsorte(poisson_val, 0, poisson_val.length - 1, (0 + poisson_val.length)/2);
            long endTime1 = System.currentTimeMillis();
            // Write the sorted array in file and console
            sort.sortedArrayDisplay(poisson_val, writer);
            long totalTime1 = endTime1 - startTime1;
            System.out.println("\nCPU time in MY_CHOICE_QUICKSORT for Set 2: " + totalTime1 + " ms\n");
            writer.write("\nCPU time in MY_CHOICE_QUICKSORT for Set 2: " + totalTime1 + " ms\n");
        }
    }
}

