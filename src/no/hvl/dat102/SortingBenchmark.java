package no.hvl.dat102;

import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortingBenchmark {

    // Insertion Sort
    public static void insertionSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Selection Sort
    public static void selectionSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Quick Sort
    public static void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Merge Sort
    public static void mergeSort(Integer[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Integer[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Generate an array of random integers
    public static Integer[] generateRandomArray(int n, int bound) {
        Random rand = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(bound);
        }
        return arr;
    }

    // Generate an array of identical integers
    public static Integer[] generateIdenticalArray(int n, int value) {
        Integer[] arr = new Integer[n];
        Arrays.fill(arr, value);
        return arr;
    }

    // Measure sorting time
    public static long measureTime(Runnable sortingAlgorithm) {
        long startTime = System.nanoTime();
        sortingAlgorithm.run();
        return System.nanoTime() - startTime;
    }

    // Compute constant c for theoretical time
    public static double computeC(long time, int n, double exponent) {
        return time / Math.pow(n, exponent);
    }

    // Benchmark sorting algorithms
    public static void main(String[] args) {
        int[] sizes = {32000, 64000, 128000};
        System.out.printf("%-10s %-15s %-15s %-15s\n", "Size", "Measured Time", "Theoretical Time", "c");
        for (int size : sizes) {
            Integer[] array = generateRandomArray(size, 1000000);

            long time;
            double c;

            time = measureTime(() -> {
                Integer[] copy = array.clone();
                insertionSort(copy);
            });
            c = computeC(time, size, 2);
            System.out.printf("%-10d %-15d %-15.2f %-15.6f\n", size, time, c * Math.pow(size, 2), c);

            time = measureTime(() -> {
                Integer[] copy = array.clone();
                selectionSort(copy);
            });
            c = computeC(time, size, 2);
            System.out.printf("%-10d %-15d %-15.2f %-15.6f\n", size, time, c * Math.pow(size, 2), c);

            time = measureTime(() -> {
                Integer[] copy = array.clone();
                quickSort(copy, 0, copy.length - 1);
            });
            c = computeC(time, size, Math.log(size) / Math.log(2));
            System.out.printf("%-10d %-15d %-15.2f %-15.6f\n", size, time, c * size * Math.log(size) / Math.log(2), c);

            time = measureTime(() -> {
                Integer[] copy = array.clone();
                mergeSort(copy, 0, copy.length - 1);
            });
            c = computeC(time, size, Math.log(size) / Math.log(2));
            System.out.printf("%-10d %-15d %-15.2f %-15.6f\n", size, time, c * size * Math.log(size) / Math.log(2), c);
            System.out.println();
        }

        // QuickSort test with identical values
        Integer[] identicalArray = generateIdenticalArray(32000, 42);
        System.out.println("QuickSort with identical values: " + measureTime(() -> quickSort(identicalArray, 0, identicalArray.length - 1)) + " ns");
    }

    // JUnit Tests
}