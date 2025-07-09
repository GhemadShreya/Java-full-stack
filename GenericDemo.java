import java.util.*;
public class GenericDemo {

	public static void main(String[] args) {
		String str="Shreya";
		Character[] ch=new Character[str.length()];
		for(int i=0;i<str.length();i++)
		{
			ch[i]=str.charAt(i);
		}
		reverse(ch);
        System.out.println("After reverse array: " + Arrays.toString(ch)); 

		System.out.println("after reverse array:"+ch);
		String[] animal= {"tiger","dog","elephant"};
		isAvilable(animal,"tiger","lion");

	}
	public static <T> void reverse(T[] arr)
	{
		int i=0,j=arr.length-1;
		while(i<j) {
			T temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			i++;
			j--;
		}
	}//reverse
	
	public static <T> void isAvilable(T[] t,T ele1,T ele2)
	{        
		for (T item : t) {
        if (item.equals(ele1)) {
        	System.out.println(ele1+" is avilable in array");
        }
        
        if (item.equals(ele2)) {
        	System.out.println(ele2+" is avilable in array");
        }
    }
	}
	}

}
