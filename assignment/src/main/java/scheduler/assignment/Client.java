package scheduler.assignment;

public class Client {
	static ISchedulerService service;
	static {
		IGameRepo gameRepo = new GameRepoImp();
		IPlayerRepo playerRepo = new PlayerRepoImp();
		IDayRepo dayRepo = new DayRepoImp();

		service = new SchedulerServiceImp(gameRepo, playerRepo, dayRepo);
	}

	public static void main(String[] args) {
	}

	public static String addGame(String name, int numPlayers) {
		Game newGame = new Game();
		newGame.setName(name);
		newGame.setNumPlayer(numPlayers);
		return service.createGame(newGame);
	}

	public static String addPlayer(String name, Game[] games) {
		Player newPlayer = new Player();
		newPlayer.setName(name);
		newPlayer.setGame(games);
		return service.createPlayer(newPlayer);
	}

	public static String addDay(String name, Game[] games) {
		Day newDay = new Day();
		newDay.setName(name);
		newDay.setGame(games);
		return service.createDay(newDay);
	}

	public static StringBuffer generateGameWiseReport(String gameName) {
		return service.gameWiseReport(gameName);
	}

	public static StringBuffer generatePlayerWiseReport(String playerName) {
		return service.playerWiseReport(playerName);
	}

	public static StringBuffer generateDayWiseReport(String dayName) {
		return service.dayWiseReport(dayName);
	}
}
