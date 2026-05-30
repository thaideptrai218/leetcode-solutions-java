package sorting.main;

import sorting.ArraySorter;
import sorting.ConsoleInput;
import sorting.ConsoleOutput;

public class P0002 {
    static ConsoleInput consoleInput = new ConsoleInput();
    public static void main(String[] args) {
        int n = consoleInput.readIntMin("Enter number of array: ", 0, "Number of array must greater than 0!");
        int[] array = ArraySorter.numsGenerator(n);
        ConsoleOutput.log("Unsorted array:", ArraySorter.printArray(array));
        ArraySorter.selectionSort(array);
        ConsoleOutput.log("Sorted array:", ArraySorter.printArray(array));
    }
}
