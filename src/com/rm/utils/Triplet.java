package com.rm.utils;

/*
 * This captures the details about triplet.
 * Left connection, Right connection and Message
 */
public class Triplet {

	String left;
	String message;
	String right;

	/*
	 * Parameterised constuctor accepting a text (i.e. TXC#t#OQR)
	 * Internally, it splits the text in 3 parts for 
	 * Left connection, Right connection and Message
	 */
	public Triplet(String triplet) {
		
		int start = 0;
		int end = triplet.indexOf(Constants.HASH);
		this.left = triplet.substring(start, end);

		start = end+1;
		end = triplet.indexOf(Constants.HASH, start+1);
		this.message = triplet.substring(start, end);
		
		start = end+1;
		end = triplet.indexOf(Constants.HASH, start+1);
		this.right = triplet.substring(start);
	}
	
	/*
	 * Returns text for left connection 
	 */
	public String getLeft() {
		return left;
	}
	
	/*
	 * Returns text for message part 
	 */
	public String getMessage() {
		return message;
	}
	
	/*
	 * Returns text for right connection 
	 */
	public String getRight() {
		return right;
	}
	
	/*
	 * Returns reversed text of right connection
	 * that will used to search next triplet in a chain 
	 */
	public String getNext() {
		
		if (right == null) {
			return null;
		}
		
		char[] chArr = right.toCharArray();

		String reverse = "";
        for (int index = chArr.length - 1; index >= 0; index--) {
        	reverse = reverse + chArr[index];
		}		
		
        return reverse;
	}
	
	/*
	 * String representation of triplet for the purpose of debugging
	 * as mention below for EJY#w#QOM 
	 * [Triplet -> Left:EJY; Message:w; Right:QOM]
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.OPEN_SQUARE_BRACE);
		sb
			.append(this.getClass().getSimpleName()).append(Constants.SPACE)
			.append("->").append(Constants.SPACE)
			.append("Left:").append(left).append(Constants.SEMICOLON).append(Constants.SPACE)
			.append("Message:").append(message).append(Constants.SEMICOLON).append(Constants.SPACE)
			.append("Right:").append(right);
		sb.append(Constants.CLOSE_SQUARE_BRACE);
		
		return sb.toString();
	}
}
