import java.util.*;

// NOTE : Some Changes Have been made from the version of BigTwo submitted in Assignment 3
// Some parts have been changed keeping in mind the Object Oriented Approach

/**
 * The BigTwo class implements the CardGame interface and is used to model a Big Two card game.
 * It has private instance variables for storing the number of players ,a deck of cards, a list of players, a list of hands played on the table, 
 * an index of the current player, and a user interface.
 * 
 * @author BHATIA Divtej Singh ( UID : 3035832438 )
 *
 */
public class BigTwo implements CardGame {
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
		for(int i = 0; i < 4;i++){
			CardGamePlayer player = new CardGamePlayer();
			playerList.add(player);
		}
		handsOnTable = new ArrayList<Hand>();
		// Creating BigTwoUI object using constructor
		BigTwoGUI = new BigTwoGUI(this);
		currentIdx = -1;
	}
	
	// INSTANCE VARIABLES
	
	// A deck of cards
	private Deck deck;
	
	// A list of players
	private ArrayList<CardGamePlayer> playerList;  
	
	// A list of hands played on the table
	private ArrayList<Hand> handsOnTable; 
	
	// An integer specifying the index of the current player
	private int currentIdx; 
	
	// An int specifying the number of players.
	private int numOfPlayers;
	
	//A BigTwoUI object for providing the user interface
	private BigTwoGUI BigTwoGUI; 
	
	
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
	 * Getter method for CurrentPlayerIdx
	 * 
	 * @return int Indexf Of current player
	 */
	public int getCurrentPlayerIdx() {
		return (this.currentIdx);
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
	 * A getter method which retrieves the index of the active player.
	 * 
	 * @return an Int type which could either be 0, 1, 2 or 3. 
	 */
	public int getCurrentIdx()
	{
		return currentIdx;	
	}
	
	/**
	 * A method for starting/restarting the game with a given shuffled deck of cards.
	 * 
	 * @param deck represents deck containing BigTwoCards
	 */
	public void start(Deck Deck){
		// Reset current player index, take the shuffled Deck
		
		// removing all cards from the table
		handsOnTable.clear();
		
		//removing cards from players hands
		for(int i=0;i<4;i++) {
			playerList.get(i).getCardsInHand().removeAllCards();
		}
		
		// giving cards to players
		int x = 0;
		for(int i = 0; i <52; i++)   
		{
			playerList.get(x%4).addCard(Deck.getCard(i));
			x++;
		}
		
		// Sorting Player Cards in Hand
		for(int i = 0; i < 4;i++){
			playerList.get(i).getCardsInHand().sort();
		}
		
		// Gettind Player with 3 of Diamonds
		Card ThreeOfDiamonds = new Card(0,2);
		
		for(int i = 0; i < 4;i++) 
		{
			if(playerList.get(i).getCardsInHand().contains(ThreeOfDiamonds))
			{
				currentIdx= i;
				BigTwoGUI.setActivePlayer(currentIdx);
				break;
			}
		}
		BigTwoGUI.printMsg(getPlayerList().get(currentIdx).getName() + "'s turn");
		BigTwoGUI.repaint();
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
	public void checkMove(int playerID, int [] cardIdx){
		CardList cardlist = new CardList(); 
		boolean isValid = true; 
		Card ThreeOfDiamonds = new Card(0,2);
		
		if(cardIdx!= null) {
			cardlist = playerList.get(playerID).play(cardIdx);
			Hand hand = composeHand(playerList.get(playerID), cardlist);
			
			if(handsOnTable.isEmpty())
			{
				//checks if beginning move contains 3 of diamonds
				if(hand.contains(ThreeOfDiamonds) && hand.isEmpty()==false && hand.isValid()==true)
					isValid = true; 
				else
					isValid = false;
			}
			else {
				if(handsOnTable.get(handsOnTable.size() - 1).getPlayer() != playerList.get(playerID))
				{
					if(!hand.isEmpty()) {
						isValid = hand.beats(handsOnTable.get(handsOnTable.size() - 1));
					}
					else {
						isValid = false;
					}
				}
				else {
					if(!hand.isEmpty()) {
						isValid = true;
					}
					else {
						isValid= false;
					}
				}	
			}
			if(isValid && hand.isValid()) {
				
				// Removing all played cards from the players hands
				for(int i=0;i<cardlist.size();i++)
				{
					playerList.get(playerID).getCardsInHand().removeCard(cardlist.getCard(i)); 
				}
				
				BigTwoGUI.printMsg("{" + hand.getType() + "} " + hand);
				handsOnTable.add(hand);
				currentIdx = (currentIdx + 1) % 4;
				BigTwoGUI.setActivePlayer(currentIdx);
				BigTwoGUI.printMsg("Player " + (currentIdx) + "'s turn");
			}
			else
			{
				BigTwoGUI.printMsg(cardlist +" <= Not a legal move!!!");
			}
		}
		else {
			
			// Passing by the player
			if(!handsOnTable.isEmpty() && handsOnTable.get(handsOnTable.size()-1).getPlayer() != playerList.get(playerID)) {
				currentIdx = (currentIdx+1)%4;
				BigTwoGUI.setActivePlayer(currentIdx);
				
				BigTwoGUI.printMsg("{Pass}");
				BigTwoGUI.printMsg("Player " + currentIdx + "'s turn");
				
				isValid = true;
			}
			else {
				BigTwoGUI.printMsg("Not a legal move!!!");
				isValid= false;
			}
		}
		
		BigTwoGUI.repaint();
		
		// checking for end of game
		if(endOfGame()) {
			
			BigTwoGUI.setActivePlayer(-1);
			BigTwoGUI.repaint();
			BigTwoGUI.printMsg("Game ends");
			
			//check who wins and how many cards the other players have
			for(int i = 0; i < playerList.size();i++)
			{
					if(playerList.get(i).getCardsInHand().size() == 0)
					{
						BigTwoGUI.printMsg("Player " + i + " wins the game"); 
					}
				
				else
				{
					BigTwoGUI.printMsg("Player " + i + " has " + playerList.get(i).getCardsInHand().size() + " cards in hand"); // list the number of cards left in the other players' hand
				}
			}
			BigTwoGUI.disable(); //from the GUI
		}
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
	public static void main(String [] args)
	{
		BigTwo game = new BigTwo();
		BigTwoDeck deck = new BigTwoDeck();
		deck.shuffle(); 
		game.start(deck);
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