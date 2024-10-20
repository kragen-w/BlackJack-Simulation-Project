package HW2;

import java.util.ArrayList;
import java.util.Random;


public class Deck {

	private ArrayList<Card> deck;

	
	public Deck() {
		this.deck = new ArrayList<>();
		char[] suits = {'c', 's', 'h', 'd'};
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				this.deck.add(new Card(i+2,suits[j]));
			}
		}
	}
	public String toString() {
		return "Deck: " + deck.toString();
	}
	public Card deal() {
		return deck.remove(0);
	}
	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < 100; i ++) {
			int randomOne = random.nextInt(deck.size() - 1);
			int randomTwo = random.nextInt(deck.size() - 1);
			Card cardOne = deck.get(randomOne);
			Card cardTwo = deck.get(randomTwo);
			deck.set(randomOne, cardTwo);
			deck.set(randomTwo, cardOne);
		}
		
		
	}
	public void stack() {
		int[] acesIndexes = new int[4];
		int whichIndex = 0;
		for(int i = 0; i < deck.size(); i++) {
			if (deck.get(i).getRank() == 14) {
				acesIndexes[whichIndex] = i;
				whichIndex += 1;
			}
		}
		for(int i = 0; i < 4; i++) {
			Card out = deck.get(i);
			Card in = deck.get(acesIndexes[i]);
			deck.set(i,  in);
			deck.set(acesIndexes[i], out);
		}
	}
}
