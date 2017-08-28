/*
 * Kegan Schaub
 * Zach Van Uum
 * 
 * Section 3
 */
// GUI components you will need
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * An event driven graphical user interface for the Boggle game using the design
 * of DiceTray and Boggle classes in Rick Mercer's C Sc classes at the UofA
 */
@SuppressWarnings("serial")
public class BoggleGUI extends JFrame {

  public static void main(String[] args) {
    // having a main method means we can run this class as a Java Application
    BoggleGUI theView = new BoggleGUI();
    theView.setVisible(true);
  }

  private Boggle game;

  // GUI components you will need
  private JTextArea diceTrayArea;
  private JButton newGameButton = new JButton("New Game"); // will be a warning
  private JLabel wLabel = new JLabel("Enter your words below"); // will be a warning
  private JButton endButton = new JButton("End game"); // will be a warning
  private JTextArea userInputArea = new JTextArea(10, 25); // will be a warning

  public BoggleGUI() {
    setUpModel(); // given below
    layoutWindow(); // started below
    setupListeners(); // will be a compile time error until you add the helper method
    //startNewGame(); // will be a compile time error until you add the helper method
  }

  private void setUpModel(){
    try {
		game = new Boggle();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  // Add five GUI components to this JFrame
  private void layoutWindow() {
    // Set up the JFrame
    this.setSize(500, 270);
    this.setResizable(false);
    setLocation(10, 10);
    setTitle("Boggle");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // With null layout, you must set the size and location of every component
    setLayout(null);

    // Layout the dice tray area as a JTextArea
    diceTrayArea = new JTextArea();
    diceTrayArea.setEditable(false);
    diceTrayArea.setBackground(Color.cyan);
    diceTrayArea.setFont(new Font("Courier", Font.BOLD, 24));

    // Every GUI component must receive a setSize and setLocation
    // message and then be added to the this JFrame
    diceTrayArea.setSize(210, 220);
    diceTrayArea.setLocation(10, 10);
    add(diceTrayArea);

    // Declare and setSize and setLocation of a JLabel for "Enter your words below"
    wLabel.setSize(170, 85);
    wLabel.setLocation(240, 0); 
    add(wLabel); //This is where we want it!

    // setSize and setLocation of the user input area. Also set line wrap true
    userInputArea.setSize(220, 150); //left horizontally //right vertically 
    userInputArea.setLocation(240, 50); //left shifts right //right shifts down
    add(userInputArea); //another one done

    // setSize and setLocation of the newGameButton
    newGameButton.setSize(100, 20);
    newGameButton.setLocation(240, 210);
    add(newGameButton);

    // setSize and setLocation of the endButton
    endButton.setSize(100, 20);
    endButton.setLocation(360, 210);
    add(endButton);

    // If you haven't already done so, add all of the graphical components to this JFrame
  }
  private void setupListeners(){
	  
	  diceTrayArea.setText(game.getDiceTrayAsString());
	  newGameButton.addActionListener(new newButtonListener());
	  endButton.addActionListener(new endButtonListener());
  }
  private class newButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Boggle newGame = null;
		try {
			newGame = new Boggle();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game = newGame;
		diceTrayArea.setText(newGame.getDiceTrayAsString());
		userInputArea.setText("");
		
	}
	  
  }
  private class endButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String toPrint = "";
		String wordsFound = "";
		String incWords = "";
		String numFound = "";
		String allWords = "";
		
		String input = userInputArea.getText();
		System.out.println(input);
		Scanner userInput = new Scanner(input);
		ArrayList<String> temp = new ArrayList<String>();
		while(userInput.hasNext()){
			temp.add(userInput.next().trim().toLowerCase());
		}
		for(int i=0; i<temp.size(); i++){
			game.addGuess(temp.get(i));
		}
		int count = 0;
		for(int i=0; i<game.getWordsFound().size(); i++){
			wordsFound += game.getWordsFound().get(i) + " ";
			if(count % 10 == 0 && count != 0){
				wordsFound += "\n";
			}
			count++;
		}
		wordsFound.trim();
		
		int count2 = 0;
		for(int i=0; i<game.getWordsIncorrect().size(); i++){
			incWords += game.getWordsIncorrect().get(i) + " ";
			if(count2 % 10 == 0 && count2 != 0){
				incWords += "\n";
			}
			count2++;
		}
		incWords.trim();
		
		/*int count3 = 0;
		for(int i=0; i<game.getWordsNotGuessed().size(); i++){
			allWords += game.getWordsNotGuessed().get(i) + " ";
			if(count3 % 10 == 0 && count3 != 0){
				allWords += "\n";
			}
			count3++;
		}*/
		allWords = game.getWordsNotGuessed().toString();
		allWords = allWords.substring(1, allWords.length()-1);
		allWords = allWords.replaceAll(",", "");
		String temp1 = "";
		int j = 0;
		Scanner scan = new Scanner(allWords);
		while (scan.hasNext()){
			temp1 += scan.next() + " ";
			j++;
			if (j % 10 == 0 && j != 0){
				temp1 += "\n";
			}
		}
		
		toPrint += "Your score: " + game.getScore() + "\n\nWords you found:\n\n" + wordsFound + "\n\nIncorrect words:\n\n" +incWords + "\n\nYou could have found " + game.getWordsNotGuessed().size() + " more words.\nThe computer found all of your words plus these you could have had:\n\n\n" + temp1;
		
		Boggle newGame = null;
		try {
			newGame = new Boggle();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		game = newGame;
		JOptionPane.showMessageDialog(null, toPrint);
		diceTrayArea.setText(newGame.getDiceTrayAsString());
		userInputArea.setText("");
	}
	  
  }
 
}
