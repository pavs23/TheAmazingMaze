import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A class for encrypting and decrypting score data
 * @author Bronte Kalebic
 *
 */
public class ScoreEncrypter {

	/**
	 * A hash table for converting characters to random strings
	 */
	private Hashtable<Character, String> hash;
	private Hashtable<String, Character> reverseHash;
	
	private final int NUM_LOWER_CASE_LETTERS = 26;
	private final int NUM_UPPER_CASE_LETTERS = 52;
	private final int LOWER_CASE_CONVERSION = 97;
	private final int UPPER_CASE_CONVERSION = 65;
	private final int NUMBER_CONVERSION = 48;
	
	/**
	 * Constructor for this class. Defines the value for every key, 
	 * which is every letter (lower and upper case) and numbers 1 to 9.
	 */
	public ScoreEncrypter(){
		
		hash = new Hashtable<Character, String>();
		reverseHash = new Hashtable<String, Character>();
		
		/*HASH VALUES*/
		//lower case letters
		hash.put('a', "qwe");
		hash.put('b', "hul");
		hash.put('c', "ygs");
		hash.put('d', "ygb");
		hash.put('e', "pos");
		hash.put('f', "tgv");
		hash.put('g', "rea");
		hash.put('h', "1dk");
		hash.put('i', "vhg");
		hash.put('j', "tgh");
		hash.put('k', "eds");
		hash.put('l', "6gv");
		hash.put('m', "hg9");
		hash.put('n', "kkg");
		hash.put('o', "r4f");
		hash.put('p', "qqe");
		hash.put('q', "cdo");
		hash.put('r', "uya");
		hash.put('s', "t6g");
		hash.put('t', "4ea");
		hash.put('u', "yhb");
		hash.put('v', "hg7");
		hash.put('w', "oop");
		hash.put('x', "lka");
		hash.put('y', "g6a");
		hash.put('z', "tgg");
		
		//upper case letters
		hash.put('A', "2gd");
		hash.put('B', "78h");
		hash.put('C', "dsc");
		hash.put('D', "kpa");
		hash.put('E', "bvn");
		hash.put('F', "0pg");
		hash.put('G', "ffv");
		hash.put('H', "vac");
		hash.put('I', "gb4");
		hash.put('J', "rfc");
		hash.put('K', "fg6");
		hash.put('L', "uhn");
		hash.put('M', "ken");
		hash.put('N', "to3");
		hash.put('O', "fu8");
		hash.put('P', "tim");
		hash.put('Q', "isa");
		hash.put('R', "gre");
		hash.put('S', "att");
		hash.put('T', "uto");
		hash.put('U', "rr1");
		hash.put('V', "not");
		hash.put('W', "rea");
		hash.put('X', "lly");
		hash.put('Y', "edc");
		hash.put('Z', "rf3");
		
		//numbers
		hash.put('0', "uhb");
		hash.put('1', "kp9");
		hash.put('2', "mhl");
		hash.put('3', "fvg");
		hash.put('4', "9kn");
		hash.put('5', "yh6");
		hash.put('6', "kma");
		hash.put('7', "km3");
		hash.put('8', "ipl");
		hash.put('9', "ooa");
		
		hash.put(' ', " ");
		
		/*REVERSE HASH VALUES*/
		//lower case letters
		reverseHash.put("qwe", 'a');
		reverseHash.put("hul", 'b');
		reverseHash.put("ygs", 'c');
		reverseHash.put("ygb", 'd');
		reverseHash.put("pos", 'e');
		reverseHash.put("tgv", 'f');
		reverseHash.put("rea", 'g');
		reverseHash.put("1dk", 'h');
		reverseHash.put("vhg", 'i');
		reverseHash.put("thh", 'j');
		reverseHash.put("eds", 'k');
		reverseHash.put("6gv", 'l');
		reverseHash.put("hg9", 'm');
		reverseHash.put("kkg", 'n');
		reverseHash.put("r4f", 'o');
		reverseHash.put("qqe", 'p');
		reverseHash.put("cdo", 'q');
		reverseHash.put("uya", 'r');
		reverseHash.put("t6g", 's');
		reverseHash.put("4ea", 't');
		reverseHash.put("yhb", 'u');
		reverseHash.put("hg7", 'v');
		reverseHash.put("oop", 'w');
		reverseHash.put("lka", 'x');
		reverseHash.put("g6a", 'y');
		reverseHash.put("tgg", 'z');
		
		//upper case letters
		reverseHash.put("2gd", 'A');
		reverseHash.put("78h", 'B');
		reverseHash.put("dsc", 'C');
		reverseHash.put("kpa", 'D');
		reverseHash.put("bvn", 'E');
		reverseHash.put("0pg", 'F');
		reverseHash.put("ffv", 'G');
		reverseHash.put("vac", 'H');
		reverseHash.put("gb4", 'I');
		reverseHash.put("rfc", 'J');
		reverseHash.put("fg6", 'K');
		reverseHash.put("uhn", 'L');
		reverseHash.put("ken", 'M');
		reverseHash.put("to3", 'N');
		reverseHash.put("fu8", 'O');
		reverseHash.put("tim", 'P');
		reverseHash.put("isa", 'Q');
		reverseHash.put("gre", 'R');
		reverseHash.put("att", 'S');
		reverseHash.put("uto", 'T');
		reverseHash.put("rr1", 'U');
		reverseHash.put("not", 'V');
		reverseHash.put("rea", 'W');
		reverseHash.put("lly", 'X');
		reverseHash.put("edc", 'Y');
		reverseHash.put("rf3", 'Z');
		
		//numbers
		reverseHash.put("uhb", '0');
		reverseHash.put("kp9", '1');
		reverseHash.put("mhl", '2');
		reverseHash.put("fvg", '3');
		reverseHash.put("9kn", '4');
		reverseHash.put("yh6", '5');
		reverseHash.put("kma", '6');
		reverseHash.put("km3", '7');
		reverseHash.put("ipl", '8');
		reverseHash.put("ooa", '9');
		
		reverseHash.put(" ", ' ');

	}
	
	/**
	 * Encrypts a string of name and high score by converting it to a 
	 * string unreadable by users. 
	 * @param string is the string of name and high score
	 * @return an encrypted string
	 */
	public String encryptString(String string){
		char[] score = string.toCharArray();
		int i = 0;
		int j = 0;
		int size = score.length;
		String[] encryptedScore = new String[size*2];
		while (i < size){
			if (score[i] == ' '){
				encryptedScore[j] = " ";
			} else {
				encryptedScore[j] = hash.get(score[i]);
			}
			encryptedScore[j+1] = ",";
			i++;
			j += 2;
			//System.out.println("i is " + i);
		}
		
		//add strings in array together 
		StringBuilder builder = new StringBuilder();
		for(String s : encryptedScore) {
		    builder.append(s);
		}
		return builder.toString();

	}
	
	/**
	 * Decrypts a string taken from the input file and turns it into
	 * a string that people can read. The string includes the player's
	 * name and their score.
	 * @param encryptedString is the string to be decrypted
	 * @return a decrypted string with the high score details
	 */
	public String decryptString(String encryptedString){
		//split string into each sub string to be turned into a char
		String[] encryptedScore = encryptedString.split(",");
		int i = 0;
		int size = encryptedScore.length;
		ArrayList<Character> score = new ArrayList<Character>();
		while (i < size){
			score.add(reverseHash.get(encryptedScore[i]));
			i++;
		}

		//add chars in array together 
		StringBuilder builder = new StringBuilder();
		for(char c : score) {
		    builder.append(c);
		}
		return builder.toString();
		
		//return score.toString();
	}
	
	//THIS DOESNT WORK
	/*private char getHashKey(String encryptedSubString){
		int i = 0;
		int size = hash.size();
		ArrayList<String> hashValues = new ArrayList<String>(hash.values());
		while (i < size){
			System.out.println("hashValues.get(i) is " + hashValues.get(i));
			if (encryptedSubString.equals(hashValues.get(i))){
				if (i < NUM_LOWER_CASE_LETTERS){
					System.out.println("lower case letter");
					i += LOWER_CASE_CONVERSION;
				} else if (i < NUM_UPPER_CASE_LETTERS){
					System.out.println("upper case letter");
					i += UPPER_CASE_CONVERSION;
				} else {
					System.out.println("number");
					i += NUMBER_CONVERSION;
				}
				break;
			}
			i++;
		}
		
		return (char) i;
	}*/
	
}
