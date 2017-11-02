import java.util.ArrayList;

public class Runner {

	public static AnObject[] list;
	
	// static methods
	public static void main(String[] a) {
		
		int[] example = new int[5];
		
		
		
		list = AnObject.pickup();
		System.out.println(list[0].color);
		list[0].eat();
		System.out.println(list[0].color);
		System.out.println(list[1].color);
		System.out.println(list.length);
		AnObject.howMany = 2;
		System.out.println(list.length);
		list = AnObject.pickup();
		System.out.println(list.length);
	
	}

	
	
}
