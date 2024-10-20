package HW2;

import java.util.ArrayList;

public class Testing {
	public static void testCard() {
		// Constructor
		Card c1 = new Card(5, 's');
		Card c2 = new Card(12, 'c');
		
		// toString
		assertEquals("Card: 5s", c1.toString(), "toString test1");
		assertEquals("Card: Qc", c2.toString(), "toString test1");
		
		// equals
		Card c3 = new Card(5, 's');
		// Make sure they are not the same reference
		assertDifferent(c1, c3, "== test");

		// Make sure equals works
		// note this would also work if they used Card instead of Object in equals
		assertTrue(c1.equals(c3) && c3.equals(c1), "equals test1");

		// Make sure equals works
		// Checking for object in equals method
		assertEquals(c1, c3, "equals test2");

		// Copy constructor
		Card c4 = new Card(c2);
		assertDifferent(c2, c4, "copy test1");
		
		// Make sure equals works
		assertEquals(c2, c4, "copy test2");
		
		// Equals differing by only suite:
		Card card = new Card(5, 'c');
		assertNotEquals(c1, card, "equals suit different test");
		
		// Jack, Queen
		assertEquals("Card: Js", new Card(11, 's').toString(), "Jack test");
		assertEquals("Card: Qd", new Card(12, 'd').toString(), "Queen test");
		assertEquals("Card: Kh", new Card(13, 'h').toString(), "King test");
		assertEquals("Card: Ac", new Card(14, 'c').toString(), "Ace test");
	}
	
	public static void testHand() {
		Hand h1 = new Hand();
		assertEquals("Hand: []", h1.toString(), "empty hand test");
		
		Card c1 = new Card(9, 's');
		h1.addCard(c1);
		assertEquals("Hand: [Card: 9s]", h1.toString(), "add 1 hand test");
		
		Card c2 = new Card(2, 'd');
		h1.addCard(c2);
		assertEquals("Hand: [Card: 9s, Card: 2d]", h1.toString(), "add 2nd hand test");
		
		Card c2copy = new Card(c2);
		h1.removeCard(c2copy);
		assertEquals("Hand: [Card: 9s]", h1.toString(), "remove last hand test");
		
		Card c3 = new Card(11, 'c');
		h1.removeCard(c3); // not found in hand, shouldn't do anything
		assertEquals("Hand: [Card: 9s]", h1.toString(), "remove not found hand test");

		Card c1copy = new Card(c1);
		h1.removeCard(c1copy);
		assertEquals("Hand: []", h1.toString(), "removed all hand test");

	}
	
	public static void testDeck() {
		Deck deck1 = new Deck();
		String deck1String = deck1.toString();
		assertTrue(deck1String.startsWith("Deck:"), "Deck toString startsWith test");
		
		System.out.println("DEBUG: Should be a full deck: " + deck1.toString());
		
		// when the Deck object is newly created, it should have all cards in it.
		for(char suit : new char [] { 'c', 'd', 'h', 's' }) {
			for(int rank = 2; rank < 15; rank++) {
				Card testCard = new Card(rank, suit);
				int idx = deck1String.indexOf(testCard.toString());
				assertTrue(idx >= 0, String.format("Deck find '%s' in Deck.toString()", testCard.toString()));
			}
		}
		
		// After a shuffle, the Deck should still have all the cards in but should be different.
		Deck deck2 = new Deck();
		deck2.shuffle();
		String deck2String = deck2.toString();
		
		for(char suit : new char [] { 'c', 'd', 'h', 's' }) {
			for(int rank = 2; rank < 15; rank++) {
				Card testCard = new Card(rank, suit);
				int idx = deck2String.indexOf(testCard.toString());
				assertTrue(idx >= 0, String.format("Deck find '%s' in Deck.toString() after shuffle()", testCard.toString()));
			}
		}
		
		// After dealing 52 items, the deck should be empty
		Deck deck3 = new Deck();
		for(int i=0; i<52; i++) {
			Card c = deck3.deal();
		}
		assertEquals("Deck: []", deck3.toString(), "Deck empty after dealing all.");
		
		// After shuffle and then stack, aces should be the first 4, in some order
		ArrayList<Card> acesList = new ArrayList<Card>();
		acesList.add(new Card(14, 'c'));
		acesList.add(new Card(14, 'd'));
		acesList.add(new Card(14, 'h'));
		acesList.add(new Card(14, 's'));

		Deck deck4 = new Deck();
		deck4.shuffle();
		deck4.stack();

		// just deal the first 4 cards and remove them from the aces set
		// that set should be empty after
		for(int i=0; i<4; i++) {
			Card c = deck4.deal();
			assertTrue(acesList.contains(c), String.format("Deck first four after stack() are aces: %s", c.toString()));
		}
	}

	public static void main(String[] args) {
		
		testCard();
		testHand();
		testDeck();
		
		System.out.print(String.format("Overall results: %d assertions with %d failures.",
				s_numAssertions, s_numFailures));
		if(s_numFailures == 0) {
			System.out.println(" Great job!");
		}
		else {
			System.out.println(" Keep working!");
		}
		
		
	}
	
	
	private static int s_numAssertions = 0;
	private static int s_numFailures = 0;
	
	private static void assertTrue(boolean passed, String label)
	{
		s_numAssertions++;	
		if(!passed) {
			System.out.println("FAIL: assertTrue: expected <true> but was <false>:" + label);
			s_numFailures++;
		}
		else {
			System.out.println("PASS: " + label);

		}
	}
	
	private static void assertSame(Object object1, Object object2, String label)
	{
		s_numAssertions++;
		if(object1 != object2) {
			System.out.println("FAIL: assertSame: objects are different <" + object1 + ">, <" + object2 + ">");
			s_numFailures++;
		}
		else {
			System.out.println("PASS: " + label);

		}
	}
	
	private static void assertDifferent(Object object1, Object object2, String label)
	{
		s_numAssertions++;
		if(object1 == object2) {
			System.out.println("FAIL: assertDifferent: objects are the same <" + object1 + ">, <" + object2 + ">");
			s_numFailures++;
		}
		else {
			System.out.println("PASS: " + label);

		}		
	}

	private static void assertEquals(String expected, String actual, String label)
	{
		s_numAssertions++;
		if(!expected.equals(actual)) {
			System.out.println("FAIL: assertEquals: expected <" + expected + ">, but was <" + actual + ">");
			s_numFailures++;
		}
		else {
			System.out.println("PASS: " + label);

		}		
	}

	private static void assertEquals(Object obj1, Object obj2, String label)
	{
		s_numAssertions++;
		if(!obj1.equals(obj2) || !obj2.equals(obj1)) {
			System.out.println("FAIL: assertEquals: objects not equal <" + obj1 + "> and <" + obj2 + ">");
			s_numFailures++;
		}
		else {
			System.out.println("PASS: " + label);

		}		
	}

	private static void assertNotEquals(Object obj1, Object obj2, String label)
	{
		s_numAssertions++;
		if(obj1.equals(obj2) || obj2.equals(obj1)) {
			System.out.println("FAIL: assertNotEquals: objects equal <" + obj1 + "> and <" + obj2 + ">");
			s_numFailures++;
		}
		else {
			System.out.println("PASS: " + label);

		}		
	}
}
