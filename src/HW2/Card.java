package HW2;




public class Card {
	private int rank;
	private char suit;
	
	public Card(int rank, char suit) {
       this.rank = rank;
       this.suit = suit;
	}
	
	public String toString() {
		if (rank <= 10) {
			return ("Card: " + rank + suit);
		}
		String royalty = "";
		if (rank == 11) {
			royalty = "J";
		}
		else if (rank == 12) {
			royalty = "Q";
		}
		else if (rank == 13) {
			royalty = "K";
		}
		else if (rank == 14) {
			royalty = "A";
		}
		return ("Card: " + royalty + suit);
	}
	
	public Card(Card that) {
		this.rank = that.rank;
		this.suit = that.suit;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card that = (Card)obj;
			if(!(this.rank == that.rank)) {
				return false;
				
			}
			
			if(suit != that.suit) {
				return false;
				
			}
			return true;
		}
		return false;
	}
	
	public int getRank() {
		return rank;
	}
		
	public char getSuit() {
		return suit;
	}
	
	public int getScore() {
		if (rank <= 10) {
			return rank;
		}
		else if(rank == 14) {
			return 11;
		}
		else {
			return 10;
		}
	}
	
	
	
		
	
}