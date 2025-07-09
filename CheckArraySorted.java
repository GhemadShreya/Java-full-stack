package package1;

// 4) Check if an array is sorted in non-descending order (generic)

public class CheckArraySorted {

    public static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false; 
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5};
        System.out.println(" array sorted? " + isSorted(nums));
       }
}
