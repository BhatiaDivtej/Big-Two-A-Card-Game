import java.util.Arrays;

/**
 * The Straight Class is a subclass of the Hand Class and implements Hand_Interface.
 * It implements isValid() and getType() and overrides getTopCard() method from the Hand Class.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class Straight extends Hand implements Hand_Interface{
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for Straight Type.
	 * 
	 * @param player The specified CardGamePlayer object
	 * @param cards The specified list of cards (CardList object)
	 */
	public Straight(CardGamePlayer player, CardList cards) {
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
	 * @return True is the given hand is a Valid straight, False of it is not A striaght type.
	 * 
	 */
	public boolean isValid() {
		// All straights should be made of 5 cards.
		if (this.size() != 5) {
			return false;
		}
		
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
				return false ;
			}
		}
		
		return true;
		
	}
	
	
	/**
	 * This method is implemented from the Hand_Interface
	 * 
	 * @return String Containing the type of hand.
	 * 
	 */
	public String getType() {
		return new String("Straight");
	}
}
