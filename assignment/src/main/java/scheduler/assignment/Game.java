package scheduler.assignment;

public class Game {
	private String name;
	private int numPlayer;

	Game(String name, int numPlayer) {
		this.name = name;
		this.numPlayer = numPlayer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public int getNumPlayer() {
		return numPlayer;
	}

	public void setNumPlayer(int newNumPlayer) {
		this.numPlayer = newNumPlayer;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
