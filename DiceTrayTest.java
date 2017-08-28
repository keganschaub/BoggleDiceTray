/*
 * Kegan Schaub
 * Zach Van Uum
 * 
 * Section 3
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTrayTest {
	 private char[][] tray = {{'A', 'B', 'C', 'D' }, 
		      				  {'E', 'F', 'G', 'H' },
		      				  {'I', 'J', 'K', 'L' },
		      				  {'M', 'N', 'O', 'P' }};
	 
	 private char[][] tray2 = { {'Q', 'B', 'C', 'D' }, 
				  				{'E', 'I', 'T', 'H' },
				  				{'I', 'C', 'E', 'L' },
				  				{'M', 'A', 'K', 'P' }};
	 
	 private char[][] tray3 = { {'R', 'E', 'D', 'M' }, 
								{'B', 'A', 'N', 'O' },
								{'T', 'Q', 'D', 'F' },
								{'L', 'O', 'E', 'V' }};
	 
		  @Test
		public void testStringFindWhenThereStartingInUpperLeftCorner() {
		    DiceTray dt = new DiceTray(tray);
		    assertTrue(dt.stringFound("AbC"));  // Must be case insensitive
		    assertTrue(dt.stringFound("aBf"));
		    assertTrue(dt.stringFound("abc"));
		    assertTrue(dt.stringFound("ABCD"));
		    assertTrue(dt.stringFound("Pokn"));
		    assertTrue(dt.stringFound("ABFEJINM"));
		    assertTrue(dt.stringFound("AbCdHgFeIjKLpONm"));
		    assertTrue(dt.stringFound("ABCDHLPOKJNMIEFG"));
		}
		@Test
		public void testStringFindWhenNotThere () {
		    DiceTray dt = new DiceTray(tray);
		    assertFalse(dt.stringFound("acb"));
		    assertFalse(dt.stringFound("AiE"));
		    assertFalse(dt.stringFound("Hello?"));
		}
		@Test
		public void testStringFindWhenAttemptIsMadeToUseALetterTwice () {
		    DiceTray dt = new DiceTray(tray);
		    assertFalse(dt.stringFound("ABA"));
		    assertFalse(dt.stringFound("ABB"));
		    assertFalse(dt.stringFound("aEa"));
		    // ... 
	    }
		
		@Test
		public void testStringWhenQu () {
			DiceTray dt = new DiceTray(tray2);
		    assertTrue(dt.stringFound("Quiet"));
		    assertTrue(dt.stringFound("Quit"));
		    assertTrue(dt.stringFound("Quick"));
		    assertTrue(dt.stringFound("Bque"));
		}
		@Test
		  public void testFailedToFindWordsWithQInUpperAndLowerCase() {

		    char[][] board = { 
		    { 'N', 'A', 'T', 'R' },
		    { 'E', 'E', 'E', 'E' },
		    { 'E', 'R', 'I', 'T' },
		    { 'S', 'Q', 'E', 'N' } };

		    DiceTray test = new DiceTray(board);

		    assertTrue(test.stringFound("QUEEN"));
		    assertTrue(test.stringFound("QueeN"));
		    assertTrue(test.stringFound("qUeEN"));
		    assertTrue(test.stringFound("queEN"));
		    assertTrue(test.stringFound("quit"));
		    assertTrue(test.stringFound("QUit"));
		    assertTrue(test.stringFound("qUit"));
		    assertTrue(test.stringFound("Quit"));
		  }
		@Test
		public void testRandom () {
			DiceTray dt = new DiceTray(tray3);
		    assertTrue(dt.stringFound("RANDOM"));
		}
		  // Many more tests will be necessary
}
