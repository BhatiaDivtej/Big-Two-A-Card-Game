import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

/**
 * The BigTwoGUI class implements the CardGameUI interface.  It is used to build a GUI for the Big Two card game and handle all user actions.
 * 
 * @author BHATIA Divtej Singh (UID : 3035832438)
 */
public class BigTwoGUI implements CardGameUI{
	
	// CONSTRUCTOR
	/**
	 * This is the constructor for BigTwoGUI.
	 * 
	 * @param game A reference to a Big Two card game associates with this GUI.
	 */
	public BigTwoGUI(BigTwo game) {
		this.game = game;
		selected = new boolean[13];
		setActivePlayer(game.getCurrentPlayerIdx()); 
		loadImages();
		SetupGUI();	
	}
	
	// PRIVATE INSTANCE VARIABLES

	private BigTwo game; // a Big Two card game associates with this GUI.
	
	private boolean[] selected; // a boolean array indicating which cards are being selected.

	private int activePlayer; // an integer specifying the index of the active player.
	
	private JFrame frame; // the main window of the application.

	private JPanel bigTwoPanel; // a panel for showing the cards of each player and the cards played on the table.
	
	private JButton playButton; // a “Play” button for the active player to play the selected cards.

	private JButton passButton; // a “Pass” button for the active player to pass his/her turn to the next player.
	
	private JTextArea msgArea; // a text area for showing the current game status as well as end of game messages.
	
	private JTextArea chatArea; // a text area for showing chat messages sent by the players.
	
	private JTextField chatInput ; // a text field for players to input chat messages.

	// Added Instance Varables (Other than requiremnts)
	
	private Image[][] cardImages; // Array storing the images for the faces of the cards.
	
	private Image cardBackImage; // Back of Cards/ Upside Down Cards
	
	private Image[] avatars; // Array containing player images
	
	// CARDGAME UI INTERFACE METHODS:
	
	/**
	 *  A method for setting the index of the active player
	 * 
	 * @param activePlayer integer index of the active player [0-3]
	 */
	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}
	
	/**
	 * A method for repainting the GUI
	 */
	public void repaint() {
		resetSelected();
		frame.repaint();
	}
	
	/**
	 * A method that prints the specified string to the message area of the GUI
	 * 
	 * @param msg string to be printed in the GUI message area
	 */
	public void printMsg(String msg) {
		msgArea.append(msg+"\n");
	}
	
	/**
	 * A method that prints the specified string to the chat area of the GUI
	 * 
	 * @param msg string to be printed in the GUI chat area
	 */
	public void printChat(String msg) {
		chatArea.append(msg+"\n");
	}
	
	/**
	 * A method for clearing the message area of the GUI.
	 * 
	 */
	public void clearMsgArea() {
		msgArea.setText(null);
	}
	
	/**
	 * A method for clearing the chat area of the GUI.
	 * 
	 */
	public void clearChatArea() {
		chatArea.setText(null);
	}
	
	/**
	 * A method that resets the GUI. It handles the following functions:
	 * (i) reset the list of selected cards; 
	 * (ii) clear the message area; and 
	 * (iii) enable user interactions.
	 * 
	 */
	public void reset() {
		this.resetSelected();
		this.clearMsgArea();
		this.clearChatArea();
		this.enable();
		
	}
	
	/**
	 * A method for enabling user interactions with the GUI. Handles the following functions
	 * (i) enable the “Play” button and “Pass” button (i.e., making them clickable); 
	 * (ii) enable the chat input; and 
	 * (iii) enable the BigTwoPanel for selection of cards through mouse clicks.
	 * 
	 */
	public void enable() {
		playButton.setEnabled(true);
		passButton.setEnabled(true);
		bigTwoPanel.setEnabled(true);
	}
	
	/**
	 * A method for disabling user interactions with the GUI. Handles the following functions
	 * (i) disable the “Play” button and “Pass” button (i.e., making them not clickable); 
	 * (ii) disable the chat input; and 
	 * (ii) disable the BigTwoPanel for selection of cards through mouse clicks.
	 */
	public void disable() {
		playButton.setEnabled(false);
		passButton.setEnabled(false);
		bigTwoPanel.setEnabled(false);
	}
	
	/**
	 * A method for prompting the active player to select cards and make his/her move.
	 * 
	 */
	public void promptActivePlayer() {
		// Functioning of this method have been handled while handling game logic
	}

	
	// This is a private method used to insert all images in the GUI
	private void loadImages() {
		// an array of images to store player images
		avatars = new Image[4];
		// storing player avatar images as per their path
		avatars[0] = new ImageIcon("images/playerPics/mo.png").getImage();
		avatars[1] = new ImageIcon("images/playerPics/xi.png").getImage();
		avatars[2] = new ImageIcon("images/playerPics/tr.png").getImage();
		avatars[3] = new ImageIcon("images/playerPics/ra.png").getImage();
		
		cardBackImage = new ImageIcon ("images/cards/back_side.gif").getImage();
		
		char[] suit = {'d','c','h','s'};
		
		char[] rank = {'a', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k'};
		
		cardImages = new Image[4][13];
		// getting and storing card images 
		for(int j=0;j<13;j++) {
			for(int i=0;i<4;i++) {
				String location = new String();
				location="images/cards/" + rank[j] + suit[i] + ".gif";
				cardImages[i][j] = new ImageIcon(location).getImage();
			}
		}
	}
	
	
	// This Private Method Sets up the GUI
	private void SetupGUI() {
		// initialize frame
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Big Two");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set menu and adding items to it
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Game"); 
		menuBar.add(menu);
		
		// Adding Menu Items
		JMenuItem restart = new JMenuItem("Restart");
		menu.add(restart);
		JMenuItem quit = new JMenuItem("Quit");
		menu.add(quit);
		restart.addActionListener(new RestartMenuItemListener());
		quit.addActionListener(new QuitMenuItemListener()); // implemented in a seperate class
		
		// Message Menu
		JMenu messageMenu = new JMenu("Message"); // implemented in a seperate class
		menuBar.add(messageMenu); 	
		
		// Adding an option to clear ChatBox in the top bar
		JMenuItem clearChat = new JMenuItem("Clear Chat") ;
		messageMenu.add(clearChat);
		
		clearChat.addActionListener(new clearChatActionListener());
		
		//Buttons Panel
		JPanel BottomPanel = new JPanel();
		
		//Buttons inside panel
		playButton = new JButton("Play");
		playButton.addActionListener(new PlayButtonListener());
		passButton = new JButton("Pass");
		passButton.addActionListener(new PassButtonListener());
		
		//Adding buttons to panel
		BottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		BottomPanel.add(Box.createHorizontalStrut(320));
		
		BottomPanel.add(playButton);
		BottomPanel.add(Box.createHorizontalStrut(20));
		BottomPanel.add(passButton);
		
		BottomPanel.add(Box.createHorizontalStrut(380));
		
		JLabel messagetext = new JLabel("Message:");
		BottomPanel.add(messagetext);
		
		BottomPanel.add(Box.createHorizontalStrut(5));
		
		//Bottom message bar
		chatInput = new JTextField(37);
		BottomPanel.add(chatInput);
		
		//chatInput is the text box which takes input
		 chatInput.addActionListener(new ActionListener() { // implemented in a class
			 public void actionPerformed(ActionEvent e) {
				 String input = chatInput.getText(); 
				 String chatmsg = "Player " + game.getCurrentPlayerIdx() + ": " + input ; 
				 printChat(chatmsg) ;
				 chatInput.setText("");
			 }
		 });
	
		//Playing area
		bigTwoPanel = new BigTwoPanel();
		bigTwoPanel.setLayout(new BoxLayout(bigTwoPanel, BoxLayout.Y_AXIS));
		
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		Font font = new Font("TimesRoman", Font.PLAIN, 15);
		msgArea.setFont(font);
		
		chatArea = new JTextArea();
		chatArea.setEditable(false); 
		chatArea.setFont(font); 
		chatArea.setForeground(Color.BLUE);
		
		JScrollPane scroll = new JScrollPane (msgArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(550, 0));
		DefaultCaret caret = (DefaultCaret)msgArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 

		JScrollPane scroller = new JScrollPane (chatArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension(550, 0));
		DefaultCaret careter = (DefaultCaret)chatArea.getCaret();
		careter.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 

		//Right panel implementation for chatbox and messagebox
		JPanel rightpanel = new JPanel();
		frame.add(BorderLayout.EAST,rightpanel) ;
		
		rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.Y_AXIS));
		
		rightpanel.add(BorderLayout.NORTH, scroll);
		rightpanel.add(BorderLayout.SOUTH , scroller); //THIS
		
		frame.add(BorderLayout.CENTER, bigTwoPanel);
		frame.add(BorderLayout.SOUTH, BottomPanel);
		frame.setSize(1500,900);  
	    frame.setVisible(true); 
	}
	

	
	/**
	 * Returns an array of indices of the cards selected.
	 * 
	 * @return Array of the indices of cards selected by active player.
	 */
	public int[] getSelected() {
		
		int x = 0;
		for(int i=0;i<selected.length;i++)
		{
			if(selected[i]==true) {
				x++;
			}
		}
		int[] result;
		if(x==0) {
			return null;
		}
		result = new int[x];
		int x2 = 0;
		for(int i=0;i<selected.length;i++) {
			if(selected[i]==true) {
				result[x2] = i;
				x2++;
			}
		}
		return result;	
	}
	
	/**
	 * Clears the selected list of cards
	 */
	public void resetSelected() {
		for(int i=0;i<selected.length;i++) {
			selected[i] = false;
		}
	}
	


	
	/**
	 * An inner class that extends the JPanel class and implements the MouseListener interface.
	 * Overrides the paintComponent() method inherited from the JPanel class to draw the card game table.
	 * 
	 * @author BHATIA Divtej Singh ( UID : 3035832438)
	 */
	class BigTwoPanel extends JPanel implements MouseListener{		
		private static final long serialVersionUID = 1L;

		// Method to get the suit of a particular card
		private int getSuitofPlayer(int Player, int Suit) {
			return game.getPlayerList().get(Player).getCardsInHand().getCard(Suit).getSuit();
		}
		
		//  Method to get the rank of a particular card
		private int getRankofPlayer(int Player, int Rank) {
			return game.getPlayerList().get(Player).getCardsInHand().getCard(Rank).getRank();
		}
		
		/**
		 * BigTwoPanel default constructor which adds the Mouse Listener to the GUI
		 * 
		 */
		public BigTwoPanel() {
			this.addMouseListener(this);
		}
		
		// Assigning positions to variables		
		private int nameXCoord = 40; // x coordinate of player name
		
		private int nameYCoord = 20; // y coordinate of player name
		
		private int avatarXCoord = 5; // x coordinate of player avatar
		
		private int avatarYCoord = 30; // y coordinate of player avatar
		
		private int lineXCoord = 160; // x coordinate of seperating line
		
		private int lineYCoord = 1600; // y coordinate of seperating line
		
		private int downCardYCoord = 43; // y coordinate of bottom card
		
		private int upCardYCoord = 23; // y coordinate of top card
		
		private int cardXCoord = 155; // x coordinate of player card
		
		private int cardWidth = 40; // width of card
		
		private int cardIncrement = 160;
		
		/**
		 * Draws the avatars, text and cards on card table.
		 * 
		 * @param g default parameter
		 */
		public void paintComponent(Graphics g){
			// Implmenting colouring of the Components
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			this.setBackground(Color.GREEN.darker().darker());
			g.setColor(Color.WHITE);
			int playerCounter = 0;
			
			//painting the four players avatars and their cards
			while(playerCounter<4) {
				if(activePlayer==playerCounter) {
					g.setColor(Color.GREEN);
					g.drawString("   YOU", nameXCoord , nameYCoord + 160*playerCounter); 
				}
				else {
					g.drawString("Player "+playerCounter, nameXCoord , nameYCoord + 160*playerCounter); 
				}
				g.setColor(Color.WHITE);
				g.drawImage(avatars[playerCounter], avatarXCoord, avatarYCoord + 160*playerCounter, this);
			    g2.drawLine(0, lineXCoord*(playerCounter+1), lineYCoord, lineXCoord*(playerCounter+1));

			    // Cards only shown if the player is active
			    if (activePlayer == playerCounter) {
			    	for (int i = 0; i < game.getPlayerList().get(playerCounter).getNumOfCards(); i++) {
			    		int suit = getSuitofPlayer(playerCounter, i);
			    		int rank = getRankofPlayer(playerCounter, i);
			    		
			    		// Raising the card id selected
			    		if (selected[i]){
			    			g.drawImage(cardImages[suit][rank], cardXCoord+cardWidth*i, upCardYCoord+cardIncrement*playerCounter, this);
			    			
			    		}else {
			    			g.drawImage(cardImages[suit][rank], cardXCoord+cardWidth*i, downCardYCoord+cardIncrement*playerCounter, this);
			    		}		
			    	}
			    } 
			    // Cards not shown if player is not active
			    else
			    {
			    	for (int i = 0; i < game.getPlayerList().get(playerCounter).getCardsInHand().size(); i++)
			    	{
			    		g.drawImage(cardBackImage, cardXCoord+cardWidth*i, downCardYCoord+cardIncrement*playerCounter, this);
			    	}
			    }
				playerCounter++;
			}
		    
			// Previous Hand on Table
		    g.drawString("Last Hand on Table", 10, 660);

		    if (!game.getHandsOnTable().isEmpty()){
		    	int sizeofHands = game.getHandsOnTable().size();
		    	Hand handOnTable = game.getHandsOnTable().get(sizeofHands - 1);
		    	g.drawString("Hand Type:\n " + game.getHandsOnTable().get(sizeofHands - 1).getType(), 10, 700);
		    	for (int i = 0; i < handOnTable.size(); i++){
		    		int suit = handOnTable.getCard(i).getSuit();
		    		int rank = handOnTable.getCard(i).getRank();
	    			g.drawImage(cardImages[suit][rank], 160 + 40*i, 690, this);
	    		}
	    		
	    		g.drawString("Played by " + game.getHandsOnTable().get(sizeofHands-1).getPlayer().getName(), 10, 725);
		    }
		    repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
		
		/**
		 * A method used to catch all the mouse click events and perform events/functions accordingly.
		 * It overrides the mousePressed method of JPanel.
		 * 
		 * @param e mouseevent object containg coordinates of the mouse event
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			boolean flag = false; 
			int numberofCards = game.getPlayerList().get(activePlayer).getNumOfCards();
			int cardToCheck = numberofCards-1;
			
			if (e.getX() >= (cardXCoord+cardToCheck*40) && e.getX() <= (cardXCoord+cardToCheck*40+73)) 
			{
				if(!selected[cardToCheck] && e.getY() >= (downCardYCoord + cardIncrement*activePlayer) && e.getY() <= (downCardYCoord + cardIncrement*activePlayer+97))
				{
					selected[cardToCheck] = true;
					flag = true;
				} 
				else if (selected[cardToCheck] && e.getY() >= (upCardYCoord + cardIncrement*activePlayer) && e.getY() <= (upCardYCoord + cardIncrement*activePlayer+97))
				{
					selected[cardToCheck] = false;
					flag = true;
				}
			}
			for (cardToCheck = numberofCards-2; cardToCheck >= 0 && !flag; cardToCheck--) 
			{
				if (e.getX() >= (cardXCoord+cardToCheck*cardWidth) && e.getX() <= (cardXCoord+(cardToCheck+1)*cardWidth)) 
				{
					if(!selected[cardToCheck] && e.getY() >= (downCardYCoord+cardIncrement*activePlayer) && e.getY() <= (downCardYCoord+cardIncrement*activePlayer+97))
					{
						selected[cardToCheck] = true;
						flag = true;
					} 
					else if (selected[cardToCheck] && e.getY() >= (upCardYCoord+cardIncrement*activePlayer) && e.getY() <= (upCardYCoord+cardIncrement*activePlayer+97))
					{
						selected[cardToCheck] = false;
						flag = true;
					}
				}
				else if (e.getX() >= (cardXCoord+(cardToCheck+1)*cardWidth) && e.getX() <= (cardXCoord+cardToCheck*cardWidth+73) && e.getY() >= (downCardYCoord+cardIncrement*activePlayer) && e.getY() <= (downCardYCoord+cardIncrement*activePlayer+97)) 
				{
					if (selected[cardToCheck+1] && !selected[cardToCheck]) 
					{
						selected[cardToCheck] = true;
						flag = true;
					}
				}
				else if (e.getX() >= (cardXCoord+(cardToCheck+1)*cardWidth) && e.getX() <= (cardXCoord+cardToCheck*cardWidth+73) && e.getY() >= (upCardYCoord + cardIncrement*activePlayer) && e.getY() <= (upCardYCoord + cardIncrement*activePlayer+97))
				{
					if (!selected[cardToCheck+1] && selected[cardToCheck])
					{
						selected[cardToCheck] = false;
						flag = true;
					}
				}
			}
			this.repaint();
		}


	}
	
	/**
	 * An inner class that implements the ActionListener interface.
	 * Implements the actionPerformed() method from the ActionListener interface to handle button-click events for the “Play” button.
	 * When the “Play” button is clicked, you should call the makeMove() method of your BigTwo object to make a move.
	 * 
	 * @author BHATIA Divtej Singh ( 3035832438 )
	 */
	class PlayButtonListener implements ActionListener{
		
		/**
		 * The function is overridden from the ActionListener Interface 
		 * and is used to perform the requisite function when the button is clicked.
		 * 
		 * @param e This is a ActionEvent object to detect if some user interaction was performed on the given object
		 */
		public void actionPerformed(ActionEvent e) 
		{
			if (getSelected() == null)
			{
				printMsg("Select cards to play.\n");
			}
			else {
			game.makeMove(activePlayer, getSelected());
			}
			repaint();
		}
		
	}
	
	/**
	 * This inner class implements the ActionListener interface and is used to detect the clicks on the passButton 
	 * and call the makeMove function based on the click.
	 *
	 * @author BHATIA Divtej Singh ( 3035832438 )
	 **/
	class PassButtonListener implements ActionListener{
		
		/**
		 * The function is overridden from the ActionListener Interface 
		 * and is used to perform the requisite function when the button is clicked.
		 * 
		 * @param e This is a ActionEvent object to detect if some user interaction was performed on the given object.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			game.makeMove(activePlayer, null);
			repaint();
		}	
	}
	
	/**
	 * an inner class that implements the ActionListener interface. 
	 * Implements the actionPerformed() method from the ActionListener interface to handle menu-item-click events for the “Restart” menu item.
	 * 
	 * @author BHATIA Divtej Singh ( 3035832438 )
	 */
	class RestartMenuItemListener implements ActionListener{

		/**
		 * The function overrides the ActionPerformed function in ActionListener interface to detect 
		 * the user interaction on the object and carry out necessary functions.
		 * 
		 * @param e This is a ActionEvent object to detect if some user interaction was performed on the given object.
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			BigTwoDeck deck = new BigTwoDeck();
			deck.shuffle();
			game.start(deck);
			reset();
		}
	}
	
	/**
	 * an inner class that implements the ActionListener interface. 
	 * Implements the actionPerformed() method from the ActionListener interface to handle menu-item-click events for the “Quit” menu item.
	 * 
	 * @author BHATIA Divtej Singh ( 3035832438 )
	 */
	class QuitMenuItemListener implements ActionListener{

		/**
		 * The function overrides the ActionPerformed function in ActionListener interface to detect 
		 * the user interaction on the object and carry out necessary functions.
		 *  
		 *  @param e This is a ActionEvent object to detect if some user interaction was performed on the given object
		 */
		public void actionPerformed(ActionEvent e) {

			System.exit(0);	
		}
		
		
	}
	/**
	 * an inner class the implements the ActionListener Interface
	 * Implements the actionPerformed() method from the ActionListener interface to handle menu-item-click events for the “Clear Chat ” menu item in Message menu.
	 * 
	 * @author BHATIA Divtej Singh ( 3035832438 )
	 *
	 */
	class clearChatActionListener implements ActionListener{
		
		/**
		 * The function overrides the ActionPerformed function in ActionListener interface to detect 
		 * the user interaction on the object and carry out necessary functions.
		 *  
		 *  @param e This is a ActionEvent object to detect if some user interaction was performed on the given object
		 */
		public void actionPerformed(ActionEvent e) {
			// chatbox clearing	 
			chatArea.setText(null);
		}
	}
	
	
}