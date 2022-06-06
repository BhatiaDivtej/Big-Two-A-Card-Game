
/**
 * The FullHouse Class is a subclass of the Hand Class and implements Hand_Interface.
 * It implements isValid() and getType() and overrides getTopCard() method from the Hand Class.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class FullHouse extends Hand implements Hand_Interface{
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for Straight Type.
	 * 
	 * @param player The specified CardGamePlayer object
	 * @param cards The specified list of cards (CardList object)
	 */
	public FullHouse(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return Card object which is the Top Card of the given Hand
	 */
	public Card getTopCard() {
		// Sorting the Cardlist
		this.sort();
		
		if(this.getCard(0).rank == this.getCard(2).rank) // comparing ranks
		{
			return this.getCard(2);
		}
		
		else
		{
			return this.getCard(4);
		}
		
}
	
	
	/**
	 * This method is implemented from the Hand_Interface
	 * 
	 * @return True is the given hand is a Valid FullHouse, False of it is not A FullHouse type.
	 * 
	 */
	public boolean isValid() {
		// All straights should be made of 5 cards.
		if(this.size() != 5)
		{
			return false;
		}
		
		this.sort(); // sorting
		
		if(this.getCard(0).rank == this.getCard(2).rank)
		{
			if(this.getCard(0).rank == this.getCard(1).rank && this.getCard(0).rank == this.getCard(2).rank && this.getCard(3).rank == this.getCard(4).rank)
			{
				return true; // condition for isValid is satisfied 
			}
		}
		
		else if(this.getCard(2).rank == this.getCard(4).rank)
		{
			if(this.getCard(2).rank == this.getCard(3).rank && this.getCard(2).rank == this.getCard(4).rank && this.getCard(0).rank == this.getCard(1).rank)
			{
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
		return new String("FullHouse");
	}
}
