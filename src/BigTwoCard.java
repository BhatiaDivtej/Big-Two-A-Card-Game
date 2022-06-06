
/**
 * The BigTwoCard class is a subclass of the Card class
 * It is used to model a card used in a Big Two card game.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class BigTwoCard extends Card  {
	// Default Serial Version ID
	private static final long serialVersionUID = 1L;

	// CONSTRUCTOR
	/**
	 * Constructor for building a card with the specified suit and rank. 
	 * 
	 * @param suit is an integer between 0 and 3
	 * @param rank is an integer between 0 and 12
	 */
	public BigTwoCard(int suit,int rank){
		//Calling superclass constructor
		super(suit,rank);
	}
	
	// OVERRIDING METHOS
	/**
	 * A method for comparing the order of this card with the specified card. 
	 * Returns a negative integer, zero, or a positive integer when this card is less than, equal to, or greater than the specified card.
	 * 
	 * @param card is the specified card to be compared with.
	 */
	public int compareTo(Card card) {
		//Compare rank as well as suit. Let rank2 and suit2 be the specified card to be compared to 
		int rank1 = this.rank;
		int rank2 = card.rank;
		
		//Case of ACE
		if (rank1 == 0) {
			rank1 = 13; //giving ACE's rank higher than King
		}
		
		if (rank2 == 0) {
			rank2 = 13; //giving ACE's rank higher than King
		}
		
		//Case of 2
		if (rank1 == 1) {
			rank1 = 14; //giving highest rank (=14) to 2
		}
		if (rank2 == 1) {
			rank2 = 14; //giving highest rank (=14) to 2
		}
		
		//Comparing 
		if (rank1 > rank2) {
			return 1;
		} 
		else if (rank1 < rank2) {
			return -1;
		}
		else if (this.suit > card.suit) {
			return 1;
		} 
		else if (this.suit < card.suit) {
			return -1;
		}
		else {
			return 0;
		}
		
	
	}

}