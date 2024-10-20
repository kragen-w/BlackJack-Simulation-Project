package HW2;

public class BlackJackOdds {
	
	public static String softToString(boolean bool) {
		if (bool == true) {
			return "Soft";
		}
		return "Hard";
	}

	public static void main(String[] args) {
		for (int i = 16; i < 19; i++) {
			for (int j = 16; j < 19; j++) {
				for (int h = 1; h < 5; h++) {
					boolean dealerSoft = true; // this remains if h == 1
					boolean playerSoft = true;
					if (h == 2) {
						 dealerSoft = true;
						 playerSoft = false;
					}
					else if (h == 3) {
						 dealerSoft = false;
						 playerSoft = true;
					}
					else if (h == 4) {
						 dealerSoft = false;
						 playerSoft = false;
					}
					float dealerWins = 0;
					float playerWins = 0;
					float ties = 0;
					for (int k = 0; k < 5000; k++) {
						Deck theDeck = new Deck();
						theDeck.shuffle();
						Player Dealer = new Player(i, dealerSoft);
						Player normalPlayer = new Player(j, playerSoft);
						normalPlayer.play(theDeck);
						Dealer.play(theDeck);
						if (normalPlayer.isBust()) {
							dealerWins += 1;
						}
						else if(Dealer.isBust() & !(normalPlayer.isBust())) {
							playerWins += 1;
						}
						else {
							if (normalPlayer.compareScores(Dealer) < 0) {
								playerWins += 1;
							}
							else if(normalPlayer.compareScores(Dealer) > 0) {
								dealerWins += 1;
							}
							else {
								ties += 1;
							}
						}
					}
					System.out.println("Dealer(" + softToString(dealerSoft) + ") " + i + " vs Player(" + softToString(playerSoft) + ") "+ j);
					System.out.println("Dealer Wins: "+ (dealerWins/5000)*100);
					System.out.println("Player Wins: "+ (playerWins/5000)*100);
					System.out.println("No Winner: "+(ties/5000)*100);
					System.out.println("");
				}
			}
		}
	}
}
