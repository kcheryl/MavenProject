package scheduler.assignment;

public class Day {
	private String name;
	private Game[] games;

	Day(String name, Game[] games) {
		this.name = name;
		this.games = games;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public Game[] getGame() {
		return games;
	}

	public void setGame(Game[] newGames) {
		this.games = newGames;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
