package HW2;

public class BlackJackBasicTest {

	public static void main(String[] args) {
		Deck theDeck = new Deck();
		theDeck.shuffle();
		Player playerOne = new Player(18);
		playerOne.play(theDeck);
		
		System.out.println(playerOne.toString());
		
		

	}

}
