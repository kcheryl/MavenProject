package scheduler.assignment;

public class Client {
	static ISchedulerService service;
	static {
		IGameRepo gameRepo = new GameRepoImp();
		IPlayerRepo playerRepo = new PlayerRepoImp();
		IDayRepo dayRepo = new DayRepoImp();

		service = new SchedulerServiceImp(gameRepo, playerRepo, dayRepo);
	}

	public static String addGame(String name, int numPlayers) {
		return service.createGame(new Game(name, numPlayers));
	}

	public static String addPlayer(String name, Game[] games) {
		return service.createPlayer(new Player(name, games));
	}

	public static String addDay(String name, Game[] games) {
		return service.createDay(new Day(name, games));
	}

	public static StringBuilder generateGameWiseReport(String gameName) {
		return service.gameWiseReport(gameName);
	}

	public static StringBuilder generatePlayerWiseReport(String playerName) {
		return service.playerWiseReport(playerName);
	}

	public static StringBuilder generateDayWiseReport(String dayName) {
		return service.dayWiseReport(dayName);
	}
}
