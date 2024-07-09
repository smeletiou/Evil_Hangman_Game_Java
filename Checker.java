package team10.hw5;
import java.util.Scanner;
/**
 * Regulator/Checking class .
 * @author N.STAM /S.R.MELE
 */
public class Checker {
	/**
	 * Correct run.
	 * This method checks if you gave the correct arguments and with the correct format 
	 * to the args so the game can start
	 * @param args given dictionary and values 
	 */
	public static void checkinfoGame(String[] args) {
		if (args.length == 0) {//checks for empty args
			System.out.println("Please set run arguments <dictionary>, <length word>, <guess>");
			System.exit(0);//exist 
		}
		HangMan.wordLength =0; 
		try {
			HangMan.wordLength =Integer.parseInt(args[1]);
			HangMan.guesses = Integer.parseInt(args[2]);
            if (HangMan.wordLength <= 0 ||HangMan.guesses <= 0) {//checking that the word length is more than 0
    			System.out.println(" The length word must be grater than 0");
    			System.exit(0);
    		}
		}catch(Exception e) {
			System.out.println("Wrong Arguments");
		}
		
		//game Settings 
		System.out.println("Welcome to the hungman Game \n");
		System.out.println("You choose to use a length word of: " + args[1]);
		System.out.println("You choose to use a maximum of tries: "+ args[2]);
		System.out.println();
	
	}
	
	/**
	 * Adds the letters from pattern to the hidden word.
	 * @param s
	 */
	public static void AddLettersTohideWord(String s) {
		char[] temp1 = s.toCharArray();
		char[] temp2 = HangMan.hideWord.toCharArray();
		for (int i=0;i<HangMan.wordLength;i++) {
			if (temp1[i] != '-')  temp2[i]=temp1[i];
		}
		HangMan.hideWord=String.valueOf(temp2);
	}
	
	/**
	 *Checks if the given letter is in the hidden word and how many times.
	 * @param s given letter
	 * @return returns the count of the letter in the current word
	 */
	public static int checkIfLetterFound(String s) { 
		String[] temp = s.split("(?!^)");
		String letterToSearch = HangMan.guessedLetters.get(HangMan.guessedLetters.size()-1);  // take the last guess letter
		int count = 0;
		for (int i=0;i<temp.length;i++) {
			if (temp[i].equals(letterToSearch)) 
				count++;
		}
		return count;
	}
	
	/**
	 * Found word.
	 * checks if the word has anymore (-) in it
	 * if it has the word is not found yet 
	 * if it doesnt you found the whole word
	 * @return returns if you find it or not 
	 */
	public static boolean checkWordFound() { 
	    return  !HangMan.hideWord.contains("-"); 
	}
	
	/**
	 * input of game.
	 * checks if you gave correct input (letter)
	 * and if its a new letter and not an already guessed one 
	 * @return returns the letter given
	 */
	public static String CheckGuessLetter() {
		Scanner in = new Scanner(System.in);
		
		String l;
	
		do {
			System.out.print("Your guess ?:");
			l = in.nextLine().toLowerCase();//.toUpperCase(); //changes all the input to uppercase
			if (HangMan.guessedLetters.contains(l)) {//checks the guessed letters list if you have already guessed that letter
				return "already quessed";
			}
		}while (l.length()!=1 || !l.matches("[a-z]+"));//input must be 1 length and a character 
		HangMan.guessedLetters.add(l);//adds the new guess to the list 
		return l;
	}
	
	/**
	 *Hides the word behind (-).
	 *initializes the hidden word with (-) with the given length
	 */
	public static void initHideWord() {
		for (int i=0;i<HangMan.wordLength;i++) {
			HangMan.hideWord += "-";
		}
	}
}

