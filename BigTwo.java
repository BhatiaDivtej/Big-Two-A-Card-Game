import java.util.*;

/**
 * The BigTwo class implements the CardGame interface and is used to model a Big Two card game.
 * It has private instance variables for storing the number of players ,a deck of cards, a list of players, a list of hands played on the table, 
 * an index of the current player, and a user interface.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class BigTwo {
	// CONSTRUCTOR
	
	/**
	 * A constructor for creating a Big Two card game.
	 * Creates 4 Players and adds to playerlist;
	 * Creates BigTwoUI object for user interface
	 * 
	 */
	public BigTwo(){
		//Creating 4 Players and adding to playerlist
		playerList = new ArrayList<CardGamePlayer> ();
		for (int i = 0 ; i < 4; i ++) {
			playerList.add(new CardGamePlayer()); // creating CardGamePlayer and adding to the arraylist
		}
		handsOnTable = new ArrayList<Hand>();
		// Creating BigTwoUI object using constructor
		ui = new BigTwoUI(this);
	
	}

	// INSTANCE VARIABLES
	
	// Instance Variables Added By Me :
	// 1. An added instance variable to store value of player who played the previous hand
	private int previous_player = -1;
	
	// 2. An added instance variableStores player who has made the last illegal move
	private int illegalmove_player_index = 100;
	
	
	// Instance Variable Requirements
	// An int specifying the number of players.
	private int numOfPlayers;
	
	// A deck of cards
	private Deck deck;
	
	// A list of players
	private ArrayList<CardGamePlayer>playerList ;
	
	// A list of hands played on the table
	private ArrayList<Hand>handsOnTable;
	
	// An integer specifying the index of the current player
	private int currentPlayerIdx;
	
	//A BigTwoUI object for providing the user interface
	private BigTwoUI ui;
	
	// PUBLIC METHODS
	/**
	 * A method which returns number of players in the big two game ( getter method for numOfPlayers).
	 * 
	 * @return integer specifying number of players in the game
	 */
	public int getNumOfPlayers() {
		return (this.numOfPlayers);
	}
	
	/**
	 * A method which returns deck in the big two game ( getter method for Deck).
	 * 
	 * @return Deck object containing deck of this big two game instance
	 */
	public Deck getDeck() {
		return (this.deck);
		
	}
	
	/**
	 * A method which returns PlayerList in the big two game ( getter method for playerList).
	 * 
	 * @return Arraylist of CardGamePlayers
	 */
	public ArrayList<CardGamePlayer> getPlayerList(){
		return (this.playerList);
	}
	
	/**
	 * Getter Method for handsOnTable
	 * 
	 * @return ArrayList containing hand on table in the big two game
	 */
	public ArrayList<Hand> getHandsOnTable(){
		return (this.handsOnTable);
	}
	
	/**
	 * Getter method for CurrentPlayerIdx
	 * 
	 * @return int Indexf Of current player
	 */
	public int getCurrentPlayerIdx() {
		return (this.currentPlayerIdx);
	}
	
	/**
	 * A method for starting/restarting the game with a given shuffled deck of cards.
	 * 
	 * @param deck represents deck containing BigTwoCards
	 */
	public void start(Deck deck) {
		// Reset current player index, take the shuffled Deck
		int x = 0;

		// Adding cards to the Hand
		for(int i = 0; i < 4; i++)  {
			for(int j = 0; j < 13; j++){
				playerList.get(i).addCard(deck.getCard(x+i+j));
			}
			
			x = x + 12;
		}
	
		// Sorting Player Cards in Hand
		for(int i = 0; i < 4;i++){
			playerList.get(i).getCardsInHand().sort();
		}
		
		// Gettind Player with 3 of Diamonds and Settin
		for(int i = 0; i < 4;i++){
			if(playerList.get(i).getCardsInHand().contains(new Card(0,2))){
				ui.setActivePlayer(i); //setting index of active player
				currentPlayerIdx = i ;
			}
		}
		
		// While loop which asks for input and prints cards till game ends
		while(endOfGame() != true) { // endOfGame is true only when one of the player has an empty list of cards
			if (illegalmove_player_index != currentPlayerIdx) { // We do not need to repaint if the player has made an illegal move
				ui.repaint();
			}
			
			// asking active player for input
			ui.promptActivePlayer(); //asking for input
			
		}
	}
	
	/**
	 * A method for making a move by a player with the specified index using the cards specified by the list of indices.
	 * 
	 * @param playerIdx represents index of currentplayer
	 * @param cardIdx represents arraylist containg card indices of player
	 */
	public void makeMove(int playerIdx, int[] cardIdx) {
		checkMove(playerIdx,cardIdx); // calls the checkmove method
	}
	
	/**
	 * A method for checking a move made by a player. This method should be called from the makeMove() method. 
	 * 
	 * @param playerIdx
	 * @param cardIdx
	 */
	public void checkMove(int playerIdx, int[] cardIdx) {
		
		CardList cardlist = new CardList();
		
		// Introduce a local bool variable to store if the move is valid or not/
		// returns true only if the move is a valid and legal move.
		boolean check_is_valid = false; 

		if (cardIdx != null) { // If the players' move is non empty
			
			cardlist = playerList.get(playerIdx).play(cardIdx); // making a cardlist of the arraylist CardIdx ( using play method of CardGamePlayer)
			Hand hand = composeHand(playerList.get(playerIdx), cardlist); // making a hand object of the players move

			
			// Checking if the first hand of game includes a 3 of diamonds
			if(handsOnTable.isEmpty()){ // Representing that game has just started
				
				if(hand.contains(new Card(0,2)) && !hand.isEmpty()) {
					if (hand.isValid() && hand.getType() != null ){
						check_is_valid = true; // assigning check_is_valid true if the first move 3 of diamonds and hand is not empty 
					}
					else {
						check_is_valid = false;
					}
				}
				
				else{
					check_is_valid = false;
					
				}
			}
			
			// For Non-first move instances
			else {
				// If there is a hand on the table and current player is not who played the last hand on table
				if(previous_player != playerIdx && hand.getType() != null) {
					check_is_valid = hand.beats(handsOnTable.get(handsOnTable.size() - 1));

				}
				
				else {
					if (hand.getType() != null) { 
						check_is_valid = true;
					}
					
				}
			}
			
			
			//	If the move is a legal move:
			if(check_is_valid){
				previous_player = playerIdx ;
				for(int i = 0; i < cardlist.size();i++){
				
					playerList.get(playerIdx).getCardsInHand().removeCard(cardlist.getCard(i)); // removes the card upon the player playing a successful move
				
				}
				
				System.out.println("{" + hand.getType() + "}" + " " + hand);
				handsOnTable.add(hand);
				playerIdx = (playerIdx+1)%4;
				ui.setActivePlayer(playerIdx); // setting the next active player
				System.out.println("");
			}
			
			// If the player is an Illegal Move:
			else{
				
				illegalmove_player_index = playerIdx;
				
				System.out.println("Not a legal move!"); // printing in case the current active player didn't play a valid move
			}
			
		}
		
		// If player presses ENTER to pass
		else{
			
			if(!handsOnTable.isEmpty() && previous_player != playerIdx){ // condition if the current player tries to pass
			
				playerIdx = (playerIdx+1)%4;
				ui.setActivePlayer(playerIdx);
				System.out.println("{Pass}");  
				System.out.println("");  
				check_is_valid = true;
				illegalmove_player_index = 8; // reset illegal_player_indexx
			}
			
			else{
				
				illegalmove_player_index = playerIdx; 
				System.out.println("Not a legal move");
			} 
			
		}
		
		if(endOfGame() == true){ // if the game ends(a player to run out of cards)
		
			ui.setActivePlayer(-1); // Instance when game ends
			System.out.println("Game ends");
			
			// Printing as per specifications
			for(int i = 0; i < playerList.size();i++){
			
				if(playerList.get(i).getCardsInHand().size() == 0){
				
					System.out.println("Player " + i + " wins the game"); // declare that player as winner
				}
				
				else{
				
					System.out.println("Player " + i + " has " + playerList.get(i).getCardsInHand().size() + " cards in hand"); // list the number of cards left in the other players' hand
				}
			}
		}
		
		currentPlayerIdx = playerIdx; // Set currentPlayerIdx as playerIdx
		
	}
	
	/**
	 * 
	 * 
	 * @return Boolean value. True if the game has ended (i.e. A player has run out of cards)
	 */
	public boolean endOfGame() {
		// if the hand of the current player is empty then it means that he won the game
		
		for (int i = 0 ; i < 4; i++) {
			if(playerList.get(i).getCardsInHand().isEmpty()) {
				return true;
			}	
		}
		return false;
		
	}
	
	
	
	// PUBLIC STATIC METHODS
	
	/**
	 * The main method of the game.
	 * a method for starting a Big Two card game.It should 
	 * (i) create a Big Two card game,
	 * (ii) create and shuffle a deck of cards, and 
	 * (iii) start the game with the deck of cards.
	 * 
	 * @param args not being used here
	 */
	public static void main(String[] args) {
		// create instance of the BigTwo Game
		BigTwo Game = new BigTwo(); 
		
		// create instance of BigTwo Deck
		Deck Game_Deck = new BigTwoDeck(); // this constructor automatically initializes the deck

		// shuffle cards
		Game_Deck.shuffle(); 
		
		// start game
		Game.start(Game_Deck); 
		
	}
	
	/**
	 * A method for returning a valid hand from the specified list of cards of the player. 
	 * Returns null if no valid hand can be composed from the specified list of cards.
	 * 
	 * @param player, is the CardGamePlayer object
	 * @param cards, arraylist containg cards of the CardGamePlayer'move
	 * @return hand from the specified list of cards of the player. Returns hand of type null when no valid hand can be formed.
	 */
	public static Hand composeHand(CardGamePlayer player, CardList cards) {
		Single single = new Single(player,cards); // new single object
		if(single.isValid()){
			return single; // returns a single object 
		}
		
		Pair pair = new Pair(player,cards); // new pair object
		if(pair.isValid()){
			return pair; // returns a pair object 
		}
				
		Triple triple = new Triple(player,cards); // triple object 
		if(triple.isValid()){
			return triple; // returns a triple object 
		}		
		
		Straight straight = new Straight(player,cards); // a new straight object 
		if(straight.isValid()){	
			return straight; // returns a straight object 
		}	
		
		Flush flush = new Flush(player,cards); // new flush object 
		if(flush.isValid()){
			return flush; // returns a flush object
		}		
		
		FullHouse fullhouse = new FullHouse(player,cards); // a new fullhouse object 
		if(fullhouse.isValid()){
			return fullhouse; // returns a fullhouse object 
		}
		
		Quad quad = new Quad(player,cards); // new quad object
		if(quad.isValid()){
			return quad; // returns a quad object 
		}
		
		StraightFlush straightflush = new StraightFlush(player,cards); // straightflush object
		if(straightflush.isValid()){
			return straightflush; // returns a straightflush object 
		}
		
		return new Hand(player,cards); // returns hand of type null when called 
	}
	
	
}
