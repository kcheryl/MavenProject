package scheduler.assignment;

import static org.junit.Assert.*;
import org.junit.Test;

public class ClientTest {
	String gameNameInvalid = "Invalid game name!";
	String playerNameInvalid = "Invalid player name!";
	String dayNameInvalid = "Invalid day name!";

	public Game[] gameErrorTemplate() {
		Game game1 = new Game();
		game1.setName("Socceer");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Football");
		game2.setNumPlayer(2);
		Game game3 = new Game();
		game3.setName("Badminton");
		game3.setNumPlayer(3);

		Game[] gameArr = { game1, game2, game3 };
		return gameArr;
	}

	@Test
	public void addGame() {
		String response = Client.addGame("Bowling", 2);
		String expected = "Game saved successfully!";
		assertEquals(expected, response);
	}

	@Test
	public void addGameNoName() {
		String response = Client.addGame("", 2);
		String expected = gameNameInvalid;
		assertEquals(expected, response);
	}

	@Test
	public void addGameNoPlayer() {
		String response = Client.addGame("Tennis1", 0);
		String expected = "Invalid number of players!";
		assertEquals(expected, response);
	}

	@Test
	public void addGameDuplicate() {
		Client.addGame("Basketball1", 2);
		String response = Client.addGame("Basketball1", 2);
		String expected = "Game is already created!";
		assertEquals(expected, response);
	}

	@Test
	public void addGameNullName() {
		String response = Client.addGame(null, 5);
		String expected = gameNameInvalid;
		assertEquals(expected, response);
	}

	@Test
	public void addPlayer() {
		Client.addGame("Tennis32", 2);

		Game game1 = new Game();
		game1.setName("Tennis32");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Football");
		game2.setNumPlayer(2);
		Game game3 = new Game();
		game3.setName("Badminton");
		game3.setNumPlayer(3);

		Game[] gameArr = { game1, game2, game3 };
		String response = Client.addPlayer("Peter", gameArr);
		String expected = "Player saved successfully!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNoGame() {
		Game[] gameArr = gameErrorTemplate();
		String response = Client.addPlayer("Rob", gameArr);
		String expected = "Player is invalid!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNoName() {

		Client.addGame("Tennis35", 2);
		Game[] gameArr = gameErrorTemplate();
		String response = Client.addPlayer("", gameArr);
		String expected = playerNameInvalid;
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerDuplicate() {
		Game game1 = new Game();
		game1.setName("Tennis46");
		game1.setNumPlayer(7);

		Client.addGame("Tennis46", 6);
		Game[] gameArr = { game1 };
		Client.addPlayer("Jane", gameArr);
		String response = Client.addPlayer("Jane", gameArr);
		String expected = "Player is already created!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNullName() {
		Client.addGame("Tennis54", 6);
		Game[] gameArr = gameErrorTemplate();
		String response = Client.addPlayer(null, gameArr);
		String expected = playerNameInvalid;
		assertEquals(expected, response);
	}

	@Test
	public void addDay() {
		Client.addGame("Tennis22", 4);
		Client.addGame("Rowing", 2);

		Game game1 = new Game();
		game1.setName("Tennis22");
		game1.setNumPlayer(2);
		Game game2 = new Game();
		game2.setName("Rowing");
		game2.setNumPlayer(4);

		Game[] gameArr = { game1, game2 };
		String response = Client.addDay("Day1", gameArr);
		String expected = "Day saved successfully!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNoGames() {
		Game game1 = new Game();
		game1.setName("Tennis2");
		game1.setNumPlayer(2);
		Game game2 = new Game();
		game2.setName("Rowing2");
		game2.setNumPlayer(4);

		Game[] gameArr = { game1, game2 };
		String response = Client.addDay("Day2", gameArr);
		String expected = "Day is invalid!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNoName() {
		Game game1 = new Game();
		game1.setName("Tennis23");
		game1.setNumPlayer(2);
		Game[] gameArr = { game1 };
		String response = Client.addDay("", gameArr);
		String expected = dayNameInvalid;
		assertEquals(expected, response);
	}

	@Test
	public void addDayDuplicate() {
		Client.addGame("Tennis24", 5);

		Game game1 = new Game();
		game1.setName("Tennis24");
		game1.setNumPlayer(2);

		Game[] gameArr = { game1 };
		Client.addDay("Day", gameArr);
		String response = Client.addDay("Day1", gameArr);
		String expected = "Day is already created!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNullName() {
		Client.addGame("Tennis25", 6);

		Game game1 = new Game();
		game1.setName("Tennis25");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		String response = Client.addDay(null, gameArr);
		String expected = dayNameInvalid;
		assertEquals(expected, response);
	}

	@Test
	public void getGameReport() {
		String gameName1 = "Tennis5";

		Client.addGame(gameName1, 6);
		Client.addGame("Swimming5", 2);

		Game game1 = new Game();
		game1.setName(gameName1);
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming5");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		Client.addDay("Day5", gameArr);
		Client.addPlayer("Tom", gameArr);
		Client.addPlayer("Mary", gameArr);
		String response = Client.generateGameWiseReport(gameName1).toString();
		String expected = "Game Wise Report for Tennis5\nDays: Day5 \nPlayers: Tom Mary ";
		assertEquals(expected, response);
	}

	@Test
	public void getGameReportNoGame() {
		Client.addGame("Tennis6", 6);
		Client.addGame("Swimming6", 2);

		Game game1 = new Game();
		game1.setName("Tennis6");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming6");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		Client.addDay("Day6", gameArr);
		Client.addPlayer("Justin", gameArr);
		Client.addPlayer("Willy", gameArr);
		String response = Client.generateGameWiseReport("Baseball").toString();
		String expected = "No records available for Baseball\n";
		assertEquals(expected, response);
	}

	@Test
	public void getGameReportEmptyGame() {
		Client.addGame("Tennis67", 6);
		Client.addGame("Swimming67", 2);

		Game game1 = new Game();
		game1.setName("Tennis67");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming67");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		Client.addDay("Day6", gameArr);
		Client.addPlayer("Justin", gameArr);
		Client.addPlayer("Willy", gameArr);
		String response = Client.generateGameWiseReport("").toString();
		String expected = gameNameInvalid;
		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReport() {
		Client.addGame("Tennis8", 6);
		Client.addGame("Swimming8", 2);

		Game game1 = new Game();
		game1.setName("Tennis8");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming8");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		Client.addDay("Day8", gameArr);
		Client.addPlayer("Justine", gameArr);
		Client.addPlayer("Wil", gameArr);
		String response = Client.generatePlayerWiseReport("Justine").toString();
		String expected = "Player Wise Report for Justine\n* Game: Tennis8\nDays: Day8\n* Game: Swimming8\nDays: Day8\n";
		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReportNoPlayer() {
		Game game1 = new Game();
		game1.setName("Tennis7");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming7");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		Client.addDay("Day7", gameArr);
		Client.addPlayer("Julian", gameArr);
		Client.addPlayer("Mix", gameArr);
		String response = Client.generatePlayerWiseReport("Peach").toString();
		String expected = "No records available for Peach\n";

		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReportEmptyPlayer() {
		Game game1 = new Game();
		game1.setName("Tennis7");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming7");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		Client.addDay("Day7", gameArr);
		Client.addPlayer("Julian", gameArr);
		Client.addPlayer("Mix", gameArr);
		String response = Client.generatePlayerWiseReport("").toString();
		String expected = playerNameInvalid;
		assertEquals(expected, response);
	}

	@Test
	public void getDayReport() {
		Client.addGame("Tennis9", 6);
		Client.addGame("Swimming9", 2);

		Game game1 = new Game();
		game1.setName("Tennis9");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming9");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		Client.addDay("Day9", gameArr);
		Client.addPlayer("Tim", gameArr);
		Client.addPlayer("Martha", gameArr);
		String response = Client.generateDayWiseReport("Day9").toString();
		String expected = "Day Wise Report for Day9\n* Game: Tennis9\nPlayers: Tim Martha\n* Game: Swimming9\nPlayers: Tim Martha\n";
		assertEquals(expected, response);
	}

	@Test
	public void getDayReportNoDay() {
		Client.addGame("Tennis100", 6);
		Client.addGame("Swimming100", 2);

		Game game1 = new Game();
		game1.setName("Tennis100");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming100");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
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

		Game game1 = new Game();
		game1.setName("Tennis10");
		game1.setNumPlayer(5);
		Game game2 = new Game();
		game2.setName("Swimming10");
		game2.setNumPlayer(2);

		Game[] gameArr = { game1, game2 };
		Client.addDay("Day10", gameArr);
		Client.addPlayer("Rachel", gameArr);
		Client.addPlayer("Bud", gameArr);
		String response = Client.generateDayWiseReport("").toString();
		String expected = dayNameInvalid;
		assertEquals(expected, response);
	}

}
