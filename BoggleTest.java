/*
 * Kegan Schaub
 * Zach Van Uum
 * 
 * Section 3
 */
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;


public class BoggleTest {
	
	@Test
	public void testGetWordsFoundAfterPrepareResultsCalledWithSetDiceTray() throws FileNotFoundException {
		char[][] tray = {
		{ 'E', 'R', 'H', 'I' },
		{ 'T', 'C', 'O', 'Z' },
		{ 'I', 'E', 'S', 'E' },
		{ 'V', 'E', 'V', 'W' } };
		DiceTray dt = new DiceTray(tray);
		Boggle game = new Boggle();
		game.setDiceTray(dt);
		game.addGuess("see");//1
		game.addGuess("see");
		game.addGuess("see");
		game.addGuess("tEeS");//1
		game.addGuess("Receives");//11
		game.addGuess("Sort");//1
		game.addGuess("cites");//2
		game.addGuess("seCreTive");//11
		game.addGuess("NotHere");//0
		game.addGuess("NotHere");
		game.addGuess("sew");//1
		System.out.println(game.getWordsFound());
		System.out.println(game.getWordsNotGuessed());
		assertEquals(28, game.getScore());
		assertEquals("[cites, receives, secretive, see, sew, sort, tees]",
		game.getWordsFound().toString());
		assertEquals("[nothere]", game.getWordsIncorrect().toString());
		assertTrue(game.getWordsNotGuessed().contains("secret"));
		assertTrue(game.getWordsNotGuessed().contains("recite"));
	}
	
	@Test
	public void testNoDuplicates() throws FileNotFoundException{
		char[][] tray = {
				{ 'D', 'U', 'P', 'L' },
				{ 'P', 'L', 'O', 'Z' },
				{ 'I', 'C', 'S', 'E' },
				{ 'A', 'T', 'E', 'W' } };
				DiceTray dt = new DiceTray(tray);
				Boggle game = new Boggle();
				game.setDiceTray(dt);
				game.addGuess("duplicate");
				game.addGuess("duplicate");
				game.addGuess("duplicate");
				assertEquals(game.getWordsFound().size(), 1);
				assertEquals("[duplicate]", game.getWordsFound().toString());
	}
	@Test
	public void letsCheat() throws FileNotFoundException{
		char[][] tray = {
				{ 'm', 'e', 'n', 'i' },
				{ 'k', 'l', 'd', 'e' },
				{ 'h', 'i', 'a', 'o' },
				{ 'u', 't', 'w', 'o' } };
				DiceTray dt = new DiceTray(tray);
				Boggle game = new Boggle();
				String temp1 = "";
				int j = 0;
				String allWords = "" + game.getWordsNotGuessed();
				allWords = game.getWordsNotGuessed().toString();
				allWords = allWords.substring(1, allWords.length()-1);
				allWords = allWords.replaceAll(",", "");
				Scanner scan = new Scanner(allWords);
				while (scan.hasNext()){
					temp1 += scan.next() + " ";
					j++;
					if (j % 10 == 0 && j != 0){
						temp1 += "\n";
					}
				}
				System.out.println(temp1);
				
	}
}
