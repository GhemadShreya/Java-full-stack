package package1;

public class CountOccurrence {
	public static <T> void countElement(T[] arr,T number)
	{
		int count=0;
		for(T t:arr) {
			if(t.equals(number)) {
				count++;
			}
		}
		System.out.println(number+" Occurrence in array:"+count+" times");
	}

	public static void main(String[] args) {
		Integer[] arr= {1,2,3,45,2,4,5,2,6,2,};
		countElement(arr,2);

	}

}
