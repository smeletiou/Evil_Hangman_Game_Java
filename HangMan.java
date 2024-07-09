package team10.hw5;

import java.util.*;

/**
 * Main Method.
 * 
 * @author N.STAM /S.R.MELE
 */
public class HangMan {
	static int guesses;// total guesses
	static int words;
	static ArrayList<String> guessedLetters;//list with the guessed letters
	static String hideWord = "";;
	static int wordLength;//chosen length of word
/**
 * Main method that runs the game.
 * @param args reads the dictionary , number of guesses , word length 
 */
	public static void main(String[] args) {
		info();//prints 
		Checker.checkinfoGame(args); // sets the length of the hidden word and the number of tries
		guessedLetters = new ArrayList<String>();//creates a list for the guessed letters
		Checker.initHideWord(); // initialize the hidden word with (-----)

		// prepare the dictionary contain word of the given length
		Dictionary dictionary = new Dictionary();//creates the dictionary
		String dictionaryFile = args[0];//reads the file 
		dictionary.readFile(dictionaryFile); // reading dictionary from file
		dictionary.setLengthword(wordLength); //sets the given word length
		dictionary.sameLengthWords();//finds words from the dictionary with the same given length
		words = dictionary.wordsCount();
		showStatus();//shows the status from every "round"
		//game
		do {
			String guessLetter = Checker.CheckGuessLetter();//calls the method for the input
			if (!guessLetter.equals("already quessed")) {//checks if the letter is already be guessed
				String hideWordPattern = dictionary.findMatchPattern(guessLetter);
				Checker.AddLettersTohideWord(hideWordPattern);
				// dictionary.show();
				int count = Checker.checkIfLetterFound(hideWord);//checks the word if it has the guessed letter in 
				if (count == 0) {//if there are no letters 
					System.out.println("Sorry there are no " + guessLetter + "'s\n");//
					guesses--;
				} else {//else prints how many of the guessed letter the word has
					System.out.println("Yes, there are " + count + " " + guessLetter + "'s\n");
				}
				if (Checker.checkWordFound()) {//if \ you guessed the world 
					System.out.println("Answer = " + hideWord + "\nYou Beat me\n");//prints the word 
					System.exit(0);//exits
				}
				words = dictionary.wordsCount();
			} else {
				System.out.println("You already quessed that\n");
			}
			showStatus();//prints the status of the round 
		} while (guesses != 0 && !Checker.checkWordFound());
		if (guesses <= 0) {//message for loss
			System.out.println("_____________________");
			System.out.println("|           |        ");
			System.out.println("|           |        ");
			System.out.println("|          ( )       ");
			System.out.println("|           |        ");
			System.out.println("|          /|\\      ");
			System.out.println("|         / | \\     ");
			System.out.println("|           |        ");
			System.out.println("|          / \\      ");
			System.out.println("|         /   \\     ");
			System.out.println("|                    ");
			System.out.println("|_______________     ");

			hideWord = dictionary.randomWord();//finds a random word to say 
			System.out.println("Answer = " + hideWord + "\nSorry, you lose\n");//prints the random word 
		}
	}
/**
 * Personal info.
 */
	public static void info() {
		System.out.println("*********************************");
		System.out.println("*           Homework 5          *");
		System.out.println("*           TEAM No 10          *");
		System.out.println("*       NIKOLETA STAMATOULAKI   *");
		System.out.println("*           AN 461114           *");
		System.out.println("*       SOTIRIS MELETIOU        *");
		System.out.println("*           AM941797            *");
		System.out.println("*********************************");
	}
/**
 * Status for rounds method.
 */
	public static void showStatus() {
		System.out.println("guesses : " + guesses);
		System.out.println("Words : " + words);
		System.out.println("guessed : " + guessedLetters);
		System.out.println("current :" + hideWord);
	}
}
