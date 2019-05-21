
public class Player implements Comparable<Player> {
	private String name;
	private int score;
	private int choice;
	
	public Player(String name) {
		this.name = name;
		choice = -1;
	}
	
	public String toString() {
		return  name + " Score: " + score;
	}
	
	public void setChoice(int c) {
		choice = c;
	}
	
	public void checkAnswer(int correct) {
		if (choice == correct) {
			score++;
		}
		choice = -1;
	}

	public boolean isSet() {
		return choice != -1;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(Player o) {
		return o.score - this.score;
	}
}
