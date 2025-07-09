package package1;

public class FindMinMaxValue {
    public static <T extends Comparable<T>> T minimumValue(T[] arr) {

        T min = arr[0];
        for (T t : arr) {
            if (t.compareTo(min) < 0) {
                min = t;
            }
        }
        return min;
    }
    public static <T extends Comparable<T>> T maximumValue(T[] arr) {

        T max = arr[0];
        for (T t : arr) {
            if (t.compareTo(max) > 0) {
                max = t;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        Integer[] arr = {55, 11, 22, 55, 699};
        Integer minresult = minimumValue(arr);
        System.out.println("Minimum value: " + minresult);
        Integer maxresult = minimumValue(arr);
        System.out.println("Maximum value: " + maxresult);

    }
}
