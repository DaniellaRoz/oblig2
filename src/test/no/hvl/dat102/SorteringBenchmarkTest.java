package no.hvl.dat102;

import org.junit.jupiter.api.Test;

import static no.hvl.dat102.SortingBenchmark.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SorteringBenchmarkTest {
    @Test
    public void testSortingAlgorithms() {
        Integer[] arr = {5, 3, 8, 1, 2};
        Integer[] expected = {1, 2, 3, 5, 8};

        Integer[] copy = arr.clone();
        insertionSort(copy);
        assertArrayEquals(expected, copy);

        copy = arr.clone();
        selectionSort(copy);
        assertArrayEquals(expected, copy);

        copy = arr.clone();
        quickSort(copy, 0, copy.length - 1);
        assertArrayEquals(expected, copy);

        copy = arr.clone();
        mergeSort(copy, 0, copy.length - 1);
        assertArrayEquals(expected, copy);
    }
}
