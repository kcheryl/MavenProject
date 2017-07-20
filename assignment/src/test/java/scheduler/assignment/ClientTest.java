package scheduler.assignment;
import static org.junit.Assert.*;
import org.junit.Test;

public class ClientTest {

	@Test
	public void addGame() {
		String response = Client.addGame("Bowling", 2);
		String expected = "Game saved successfully!";
		assertEquals(expected, response);
	}

	@Test
	public void addGameNoName() {
		String response = Client.addGame("", 2);
		String expected = "Invalid game name!";
		assertEquals(expected, response);
	}

	@Test
	public void addGameNoPlayer() {
		String response = Client.addGame("Tennis", 0);
		String expected = "Invalid number of players!";
		assertEquals(expected, response);
	}

	@Test
	public void addGameDuplicate() {
		Client.addGame("Basketball", 2);
		String response = Client.addGame("Basketball", 2);
		String expected = "Game is already created!";
		assertEquals(expected, response);
	}

	@Test
	public void addGameNullName() {
		String response = Client.addGame(null, 5);
		String expected = "Invalid game name!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayer() {
		Client.addGame("Tennis", 2);
		Game[] gameArr = { new Game("Tennis", 5), new Game("Football", 2), new Game("Badminton", 3) };
		String response = Client.addPlayer("Peter", gameArr);
		String expected = "Player saved successfully!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNoGame() {
		Game[] gameArr = { new Game("Soccer", 5), new Game("Football", 2), new Game("Badminton", 3) };
		String response = Client.addPlayer("Rob", gameArr);
		String expected = "Player is invalid!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNoName() {
		Client.addGame("Tennis", 2);
		Game[] gameArr = { new Game("Basketball", 5), new Game("Tennis", 2) };
		String response = Client.addPlayer("", gameArr);
		String expected = "Invalid player name!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerDuplicate() {
		Client.addGame("Tennis", 6);
		Game[] gameArr = { new Game("Tennis", 7) };
		Client.addPlayer("Jane", gameArr);
		String response = Client.addPlayer("Jane", gameArr);
		String expected = "Player is already created!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNullName() {
		Client.addGame("Tennis", 6);
		Game[] gameArr = { new Game("Tennis", 5), new Game("Swimming", 2) };
		String response = Client.addPlayer(null, gameArr);
		String expected = "Invalid player name!";
		assertEquals(expected, response);
	}

	@Test
	public void addDay() {
		Client.addGame("Tennis", 4);
		Client.addGame("Rowing", 2);
		Game[] gameArr = { new Game("Tennis", 2), new Game("Rowing", 4) };
		String response = Client.addDay("Day1", gameArr);
		String expected = "Day saved successfully!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNoGames() {
		Game[] gameArr = { new Game("Tennis2", 2), new Game("Rowing2", 4) };
		String response = Client.addDay("Day2", gameArr);
		String expected = "Day is invalid!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNoName() {
		Game[] gameArr = { new Game("Tennis", 2) };
		String response = Client.addDay("", gameArr);
		String expected = "Invalid day name!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayDuplicate() {
		Client.addGame("Tennis", 5);
		Game[] gameArr = { new Game("Tennis", 2) };
		Client.addDay("Day", gameArr);
		String response = Client.addDay("Day1", gameArr);
		String expected = "Day is already created!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNullName() {
		Client.addGame("Tennis", 6);
		Game[] gameArr = { new Game("Tennis", 5), new Game("Swimming", 2) };
		String response = Client.addDay(null, gameArr);
		String expected = "Invalid day name!";
		assertEquals(expected, response);
	}

	@Test
	public void getGameReport() {
		Client.addGame("Tennis5", 6);
		Client.addGame("Swimming5", 2);
		Game[] gameArr = { new Game("Tennis5", 5), new Game("Swimming5", 2) };
		Client.addDay("Day5", gameArr);
		Client.addPlayer("Tom", gameArr);
		Client.addPlayer("Mary", gameArr);
		String response = Client.generateGameWiseReport("Tennis5").toString();
		String expected = "Game Wise Report for Tennis5\nDays: Day5 \nPlayers: Tom Mary ";
		assertEquals(expected, response);
	}

	@Test
	public void getGameReportNoGame() {
		Client.addGame("Tennis6", 6);
		Client.addGame("Swimming6", 2);
		Game[] gameArr = { new Game("Tennis6", 5), new Game("Swimming6", 2) };
		Client.addDay("Day6", gameArr);
		Client.addPlayer("Justin", gameArr);
		Client.addPlayer("Willy", gameArr);
		String response = Client.generateGameWiseReport("Baseball").toString();
		String expected = "No records available for Baseball\n";
		assertEquals(expected, response);
	}

	@Test
	public void getGameReportEmptyGame() {
		Client.addGame("Tennis6", 6);
		Client.addGame("Swimming6", 2);
		Game[] gameArr = { new Game("Tennis6", 5), new Game("Swimming6", 2) };
		Client.addDay("Day6", gameArr);
		Client.addPlayer("Justin", gameArr);
		Client.addPlayer("Willy", gameArr);
		String response = Client.generateGameWiseReport("").toString();
		String expected = "Invalid game name!";
		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReport() {
		Client.addGame("Tennis8", 6);
		Client.addGame("Swimming8", 2);
		Game[] gameArr = { new Game("Tennis8", 5), new Game("Swimming8", 2) };
		Client.addDay("Day8", gameArr);
		Client.addPlayer("Justine", gameArr);
		Client.addPlayer("Wil", gameArr);
		String response = Client.generatePlayerWiseReport("Justine").toString();
		String expected = "Player Wise Report for Justine\n* Game: Tennis8\nDays: Day8\n* Game: Swimming8\nDays: Day8\n";
		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReportNoPlayer() {
		Game[] gameArr = { new Game("Tennis7", 5), new Game("Swimming7", 2) };
		Client.addDay("Day7", gameArr);
		Client.addPlayer("Julian", gameArr);
		Client.addPlayer("Mix", gameArr);
		String response = Client.generatePlayerWiseReport("Peach").toString();
		String expected = "No records available for Peach\n";

		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReportEmptyPlayer() {
		Game[] gameArr = { new Game("Tennis7", 5), new Game("Swimming7", 2) };
		Client.addDay("Day7", gameArr);
		Client.addPlayer("Julian", gameArr);
		Client.addPlayer("Mix", gameArr);
		String response = Client.generatePlayerWiseReport("").toString();
		String expected = "Invalid player name!";
		assertEquals(expected, response);
	}

	@Test
	public void getDayReport() {
		Client.addGame("Tennis9", 6);
		Client.addGame("Swimming9", 2);
		Game[] gameArr = { new Game("Tennis9", 5), new Game("Swimming9", 2) };
		Client.addDay("Day9", gameArr);
		Client.addPlayer("Tim", gameArr);
		Client.addPlayer("Martha", gameArr);
		String response = Client.generateDayWiseReport("Day9").toString();
		String expected = "Day Wise Report for Day9\n* Game: Tennis9\nPlayers: Tim Martha\n* Game: Swimming9\nPlayers: Tim Martha\n";
		assertEquals(expected, response);
	}

	@Test
	public void getDayReportNoDay() {
		Client.addGame("Tennis10", 6);
		Client.addGame("Swimming10", 2);
		Game[] gameArr = { new Game("Tennis10", 5), new Game("Swimming10", 2) };
		Client.addDay("Day10", gameArr);
		Client.addPlayer("Rachel", gameArr);
		Client.addPlayer("Bud", gameArr);
		String response = Client.generateDayWiseReport("Day111").toString();
		String expected = "No records available for Day111\n";
		assertEquals(expected, response);
	}

	@Test
	public void getDayReportEmptyDay() {
		Client.addGame("Tennis10", 6);
		Client.addGame("Swimming10", 2);
		Game[] gameArr = { new Game("Tennis10", 5), new Game("Swimming10", 2) };
		Client.addDay("Day10", gameArr);
		Client.addPlayer("Rachel", gameArr);
		Client.addPlayer("Bud", gameArr);
		String response = Client.generateDayWiseReport("").toString();
		String expected = "Invalid day name!";
		assertEquals(expected, response);
	}

}
