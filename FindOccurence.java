package package1;

public class FindOccurence {

	public static <T> void isFound(T[] arr, T[] item) {
		int count = 0;

		for (int i = 0; i <= arr.length - item.length; i++) {
			boolean match = true;

			for (int j = 0; j < item.length; j++) {
				if (!arr[i + j].equals(item[j])) {
					match = false;
					break;
				}
			}

			if (match) {
				count++;
				int start = i;
				int end = i + item.length - 1;
				System.out.println(count + ") item found at starting index " + start + " and ending index " + end);
			}
		}

		if (count == 0) {
			System.out.println("Item is not found.");
		} else {
			System.out.println("Item is found " + count + " times.");
		}
	}

	public static void main(String[] args) {
		String str = "Java is a programming language. Many developers love Java. JavaScript is different from Java.";
		String str2 = "Java";

		Character[] mainStr = toCharacter(str);
		Character[] subStr = toCharacter(str2);

		isFound(mainStr, subStr);
	}

	public static Character[] toCharacter(String str) {
		Character[] ch = new Character[str.length()];
		for (int i = 0; i < str.length(); i++) {
			ch[i] = str.charAt(i);
		}
		return ch;
	}
}
