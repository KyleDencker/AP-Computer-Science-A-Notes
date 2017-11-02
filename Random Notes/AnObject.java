
public class AnObject {
	
	// Instance Variable
	// A variable that belongs to an object.
	public int color;
	
	
	// Static Variable
	// A variable that belongs to a class.
	public static int howMany = 5;
	
	
	// Constructors
	public AnObject() {
		color = 2;
	
		
	}

	// Instance Methods
	// Methods or actions that belong to the object.
	public void eat() {
		color = 0;
	}
 	
	// Static Methods
	// Methods that belong to the class
	public static AnObject[] pickup() {
		AnObject[] temp = new AnObject[AnObject.howMany];
		for (int i=0; i<AnObject.howMany; i++) {
			temp[i] = new AnObject();
		}
		return temp;
	}
}
