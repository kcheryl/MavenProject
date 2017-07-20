package scheduler.assignment;

public class SchedulerServiceImp implements ISchedulerService {
	IGameRepo gameRepo;
	IPlayerRepo playerRepo;
	IDayRepo dayRepo;

	SchedulerServiceImp() {
		gameRepo = new GameRepoImp();
		playerRepo = new PlayerRepoImp();
		dayRepo = new DayRepoImp();
	}

	SchedulerServiceImp(IGameRepo gameRepo, IPlayerRepo playerRepo, IDayRepo dayRepo) {
		this.gameRepo = gameRepo;
		this.playerRepo = playerRepo;
		this.dayRepo = dayRepo;
	}

	public String createGame(Game g) {
		String error = checkGame(g);
		if (error != null) {
			return error;
		}

		Game game = gameRepo.findOne(g.getName());
		// if game does not exist
		if (game == null) {
			return gameRepo.save(g);
		}
		// if game already exist
		else {
			return "Game is already created!";
		}
	}

	public String checkGame(Game g) {
		String name = g.getName();
		int numPlayers = g.getNumPlayer();
		if (name == null || !name.matches(".*[a-z].*")) {
			return "Invalid game name!";
		}
		if (numPlayers < 1) {
			return "Invalid number of players!";
		}
		return null;
	}

	public String createPlayer(Player p) {
		String error = checkPlayer(p);
		if (error != null) {
			return error;
		}

		Player player = playerRepo.findOne(p.getName());
		// if player does not exist
		if (player == null) {
			// check if player is valid (at least 1 game is part of the system)
			if (p.getGame().length > 0) {
				for (Game game : p.getGame()) {
					Game temp = gameRepo.findOne(game.getName());
					// if game exist
					if (temp != null) { // if game exist
						// player is valid
						return playerRepo.save(p);
					}
				}
			}
			return "Player is invalid!";
		}
		// if player already exist
		else {
			return "Player is already created!";
		}
	}

	public String checkPlayer(Player p) {
		String name = p.getName();
		Game[] games = p.getGame();
		if (name == null || !name.matches(".*[a-z].*")) {
			return "Invalid player name!";
		}
		if (games.length < 1) {
			return "Invalid number of players!";
		}
		for (Game game : games) {
			// check if game in games are valid
			String response = checkGame(game);
			if (response != null) {
				return response;
			}
		}
		return null;
	}

	public String createDay(Day d) {
		String error = checkDay(d);
		if (error != null) {
			return error;
		}

		Day day = dayRepo.findOne(d.getName());
		// if day does not exist
		if (day == null) {
			// check if day is valid (all games are part of the system)
			if (d.getGame().length > 0) {
				for (Game game : d.getGame()) {
					Game temp = gameRepo.findOne(game.getName());
					// if day do not exist
					if (temp == null) {
						return "Day is invalid!";
					}
				}
			}
			return dayRepo.save(d);
		}
		// if day already exist
		else {
			return "Day is already created!";
		}
	}

	public String checkDay(Day d) {
		String day = d.getName();
		Game[] games = d.getGame();
		if (day == null || !day.matches(".*[a-z].*")) {
			return "Invalid day name!";
		}
		if (games.length < 1) {
			return "Invalid number of players!";
		}
		for (Game game : games) {
			// check if game in games are valid
			String response = checkGame(game);
			if (response != null) {
				return response;
			}
		}
		return null;
	}

	public StringBuilder gameWiseReport(String gameName) {
		StringBuilder sb = new StringBuilder();

		if (gameName == null || !gameName.matches(".*[a-z].*")) {
			sb.append("Invalid game name!");
			return sb;
		}

		Game g = gameRepo.findOne(gameName);
		// if game does not exist
		if (g == null) {
			sb.append("No records available for " + gameName + "\n");
			return sb;
		}
		sb.append("Game Wise Report for " + gameName + "\n");

		// get all days from DayRepoImp
		Day[] days = dayRepo.findAll();
		// check for the game every day
		sb.append("Days: ");
		for (Day day : days) {
			if (day == null) {
				continue;
			}
			// check games from individual days
			Game[] dayGames = day.getGame();
			for (Game dayGame : dayGames) {
				if (dayGame.getName().equals(g.getName())) {
					sb.append(day.getName() + " ");
					break;
				}
			}
		}
		sb.append("\n");
		// get all players from PlayerRepoImp
		Player[] players = playerRepo.findAll();
		sb.append("Players: ");
		for (Player player : players) {
			if (player == null) {
				continue;
			}
			// check games from individual players
			Game[] playerGames = player.getGame();
			for (Game playerGame : playerGames) {
				if (playerGame.getName().equals(g.getName())) {
					sb.append(player.getName() + " ");
					break;
				}
			}
		}
		return sb;
	}

	public StringBuilder playerWiseReport(String playerName) {
		StringBuilder sb = new StringBuilder();

		if (playerName == null || !playerName.matches(".*[a-z].*")) {
			sb.append("Invalid player name!");
			return sb;
		}

		Player p = playerRepo.findOne(playerName);
		// if player does not exist
		if (p == null) {
			sb.append("No records available for " + playerName + "\n");
			return sb;
		}
		sb.append("Player Wise Report for " + playerName + "\n");
		// get all games from Player
		Game[] games = p.getGame();
		// check for every game for that player
		for (Game game : games) {
			if (game == null) {
				continue;
			}
			sb.append("* Game: " + game.getName() + "\n");
			// get all days from DayRepoImp
			Day[] days = dayRepo.findAll();
			sb.append("Days:");
			for (Day day : days) {
				if (day == null) {
					continue;
				}
				// check games from individual days
				Game[] dayGames = day.getGame();
				for (Game dayGame : dayGames) {
					// if the games (individual players) is same as the day game
					if (dayGame.getName().equals(game.getName())) {
						sb.append(" " + day.getName());
						break;
					}

				}
			}
			sb.append("\n");
		}
		return sb;
	}

	public StringBuilder dayWiseReport(String dayName) {
		StringBuilder sb = new StringBuilder();

		if (dayName == null || !dayName.matches(".*[a-z].*")) {
			sb.append("Invalid day name!");
			return sb;
		}

		Day d = dayRepo.findOne(dayName);
		// if day does not exist
		if (d == null) {
			sb.append("No records available for " + dayName + "\n");
			return sb;
		}
		sb.append("Day Wise Report for " + dayName + "\n");
		// get all games from Day
		Game[] games = d.getGame();
		// check for every game on that day
		for (Game game : games) {
			if (game == null) {
				continue;
			}
			sb.append("* Game: " + game.getName() + "\n");
			// get all players from PlayerRepoImp
			sb.append("Players:");
			Player[] players = playerRepo.findAll();
			for (Player player : players) {
				if (player == null) {
					continue;
				}
				// check games from individual players
				Game[] playerGames = player.getGame();
				for (Game playerGame : playerGames) {
					// if the games (individual players) is same as the day game
					if (playerGame.getName().equals(game.getName())) {
						sb.append(" " + player.getName());
						break;
					}

				}
			}
			sb.append("\n");
		}
		return sb;
	}
}
