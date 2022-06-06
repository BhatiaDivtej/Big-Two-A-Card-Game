/**
 * The Quad Class is a subclass of the Hand Class and implements Hand_Interface.
 * It implements isValid() and getType() and overrides getTopCard() method from the Hand Class.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class Quad extends Hand implements Hand_Interface{
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for Quad Type.
	 * 
	 * @param player The specified CardGamePlayer object
	 * @param cards The specified list of cards (CardList object)
	 */
	public Quad(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return Card object which is the Top Card of the given Hand
	 */
	public Card getTopCard() {
		// Sort the CardList
		this.sort();
		
		// Returning Highest Rank Card
		if(this.getCard(0).rank == this.getCard(1).rank){
			return this.getCard(3);
		}
		else{
			return this.getCard(4);
		}
	}

	/**
	 * This method is implemented from the Hand_Interface
	 * 
	 * @return True is the given hand is a Valid Quad, False of it is not A striaght type.
	 * 
	 */
	public boolean isValid() {
		// All Quads should be made of 5 cards.
		if (this.size() != 5) {
			return false;
		}
		
		this.sort();
		
		//checking quad
		if (this.getCard(0).getRank() == this.getCard(3).getRank()) {
			if(this.getCard(0).getRank()==this.getCard(1).getRank() && this.getCard(0).getRank()==this.getCard(2).getRank()) {
				return true;
			}
		}
		
		if (this.getCard(1).getRank()==this.getCard(4).getRank()) {
			if(this.getCard(1).getRank()==this.getCard(2).getRank() && this.getCard(1).getRank()==this.getCard(3).getRank()){
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
		return new String("Quad");
	}
}
