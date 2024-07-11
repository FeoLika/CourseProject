package ru.feolika.Tasks.w1d2;

/**
 * Class with different sort types
 */
public class ArraySort {

    /**
     *  Quick sort
     * @param array unsorted array
     * @param low min index
     * @param high max index
     * @return sorted array
     */
    public static int[] quickSort(int[] array, int low, int high){
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
        return array;
    }

    /**
     * Func creates a pivot,
     * then moves all elements that are less than pivot on the left,
     * those that are greater than or equal to - on the right
     * @param array unsorted array
     * @param low min index
     * @param high  max index
     * @return
     */
    private static int partition(int[] array, int low, int high){
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    /**
     * Selection sort
     * @param array unsorted array
     * @return sorted array
     */
    public static int[] selectionSort(int[] array){
        int length = array.length;
        try {
            for (int i = 0; i < length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < length; j++) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        return array;
    }
}
