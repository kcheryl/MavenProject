package scheduler.assignment;

public class GameRepoImp implements IGameRepo {
	private Game[] games;
	private int numGames;

	GameRepoImp() {
		games = new Game[5];
		numGames = 0;
	}

	public String save(Game g) {
		if (numGames < games.length) {
			games[numGames] = g;
			numGames++;
		} else {
			Game[] oldGames = games.clone();
			games = new Game[numGames + 5];
			for (int i = 0; i < oldGames.length; i++) {
				games[i] = oldGames[i];
			}
			games[numGames] = g;
			numGames++;
		}
		return "Game saved successfully!";
	}

	public Game findOne(String name) {
		for (Game game : games) {
			// if game exists
			if (game != null && game.getName().equals(name)) {
				return game;
			}
		}
		// if game does not exist
		return null;

	}

	public Game[] findAll() {
		return this.games;
	}

}
