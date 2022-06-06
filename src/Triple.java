
/**
 * The Triple Class is a subclass of the Hand Class and implements Hand_Interface.
 * It implements isValid() and getType() and overrides getTopCard() method from the Hand Class.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class Triple extends Hand implements Hand_Interface {
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for Single Type.
	 * 
	 * @param player The specified CardGamePlayer object
	 * @param cards The specified list of cards (CardList object)
	 */
	public Triple(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}

	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return Card object which is the Top Card of the given Hand
	 */
	public Card getTopCard(){
		if (this.getCard(0).getSuit() > this.getCard(1).getSuit()) {
			if (this.getCard(0).getSuit() > this.getCard(2).getSuit()){
				return this.getCard(0);
			}
			else {
				return this.getCard(2);
			}
		}
		
		else {
			if (this.getCard(1).getSuit() > this.getCard(2).getSuit()) {
				return this.getCard(1);
						
			}
			else {
				return this.getCard(2);
			}
		}
		
	}
		
	
	/**
	 * This method is implemented from the Hand_Interface
	 * 
	 * @return True is the given hand is a Valid Triple, False of it is not A triple type.
	 */
	public boolean isValid() {
		if(this.size() !=3 ) {
			return false;
		}
		
		if (this.getCard(0).getRank() == this.getCard(1).getRank()){
			if (this.getCard(0).getRank() == this.getCard(2).getRank()){
				return true;
			}
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
		return new String("Triple");
	}
	
}

