package team10.hw5;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Dictionary interface.
 * @author N.STAM /S.R.MELE
 */
public class Dictionary {
	  private List<String> dictionary;
	 // private List<List<String>> sets;
	  private int lengthword;
	  
	  /**
	   * Dictionary constructor.
	   */
	  public Dictionary(){
		  dictionary = new ArrayList<String>(); 
	  }

	  /**
	   * Sets the given length for the words .
	   * @param lengthword the value from the arguments 
	   */
	  public void setLengthword(int lengthword) {
		this.lengthword = lengthword;
	  }

	  /**
	   * File reader for the dictionary file.
	   * @param fileName file from the args 
	   */
	  public void readFile(String fileName) {
	    	try {
		      File myFile = new File(fileName);
			  Scanner myReader = new Scanner(myFile);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        dictionary.add(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred reading Dictionary.");
		      System.exit(0);
		    }
		}
	  
	  /**
	   * Counts how many word still exists from the dictionary after every letter.
	   * @return the number of words in the dictionary
	   */
	  public int wordsCount() {
		  return dictionary.size();
	  }
	  
	  /**
	   *  choose a random cheating word from dictionary .
	   * @return returns the "random" chosen word that the pc chose
	   */
	  public String randomWord() {
		  Random rnd = new Random();
		  int choose = rnd.nextInt(wordsCount());
		  return dictionary.get(choose);
	  }
	  
	  /*
	  *only for testing to show the remaining words.
	  */
	  public void show() {
		  System.out.println(dictionary);
		  System.out.println();
	  }
	  
	  /**
	   * finds word with the same length that was given from the args 
	   */
	  public void sameLengthWords(){
		  ArrayList<String> newDictionary = new ArrayList<String>();
		  for(String s:dictionary) {
			  if (s.length()==lengthword) {
				  newDictionary.add(s);
			  }
		  }
		  dictionary=newDictionary;
	  }
	  
	  /**
	   * Finds a pattern to the existing words from the given letter.
	   * it returns the pattern that exists the most .for example if theres 5 words with the same patterns 
	   * it will chose this pattern
	   * @param letter guessed letter
	   * @return returns the pattern that exists more and shows it on the current .for example --e-- 
	   */
	  public String findMatchPattern(String letter) {
		  ArrayList<String> newDictionary = new ArrayList<String>();
		  ArrayList<String> patternList = new ArrayList<String>(); // a list of all patterns
		  
		  // create all possible patterns and store at patternList 
		  Pattern p = Pattern.compile(letter);
		  for(String s:dictionary) {
			   Matcher m = p.matcher(s);   // get a matcher object
			   if(m.find()) { 
				   String regex = "[^"+ letter +"]"; // exclude the given letter
				   s = s.replaceAll(regex,"-");      // exclude the given letter and replace all other character with "-" 
			   }else {
				    s = s.replaceAll("[a-z]","-");   // letter not found so replace all character with  "-" 
			   }
			   patternList.add(s); // store the pattern   
		  }
		 
		  // choose the pattern with max counts from patternList
		  int max = 0;
		  String choosePattern="";
		  for (String s:patternList) {
		     int count = Collections.frequency(patternList, s); //  count the times of any pattern appears in the list of patterns
		     if (count > max) { 
		    	 max= count;
		     	 choosePattern = s;
		     }
		  }
		  
		  // find the words according to the chosen pattern and store to newDictionary list
		  for (int i=0;i<patternList.size();i++) {
		      if (patternList.get(i).equals(choosePattern)) 
		    	  newDictionary.add(dictionary.get(i));
		  }
		  
		  // update the new dictionary list 
		  dictionary=newDictionary;
		  // return the pattern to show to user 
		  return choosePattern;
	  }
}
