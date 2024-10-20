package HW2;


public class Player {
	private Hand playerHand;
	private int standValue;
	private boolean softStand;
	
	public Player(int standValue, boolean softValue) {
	       this.standValue = standValue;
	       this.softStand = softValue;
	       this.playerHand = new Hand();
	}
	
	public String toString() {
		return playerHand.toString();
	}
	
	public void play(Deck deck) {
		while (playerHand.getScore() < standValue || (softStand = false & playerHand.getSoft() == true & playerHand.getScore() >= standValue)) {
			playerHand.addCard(deck.deal());
		}
		//this very long while condition says:
		//if the hand score is below the stand value, they play.
		//but, if they are doing hardstands, the hand is soft, and they are at their stand value, they will hit

		return;
		
	}
	
	public Boolean isBust() {
		return playerHand.getScore() > 21;
	}
	
	public int compareScores(Player player) {
		return this.playerHand.getScore() - player.playerHand.getScore();
	}
}
