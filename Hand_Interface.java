/**
 * This interface is for two abstract methods isValid() and getType.
 * This interface is implemented by Hand as well as subclasses of hand
 * 
 * @author BHATIA Divtej Singh (UID: 3035832438)
 *
 */
public interface Hand_Interface {
	/**
	 * A method for checking if THIS is a valid hand.
	 * 
	 * @return true or false is THIS hand is of a particular type.
	 */
	public abstract boolean isValid();
	
	/**
	 * A method for returning a string specifying the type of this hand.
	 * 
	 * @return String Containing name of the hand type. (eg: Pair type as "Pair")
	 */
	public abstract String getType();

}
