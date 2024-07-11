package ru.feolika.Tasks.w1d2;

import java.util.Arrays;
import java.util.Random;


public class Starter {

    public static void main(String[] args) {
        int[] unsortedArray = setRandomArrayOfInt(100);

        printArray(ArraySort.quickSort(unsortedArray, 0, unsortedArray.length - 1));
        //printArray(ArraySort.selectionSort(unsortedArray));

    }

    /**
     * Setting array of random int from 0 to 99
     * @param length Length of result array
     * @return array of <code>length</code> with random int from 0 to 99
     */
    private static int[] setRandomArrayOfInt(int length){
        int[] numbs = new int[length];

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            numbs[i] = random.nextInt(100);
        }
        return numbs;
    }

    /**
     * printing array to console
     * @param array
     */
    private static void printArray(int[] array){
        System.out.println(Arrays.toString(array));
    }
}
