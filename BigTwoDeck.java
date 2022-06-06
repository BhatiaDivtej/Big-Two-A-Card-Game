
/**
 * The BigTwoDeck class is a subclass of the Deck class. ( its superclass is CardList )
 * This class inherits methods from Deck as well as CardList
 * It is used to model a deck of cards used in a Big Two card game
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class BigTwoDeck extends Deck{
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	/**
	 * An overriding method for initializing a deck of Big Two cards.
	 * It should remove all cards from the deck, create 52 Big Two cards and add them to the deck.
	 * 
	 */
	public void initialize() {
		//Removing all cards from deck
		removeAllCards();
		
		//Create 52 new Big Two Cards
		for (int i = 0 ; i < 4; i ++) {
			for (int j = 0 ; j < 13; j ++) {
				// create new Big Two Cards using its constructor
				BigTwoCard bigtwocard = new BigTwoCard(i, j);
				//add the big two card to deck 
				this.addCard(bigtwocard);
			}
		}
		
	}
	
}
