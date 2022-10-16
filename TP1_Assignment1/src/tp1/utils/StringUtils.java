package tp1.utils;

/**  
 * Util class for string handling
 * 
 * @author Simon Pickin
 * @author Ivan Martinez-Ortiz
 */
public class StringUtils {

	/**
	 * Repeat a character for some times
	 * 
	 * @param c character to be repeated
	 * @param length number of times of repeating the character
	 * 
	 * @return string containing the repeated characters
	 */
	public static String repeat(char c, int length) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < length; i++) {
		    buffer.append(c);
		}
		return buffer.toString();
	}
	
	/**
	 * Repeat a string for some times
	 * 
	 * @param string to be repeated
	 * @param length number of times of repeating the string
	 * 
	 * @return string containing the repeated input strings
	 */
	public static String repeat(String string, int length) {
		return string.repeat(length);
	}

	/**
	 * Center a string in an single-line area of known length 
	 * 
	 * @param text string to be centered
	 * @param length length of the single-line area
	 * 
	 * @return string representation of the single-line area with the input string sitting in the center
	 */
	public static String centre(String text, int length){
		return centre(text, length, ' ');
	}

	
	/**
	 * Center a string in an single-line area of known length and pad character to the left and right of the string
	 * 
	 * @param text string to be centered
	 * @param length length of the single-line area
	 * @param paddingChar character to pad the string
	 * 
	 * @return string representation of the single-line area with the input string sitting in the center with the character padded on both of its side
	 */
	public static String centre(String text, int length, char paddingChar){
		if (length < text.length()) {
			throw new IllegalArgumentException(String.format("length must be at least '%d', but is '%d'", text.length(), length));
		}

		int paddingLength = length - text.length();
		int paddingRight = paddingLength / 2;
		int paddingLeft = paddingRight + paddingLength % 2;
		
	    return String.format("%s%s%s", repeat(paddingChar, paddingLeft), text, repeat(paddingChar, paddingRight));
	}

}
