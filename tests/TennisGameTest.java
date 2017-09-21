import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		assertEquals("Player1 wins - incorrect", "player1 wins", game.getScore() );
	}
	
	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		assertEquals("Player2 wins - incorrect", "player2 wins", game.getScore() );
	}
	
	@Test
	public void testTennisGame_30_30() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();

		assertEquals("30 30 - incorrect", "30 - 30", game.getScore() );
	}
	
	@Test
	public void testTennisGame_40_40() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		assertEquals("40 40 - incorrect", "40 - 40", game.getScore() );
	}
	
	@Test
	public void testTennisGame_Game15_Love() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player1Scored();

		assertEquals("15 love - incorrect", "15 - love", game.getScore() );
	}
	
	@Test
	public void testTennisGame_GameLove_15() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player2Scored();

		assertEquals("love 15 - incorrect", "Love - 15", game.getScore() );
	}
	
	@Test
	public void testTennisGame_GameLove_30() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player2Scored();
		game.player2Scored();

		assertEquals("love 30 - incorrect", "Love - 30", game.getScore() );
	}
	
	@Test
	public void testTennisGame_Game30_Love() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();

		assertEquals("30 Love - incorrect", "30 - Love", game.getScore() );
	}
	
	@Test
	public void testTennisGame_Game40_Love() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		assertEquals("40 love - incorrect", "40 - love", game.getScore() );
	}
	
	@Test
	public void testTennisGame_GameLove_40() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		assertEquals("Love 40 - incorrect", "Love - 40", game.getScore() );
	}
	
	@Test
	public void testTennisGame_Game15_30() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();

		assertEquals("15 30 - incorrect", "15 - 30", game.getScore() );
	}
	
	@Test
	public void testTennisGame_Game40_15() throws TennisGameException {
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();

		assertEquals("40 15 - incorrect", "40 - 15", game.getScore() );
	}
	
	@Test
	public void testTennisGame_Player1HasAdvantage() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		assertEquals("Player1 has advantage - incorrect", "player1 has advantage", game.getScore() );
	}
	
	@Test
	public void testTennisGame_Player2HasAdvantage() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		assertEquals("Player2 has advantage - incorrect", "player2 has advantage", game.getScore() );
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();			
	}
}
