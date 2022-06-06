
/**
 * The Pair Class is a subclass of the Hand Class and implements Hand_Interface.
 * It implements isValid() and getType() and overrides getTopCard() method from the Hand Class.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class Pair extends Hand implements Hand_Interface {
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for Pair Type.
	 * 
	 * @param player The specified CardGamePlayer object
	 * @param cards The specified list of cards (CardList object)
	 */
	public Pair(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}

	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return Card object which is the Top Card of the given Hand
	 */
	public Card getTopCard() {
		if (this.getCard(0).getSuit() > this.getCard(0).getSuit()) {
			return this.getCard(0);
		}
		return this.getCard(1);
	}
	
	/**
	 * This method is implemented from the Hand_Interface
	 * 
	 * @return True is the given hand is a Valid Pair, False of it is not A pair type.
	 * 
	 */
	public boolean isValid() {
		if(this.size() != 2) {
			return false;
		}
		if(this.getCard(0).getRank() != this.getCard(1).getRank()) {
			return false;
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
		return new String("Pair");
	}
	
}

