
/**
 * The Hand class is a subclass of the CardList class and is used to model a hand of cards.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 * 
 */
public class Hand extends CardList implements Hand_Interface{
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	// Instance Variable ( Private )
	// The player who plays THIS hand
	private CardGamePlayer player;	
	
	// Constructor:
	/**
	 * A constructor for building a hand with the specified player and list of cards.
	 * 
	 * @param player The specified CardGamePlayer
	 * @param cards The specified list of cards (CardList)
	 */
	public Hand(CardGamePlayer player, CardList cards) {
		this.player = player;
		//adding the cards to player's ArrayList of Cards
		for (int i = 0 ; i < cards.size(); i ++) {
			this.addCard(cards.getCard(i));
		}
	}
	

	
	// Public Methods:
	/**
	 * A method for retrieving the player of this hand
	 * 
	 * @return player who plays this hand
	 */
	public CardGamePlayer getPlayer() {
		return (this.player);
	}
	
	
	/**
	 * A method for retrieving the top card of this hand. This method is overridden in the subclasses.
	 * 
	 * @return null as this method is overridden in the subclasses
	 */
	public Card getTopCard() {
		return null;
	}
	
	/**
	 * A method for checking if THIS hand beats a specified hand
	 * 
	 * @param hand ( to be checked if it is beaten by THIS hand )
	 * @return boolean value 
	 */
	public boolean beats(Hand hand) {
		
		// if the size of hand on table is same as the size of played hand of the current player
		if(hand.size() == 1){
			if(this.size() == hand.size() && this.isValid() && this.getTopCard().compareTo(hand.getTopCard()) == 1 && this.getType() != null){
				return true;
			}
		}
		
		// if the size of hand on table is same as the size of played hand of the current player
		if(hand.size() == 2){
			if(this.size() == hand.size() && this.isValid() && this.getTopCard().compareTo(hand.getTopCard()) == 1 && this.getType() != null){
				return true;
			}
		}
		
		// if the size of hand on table is same as the size of played hand of the current player
		if(hand.size() == 3){
			if(this.size() == hand.size() && this.isValid() && this.getTopCard().compareTo(hand.getTopCard()) == 1 && this.getType() != null){
				return true;
			}
		}
		
		// No 4 card play is valid
		if (this.size() == 4) {
			return false;
		}
		
		// if the size of hand on table is same as the size of played hand of the current player
		if(hand.size() == 5 && this.size() == 5) {
			
			// Using this logic :
			// giving ranking order to the 5 card cases
			// straight < flush< full house < quad < straight flush
			
			// (non Javadoc)
			// hand_rank stores rank of parameter hand
			// 1 for straight
			// 2 for flush
			// 3 for full house
			// 4 for quad
			// 5 for straight flush
			int hand_rank = 0;
			
			if (hand instanceof Straight) {
				hand_rank = 1;
			}
			
			else if (hand instanceof Flush) {
				hand_rank = 2;
			}
			
			else if (hand instanceof FullHouse) {
				hand_rank = 3;
			}
			
			else if (hand instanceof Quad) {
				hand_rank = 4;
			}
			
			else if (hand instanceof StraightFlush) {
				hand_rank = 5;
			}
			
			// (non Javadoc)
			// this_rank stores rank of THIS hand
			// 1 for straight
			// 2 for flush
			// 3 for full house
			// 4 for quad
			// 5 for straight flush
			int this_rank = 0;
			
			if (this instanceof Straight) {
				this_rank = 1;
			}
			
			else if (this instanceof Flush) {
				this_rank = 2;
			}
			
			else if (this instanceof FullHouse) {
				this_rank = 3;
			}
			
			else if (this instanceof Quad) {
				this_rank = 4;
			}
			
			else if (this instanceof StraightFlush) {
				this_rank = 5;
			}

			// Checking conditon in which player's hand beats hand on table
			if (this_rank > hand_rank ) {
				return true;
			}
			
			else if (this_rank == hand_rank ) {
				if (this.getTopCard().compareTo(hand.getTopCard()) == 1) {
					return true;
				}
			}
			
		}
		

		return false;
	}
	
	// Other Methods from Interface
	
	/* (non Javadoc)
	 * ~from Hand_Interface  (line 14)
	 * 
	 */
	public boolean isValid() {
		return false; // to be overridden in subclasses
	}
	
	/*
	 * (non Javadoc)
	 * ~from Hand_Interface  (line 21)
	 * 
	 */
	public String getType() {
		return null; // to be overridden in subclasses
	}
}
