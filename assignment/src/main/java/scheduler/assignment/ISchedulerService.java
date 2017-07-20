package scheduler.assignment;

public interface ISchedulerService {
	public String createGame(Game g);

	public String createPlayer(Player p);

	public String createDay(Day d);

	public StringBuilder gameWiseReport(String gameName);

	public StringBuilder playerWiseReport(String playerName);

	public StringBuilder dayWiseReport(String dayName);
}
