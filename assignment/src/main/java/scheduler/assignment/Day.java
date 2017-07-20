package scheduler.assignment;

public class Day {
	private String dayName;
	private Game[] dayGames;

	public Day(String dayName, Game[] dayGames) {
		this.dayName = dayName;
		this.dayGames = dayGames;
	}

	public String getName() {
		return this.dayName;
	}

	public Game[] getGame() {
		return dayGames;
	}

	public void setName(String newName) {
		this.dayName = newName;
	}

	public void setGame(Game[] newGames) {
		this.dayGames = newGames;
	}

	@Override
	public String toString() {
		return this.dayName;
	}
}
