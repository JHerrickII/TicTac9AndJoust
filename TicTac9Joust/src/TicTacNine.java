import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//Willy Sherrerd-Smith  wws2a
//John Herrick jah6vg
//Anish Doshi apd4hy
//Danny McNamara djm3am

public class TicTacNine extends JFrame {
	
	//Initializing variables to be used in the future
	private Container content;
	
	//Creating Icons
	ImageIcon face = new ImageIcon("illustrationCavMan.jpg");
	ImageIcon sabres = new ImageIcon("sabres.jpg");
	ImageIcon gray = new ImageIcon("gray.jpg");
	
	//Booleans
	private boolean noughts;
	private boolean gameOver;

	//JButtons
	JButton j1 = new JButton("");
	JButton j2 = new JButton("");
	JButton j3 = new JButton("");
	JButton j4 = new JButton("");
	JButton j5 = new JButton("");
	JButton j6 = new JButton("");
	JButton j7 = new JButton("");
	JButton j8 = new JButton("");
	JButton j9 = new JButton("");
	JButton currentMarker = new JButton("");
	JButton reset = new JButton("");
	JButton exit = new JButton("");
	JButton pressed = new JButton();

	//Cell Button Handlers
	CellButtonHandler h1 = new CellButtonHandler();
	CellButtonHandler h2 = new CellButtonHandler();
	CellButtonHandler h3 = new CellButtonHandler();
	CellButtonHandler h4 = new CellButtonHandler();
	CellButtonHandler h5 = new CellButtonHandler();
	CellButtonHandler h6 = new CellButtonHandler();
	CellButtonHandler h7 = new CellButtonHandler();
	CellButtonHandler h8 = new CellButtonHandler();
	CellButtonHandler h9 = new CellButtonHandler();
	ExitButtonHandler close = new ExitButtonHandler();
	InitButtonHandler init = new InitButtonHandler();
	CellButtonHandler[] CellHandler = {h1,h2,h3,h4,h5,h6,h7,h8,h9};
	
	//Array containing JButtons
	JButton[] JButtons = {j1,j2,j3,j4,j5,j6,j7,j8,j9};

	//Constructor for Tic Tac Nine
	public TicTacNine(){
		
		//Setting up the panel and grid
		setTitle("Tic Tac Nine");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(900, 900);
		content = getContentPane();

		content.setLayout(new GridLayout(4,3));

		//adds all relevant JButtons to the grid with their action listeners
		int h = 0;
		for(int i = 0; i < JButtons.length; i++){
			add(JButtons[i]);
			JButtons[i].addActionListener(CellHandler[h]);
			h++;
		}
		add(currentMarker);
		add(reset);
		add(exit);

		reset.addActionListener(init);
		reset.setText("RESET");

		exit.addActionListener(close);
		exit.setText("Exit");
		
		//Initializes the board
		init();
		setVisible(true);

	}

	//Class to set the defaults of the board every time it restarts
	public void init(){

		//Random turn starting generator
		Random coin = new Random();
		if(coin.nextInt()%2 == 1 || coin.nextInt()%2 == -1){
			noughts = true;
			currentMarker.setText("Current Turn: Sabres");
		}
		else{
			noughts = false;
			currentMarker.setText("Current Turn: Cav Man");
		}
		
		gameOver=false;
		
		//Sets the text, background, and icons for each button
		for(int i = 0; i < JButtons.length; i++){
			JButtons[i].setText("");
			JButtons[i].setBackground(Color.lightGray);
			JButtons[i].setIcon(gray);
		}
		setLocationRelativeTo(null);
	}

	//checks the board to see if all items are taken but there is no winner
	public boolean checkTie() {
		if(j1.getText() != "" && j2.getText() != "" && j3.getText() != "" && j4.getText() != "" && j5.getText() != "" && j6.getText() != "" && j7.getText() != "" && j8.getText() != "" && j9.getText() != ""){
			return true;
		}
		return false;
	}

	//Checks all possible ways to win in tic tac toe
	public boolean checkWinner() {
		
		if(j1.getText().equals(j2.getText()) && j2.getText().equals(j3.getText()) && j1.getText() != ""){
			return true;
		}
		else if(j4.getText().equals(j5.getText()) && j5.getText().equals(j6.getText()) && j4.getText() != ""){
			return true;
		}
		else if(j7.getText().equals(j8.getText()) && j8.getText().equals(j9.getText()) && j7.getText() != ""){
			return true;
		}
		else if(j1.getText().equals(j4.getText()) && j4.getText().equals(j7.getText()) && j1.getText() != ""){
			return true;
		}
		else if(j2.getText().equals(j5.getText()) && j5.getText().equals(j8.getText()) && j2.getText() != ""){
			return true;
		}
		else if(j3.getText().equals(j6.getText()) && j6.getText().equals(j9.getText()) && j3.getText() != ""){
			return true;
		}
		else if(j1.getText().equals(j5.getText()) && j5.getText().equals(j9.getText()) && j5.getText() != ""){
			return true;
		}
		else if(j3.getText().equals(j5.getText()) && j5.getText().equals(j7.getText()) && j3.getText() != ""){
			return true;
		}
		else{
			return false;
		}
	}

	//Defines the actions of the exit button
	private class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	//Defines the action of the reset/init button
	private class InitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			init();
		}
	}

	//Defines the action of all of the tic tac nine cells
	private class CellButtonHandler implements ActionListener{
		String winner = "";

		public void actionPerformed(ActionEvent e){
			//checks for a tie
			if (!checkWinner() && checkTie()){
				if (JOptionPane.showConfirmDialog(null, 
						"The game is tied, play again?", "Game Over!", JOptionPane.YES_NO_OPTION)
						!=JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				init();
				// new TicTacNine();

			}
			//If game over, ignore
			if(gameOver){
				currentMarker.setText("Game Over!");
				return;
			}

			//Get button pressed
			pressed=(JButton)(e.getSource());

			//Get text of button
			String text = pressed.getText();

			//If noughts or crosses, ignore
			if(text.equals("Orange") || text.equals("Blue")){
				currentMarker.setText("invalid space!");
				return;
			}

			//If there is no winner open a new Board class
			if (!checkWinner()){
				Board b = new Board();
				b.setNineButton(pressed);

			}	
			
			//If there is a winner display winner
			if (checkWinner() && noughts){
				if (JOptionPane.showConfirmDialog(null, 
						"Game Over! Sabres has won! \nPlay again?", "Game Over!", JOptionPane.YES_NO_OPTION)
						!=JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				init();
			}
			if (checkWinner() && !noughts){
				if (JOptionPane.showConfirmDialog(null, 
						"Game Over! Cav Man has won! \nPlay again?", "Game Over!", JOptionPane.YES_NO_OPTION)
						!=JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				init();
				// new TicTacNine();

			}

			//Determines whose turn it currently is
			if(noughts){
				currentMarker.setText("Current Turn: Sabres");
			}
			else{
				currentMarker.setText("Current Turn: Cav Man");
			}


		}
	}

}