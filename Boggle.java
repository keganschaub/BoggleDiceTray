import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Kegan Schaub
 * Zach Van Uum
 * 
 * Section 3
 */

public class Boggle {
	//Instance variables
	private DiceTray dicetray;
	private Scanner boggleWords;
	private ArrayList<String> guesses;
	private ArrayList<String> found;
	private ArrayList<String> incorrect;
	private ArrayList<String> leftovers;
	private ArrayList<String> words;
	
	
	//Other instance variables will be needed to store collections such
	//as the 80,000+ BoggleWords ...
	//Initialize a game with 16 dice to simulate the real game with the real
	//Boggle dice. You will need java.util.Random to get a random side. Also make
	//sure you shuffle the dice so the dice appear in seemingly random locations!
	public Boggle() throws FileNotFoundException {
		words = new ArrayList<String>();
		try{
			boggleWords = new Scanner((new File("BoggleWords.txt")));
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
		}
		while (boggleWords.hasNext()){
			words.add(boggleWords.next().trim());
		}
	    dicetray = new DiceTray();
	    guesses = new ArrayList<String>();
	    found = new ArrayList<String>();
	    incorrect = new ArrayList<String>();
	    leftovers = new ArrayList<String>();
	}
	
	//Allow testers to set the DiceTray to a known one (not random).
	public void setDiceTray(DiceTray dt) {
	    this.dicetray = dt;
	}
	
	//Return the DiceTray object as a textual representation as a String
	public String getDiceTrayAsString() {
	    return dicetray.toString();
	}
	
	//Record one word (a string with no whitespace) as one of the users' guesses.
	//Do what you want with it, but oneGuess should be processed so all methods
	//can return the correct values such as getScore() and getWordsFound().
	public void addGuess(String oneGuess) {
	    guesses.add(oneGuess.trim().toLowerCase());
	    Collections.sort(guesses);
	}
	
	//Return a list of all words the user entered that count for the score.
	//The found words must be in their natural ordering. This method should
	//return an empty list until addGuess(String) is called at least once.
	//The returned List<E> could also be empty if no attempts actually
	//counted for anything.
	//
	//There must be no duplicates!
	public ArrayList<String> getWordsFound() {
		int count = 0;
		
		while(count < guesses.size()){
			if(words.contains(guesses.get(count).toLowerCase())){
				if(dicetray.stringFound(guesses.get(count))){
					if(!found.contains(guesses.get(count).toLowerCase())){
						found.add(guesses.get(count).toLowerCase());
					}
				}
			}
			count++;
		}
		
		Collections.sort(found);
	    return found;
	}
	
	//Return a list of all words the user entered that do not count for the score
	//in their natural order. This list may be empty with no words guessed or all
	//guessed words actually count for points.
	//
	//There must be no duplicates!
	public List<String> getWordsIncorrect() {
		int count = 0;
		
		while(count < guesses.size()){
			if(!dicetray.stringFound(guesses.get(count))){
				if(!incorrect.contains(guesses.get(count).toLowerCase())){
					incorrect.add(guesses.get(count).toLowerCase());
				}
			}
			count++;
		}
		
		Collections.sort(incorrect);
	    return incorrect;
	}
	
	//All words the user could have guessed but didn't in their natural order.
	//This list could also be empty at first or if the user guessed ALL words
	//in the board and in the list of 80K words (unlikely).
	//
	//There must be no duplicates!
	public List<String> getWordsNotGuessed() {
		int count = 0;
		
		while(count < words.size()){
			if(dicetray.stringFound(words.get(count)) == true){
				if(found.contains(words.get(count)) != true){
					leftovers.add(words.get(count));
				}
			}
			count++;
		}
		
		return leftovers;
	}
	
	public int getScore(){
		ArrayList<String> temp = new ArrayList<String>();
		int score = 0;
		int count = 0;
		
		while(count < found.size()){
			if(found.get(count).length() == 3 || found.get(count).length() == 4)
				score += 1;
			else if(found.get(count).length() == 5)
				score += 2;
			else if(found.get(count).length() == 6)
				score += 3;
			else if(found.get(count).length() == 7)
				score += 5;
			else if(found.get(count).length() >= 8)
				score += 11;
			count++;
		}
		
		return score;
	}
	
}
