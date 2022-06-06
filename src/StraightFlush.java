import java.util.Arrays;

/**
 * The StraightFlush Class is a subclass of the Hand Class and implements Hand_Interface.
 * It implements isValid() and getType() and overrides getTopCard() method from the Hand Class.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class StraightFlush extends Hand implements Hand_Interface{
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for Straight Type.
	 * 
	 * @param player The specified CardGamePlayer object
	 * @param cards The specified list of cards (CardList object)
	 */
	public StraightFlush(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return Card object which is the Top Card of the given Hand
	 */
	public Card getTopCard() {
		// Storing the ranks of all cards in an array
		int[] ranks = new int[5];
		for (int i = 0 ; i < 5; i ++) {
			int k = this.getCard(i).getRank();
			
			if (k == 0) { // Case of Ace
				ranks[i] = 13; //second highest rank
			}
			else if (k == 1 ) {// Case of 2
				ranks[i] = 14; //highest rank
			}
			
			else {
				ranks[i] = k;
			}
		}
		
		// Finding highest rank
		int highest_rank = ranks[0];
		for (int i = 0 ;i < 5; i ++) { // Looping the ranks array
			if (ranks[i] > highest_rank) {
				highest_rank = ranks[i];
			}
		}
		
		// Return Card with highest rank
		for (int i = 0 ; i < 5 ; i ++) {
			if (highest_rank == ranks[i]) {
				return (this.getCard(i));
			}
		}
		
		return null;	
	
	}

	/**
	 * This method is implemented from the Hand_Interface
	 * 
	 * @return True is the given hand is a Valid straightflush, False of it is not A striaghtflush type.
	 * 
	 */
	public boolean isValid() {
		// All straights should be made of 5 cards.
		if (this.size() != 5) {
			return false;
		}

		// All cards of same suit and in sequence
		int check_flush = 1;
		// Checking Same Suit/Flush Condition
		int flush_colour = this.getCard(0).getSuit();
		for (int i = 1 ; i < 5; i ++) {
			if(flush_colour != this.getCard(i).getSuit()) {
				check_flush = 0;
			}
		}
		
		// Checking Straight Condition
		int check_straight = 1;
		// Storing the ranks of all cards in an array
		int[] ranks = new int[5];
		for (int i = 0 ; i < 5; i ++) {
			int k = this.getCard(i).getRank();
			
			if (k == 0) { // Case of Ace
				ranks[i] = 13; //second highest rank
			}
			else if (k == 1 ) {// Case of 2
				ranks[i] = 14; //highest rank
			}
			
			else {
				ranks[i] = k;
			}
		} //ranks stores all
		
		//sorting ranks array
		Arrays.sort(ranks);
		
		for(int i = 1 ; i < 5; i ++) {
			if(ranks[i] != ranks[i-1] + 1) { // successive rank should be current rank + 1
				check_straight = 0 ;
			}
		}
	
		// if both conditions are passed -> only then straightflush IS VALID
		if (check_flush == 1 && check_straight == 1) {
			return true;
		}
		
		return false;
		
	}
	
	
	/**
	 * This method is implemented from the Hand_Interface
	 * 
	 * @return String Containing the type of hand.
	 * 
	 */
	public String getType() {
		return new String("StraightFlush");
	}
}
