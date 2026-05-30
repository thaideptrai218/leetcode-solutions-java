package sorting;

public class ArraySorter {
    public static void bubbleSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            boolean swapped = false;

            for (int j = 1; j < n - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j - 1, j);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    public static void selectionSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, min, i);
        }
    }

    public static void insertionSort(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int j = i - 1;
            while (j >= 0 && cur < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = cur;
        }
    }



    private static void swap(int[] nums, int i, int j) {
        int n = nums.length;
        if (i < 0 || i >= n || j < 0 || j >= n || i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static int[] numsGenerator(int quantity) {
        int[] array = new int[quantity];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * (quantity));
        }
        return array;
    }

    public static String printArray(int[] nums) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i < nums.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
