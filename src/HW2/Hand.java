package HW2;

import java.util.ArrayList;


public class Hand {

	private ArrayList<Card> cardList;
	private int Score;
	private boolean Soft;
	
	public Hand() {
	       this.cardList = new ArrayList<>();
	       this.Score = 0;
	       this.Soft = false;
	}

	public int getScore() {
		return Score;
	}
	
	public boolean getSoft() {
		return Soft;
	}
	
	public String toString() {
		return "Score: " + Score + ", Hand: " + cardList.toString() + "Soft: "+ Soft;
	}																																					
	
	public void addCard(Card cardAdd) {
		cardList.add(cardAdd);
		updateScore(cardAdd);
		}
	
	public void updateScore(Card card) {
		if (card.getRank() == 14) {
			if (Score < 11) { //The hand can take an 11 Ace 
				this.Soft = true;
				this.Score += 11;
			}
			else if (Score > 10) { //The hand has to take a 1 Ace 
				this.Soft = false;
				this.Score += 1;
			}
		}
		else if (Score + card.getScore() > 21 & Soft == true) {//The hand has an ace that is an 11
//			that needs to be turned into a 1, making the hand hard
			Score -= 10;
			Soft = false;
		}
		Score += card.getScore();
	}
	
	public void removeCard(Card cardRemove) {
		cardList.remove(cardRemove);
		}
	}

