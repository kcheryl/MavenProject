package scheduler.assignment;

import static org.junit.Assert.*;
import org.junit.Test;

public class ClientTest {

	@Test
	@SuppressWarnings("all")
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
		String response = Client.addGame("Tennis1", 0);
		String expected = "Invalid number of players!";
		assertEquals(expected, response);
	}

	@Test
	public void addGameDuplicate() {
		Client.addGame("Basketball2", 2);
		String response = Client.addGame("Basketball2", 2);
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
		Client.addGame("Tennis3", 2);
		Game[] gameArr = { new Game("Tennis3", 5), new Game("Football3", 2), new Game("Badminton3", 3) };
		String response = Client.addPlayer("Peter", gameArr);
		String expected = "Player saved successfully!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNoGame() {
		Game[] gameArr = { new Game("Soccer4", 5), new Game("Football4", 2), new Game("Badminton4", 3) };
		String response = Client.addPlayer("Rob", gameArr);
		String expected = "Player is invalid!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNoName() {
		Client.addGame("Tennis5", 2);
		Game[] gameArr = { new Game("Basketball5", 5), new Game("Tennis5", 2) };
		String response = Client.addPlayer("", gameArr);
		String expected = "Invalid player name!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerDuplicate() {
		Client.addGame("Tennis6", 6);
		Game[] gameArr = { new Game("Tennis6", 7) };
		Client.addPlayer("Jane", gameArr);
		String response = Client.addPlayer("Jane", gameArr);
		String expected = "Player is already created!";
		assertEquals(expected, response);
	}

	@Test
	public void addPlayerNullName() {
		Client.addGame("Tennis7", 6);
		Game[] gameArr = { new Game("Tennis7", 5), new Game("Swimming7", 2) };
		String response = Client.addPlayer(null, gameArr);
		String expected = "Invalid player name!";
		assertEquals(expected, response);
	}

	@Test
	public void addDay() {
		Client.addGame("Tennis8", 4);
		Client.addGame("Rowing8", 2);
		Game[] gameArr = { new Game("Tennis8", 2), new Game("Rowing8", 4) };
		String response = Client.addDay("Day1", gameArr);
		String expected = "Day saved successfully!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNoGames() {
		Game[] gameArr = { new Game("Tennis9", 2), new Game("Rowing9", 4) };
		String response = Client.addDay("Day2", gameArr);
		String expected = "Day is invalid!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNoName() {
		Game[] gameArr = { new Game("Tennis10", 2) };
		String response = Client.addDay("", gameArr);
		String expected = "Invalid day name!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayDuplicate() {
		Client.addGame("Tennis11", 5);
		Game[] gameArr = { new Game("Tennis11", 2) };
		Client.addDay("Day", gameArr);
		String response = Client.addDay("Day1", gameArr);
		String expected = "Day is already created!";
		assertEquals(expected, response);
	}

	@Test
	public void addDayNullName() {
		Client.addGame("Tennis12", 6);
		Game[] gameArr = { new Game("Tennis12", 5), new Game("Swimming12", 2) };
		String response = Client.addDay(null, gameArr);
		String expected = "Invalid day name!";
		assertEquals(expected, response);
	}

	@Test
	public void getGameReport() {
		Client.addGame("Tennis13", 6);
		Client.addGame("Swimming13", 2);
		Game[] gameArr = { new Game("Tennis13", 5), new Game("Swimming13", 2) };
		Client.addDay("Day5", gameArr);
		Client.addPlayer("Tom", gameArr);
		Client.addPlayer("Mary", gameArr);
		String response = Client.generateGameWiseReport("Tennis13").toString();
		String expected = "Game Wise Report for Tennis13\nDays: Day5 \nPlayers: Tom Mary ";
		assertEquals(expected, response);
	}

	@Test
	public void getGameReportNoGame() {
		Client.addGame("Tennis14", 6);
		Client.addGame("Swimming14", 2);
		Game[] gameArr = { new Game("Tennis14", 5), new Game("Swimming14", 2) };
		Client.addDay("Day6", gameArr);
		Client.addPlayer("Justin", gameArr);
		Client.addPlayer("Willy", gameArr);
		String response = Client.generateGameWiseReport("Baseball").toString();
		String expected = "No records available for Baseball\n";
		assertEquals(expected, response);
	}

	@Test
	public void getGameReportEmptyGame() {
		Client.addGame("Tennis15", 6);
		Client.addGame("Swimming15", 2);
		Game[] gameArr = { new Game("Tennis15", 5), new Game("Swimming15", 2) };
		Client.addDay("Day6", gameArr);
		Client.addPlayer("Justin", gameArr);
		Client.addPlayer("Willy", gameArr);
		String response = Client.generateGameWiseReport("").toString();
		String expected = "Invalid game name!";
		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReport() {
		Client.addGame("Tennis16", 6);
		Client.addGame("Swimming16", 2);
		Game[] gameArr = { new Game("Tennis16", 5), new Game("Swimming16", 2) };
		Client.addDay("Day8", gameArr);
		Client.addPlayer("Justine", gameArr);
		Client.addPlayer("Wil", gameArr);
		String response = Client.generatePlayerWiseReport("Justine").toString();
		String expected = "Player Wise Report for Justine\n* Game: Tennis16\nDays: Day8\n* Game: Swimming16\nDays: Day8\n";
		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReportNoPlayer() {
		Game[] gameArr = { new Game("Tennis17", 5), new Game("Swimming17", 2) };
		Client.addDay("Day7", gameArr);
		Client.addPlayer("Julian", gameArr);
		Client.addPlayer("Mix", gameArr);
		String response = Client.generatePlayerWiseReport("Peach").toString();
		String expected = "No records available for Peach\n";

		assertEquals(expected, response);
	}

	@Test
	public void getPlayerReportEmptyPlayer() {
		Game[] gameArr = { new Game("Tennis18", 5), new Game("Swimming18", 2) };
		Client.addDay("Day", gameArr);
		Client.addPlayer("Julian", gameArr);
		Client.addPlayer("Mix", gameArr);
		String response = Client.generatePlayerWiseReport("").toString();
		String expected = "Invalid player name!";
		assertEquals(expected, response);
	}

	@Test
	public void getDayReport() {
		Client.addGame("Tennis19", 6);
		Client.addGame("Swimming19", 2);
		Game[] gameArr = { new Game("Tennis19", 5), new Game("Swimming19", 2) };
		Client.addDay("Day9", gameArr);
		Client.addPlayer("Tim", gameArr);
		Client.addPlayer("Martha", gameArr);
		String response = Client.generateDayWiseReport("Day9").toString();
		String expected = "Day Wise Report for Day9\n* Game: Tennis19\nPlayers: Tim Martha\n* Game: Swimming19\nPlayers: Tim Martha\n";
		assertEquals(expected, response);
	}

	@Test
	public void getDayReportNoDay() {
		Client.addGame("Tennis20", 6);
		Client.addGame("Swimming20", 2);
		Game[] gameArr = { new Game("Tennis20", 5), new Game("Swimming20", 2) };
		Client.addDay("Day10", gameArr);
		Client.addPlayer("Rachel", gameArr);
		Client.addPlayer("Bud", gameArr);
		String response = Client.generateDayWiseReport("Day111").toString();
		String expected = "No records available for Day111\n";
		assertEquals(expected, response);
	}

	@Test
	public void getDayReportEmptyDay() {
		Client.addGame("Tennis21", 6);
		Client.addGame("Swimming21", 2);
		Game[] gameArr = { new Game("Tennis21", 5), new Game("Swimming21", 2) };
		Client.addDay("Day10", gameArr);
		Client.addPlayer("Rachel", gameArr);
		Client.addPlayer("Bud", gameArr);
		String response = Client.generateDayWiseReport("").toString();
		String expected = "Invalid day name!";
		assertEquals(expected, response);
	}
}