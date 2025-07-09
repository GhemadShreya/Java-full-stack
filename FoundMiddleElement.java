package package1;

public class FoundMiddleElement {

    public static <T> T foundMiddleElement(T[] arr) {
        int mid = arr.length / 2;
        return arr[mid];
    }

    public static void main(String[] args) {
        Integer[] arr = {11, 22, 33, 44, 55, 66};
        Integer result = foundMiddleElement(arr);
        System.out.println("Middle element is: " + result);
    }
}
