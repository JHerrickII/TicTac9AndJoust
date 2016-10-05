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

public class Board extends JFrame {

	//Creates the variables used later
	private Container content;

	//creates image icons for players
	ImageIcon face = new ImageIcon("illustrationCavMan.jpg");
	ImageIcon sabres = new ImageIcon("sabres.jpg");
	ImageIcon gray = new ImageIcon("gray.jpg");

	private boolean noughts;
	private boolean gameOver;
	private String winner = "";

	//creates the button that will update TicTacNine
	private JButton nineButton;

	//Getters and Setters for relevent parts
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public JButton getNineButton(){
		return nineButton;
	}

	public void setNineButton(JButton j){
		this.nineButton = j;
	}
	//end getters and setters

	//creates JButtons
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
	JButton tie = new JButton("");
	JButton exit = new JButton("");

	//creates cell button handlers for cells
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
	ReportButtonHandler init = new ReportButtonHandler();

	//creates arrays for cell button handlers
	CellButtonHandler[] CellHandler = {h1,h2,h3,h4,h5,h6,h7,h8,h9};
	JButton[] JButtons = {j1,j2,j3,j4,j5,j6,j7,j8,j9};

	//constructor for board
	public Board(){
		
		//creates the grid and JFrame
		setTitle("Tic Tac Toe");
		this.setSize(900, 900);
		content = getContentPane();
		content.setLayout(new GridLayout(4,3));
		
		//adds the buttons to the JFrame's grid and action listeners
		int h = 0;
		for(int i = 0; i < JButtons.length; i++){
			add(JButtons[i]);
			JButtons[i].addActionListener(CellHandler[h]);
			h++;
		}

		add(currentMarker);
		add(tie);
		add(exit);


		tie.addActionListener(init);
		tie.setText("RESET");

		exit.addActionListener(close);
		exit.setText("Exit");

		init();
		setVisible(true);
	}

	//sets the default values of the board class
	public void init(){

		//Initialize booleans
		//used random rather than loser based first turn because it makes the game more fair
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
		
		//Sets the text, background, and icon for Board
		for(int i = 0; i < JButtons.length; i++){
			JButtons[i].setText("");
			JButtons[i].setBackground(Color.lightGray);
			JButtons[i].setIcon(gray);
		}
	}

	//Checks for a tie in Board
	public boolean checkTie() {
		if(j1.getText() != "" && j2.getText() != "" && j3.getText() != "" && j4.getText() != "" && j5.getText() != "" && j6.getText() != "" && j7.getText() != "" && j8.getText() != "" && j9.getText() != ""){
			return true;
		}
		return false;
	}

	//Checks for a winner in Board at all possible locations
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

	//Determines the effect of pressing the exit button for board
	private class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
		}
	}

	//determines the effect of hitting the reset button
	private class ReportButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(!gameOver){

				init();
			}
		}
	}

	//Determines the effect of hitting a cell button on board
	private class CellButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//If game over, ignore
			if(gameOver){
				currentMarker.setText("Game Over!");
				return;
			}

			//Get button pressed
			JButton pressed=(JButton)(e.getSource());

			//Get text of button
			String text = pressed.getText();

			//If orange or blue, ignore
			if(text.equals("Orange") || text.equals("Blue")){
				currentMarker.setText("invalid space!");
				return;
			}

			//Add orange/sabers
			if(noughts)
			{
				pressed.setText("Orange");
				pressed.setBackground(Color.ORANGE);
				pressed.setIcon(sabres);
			}
			//add blue/cav man
			else
			{
				pressed.setText("Blue");
				pressed.setBackground(Color.BLUE);
				pressed.setIcon(face);
			}

			//checks if there is a winner and sets the cell on TicTacNine to the appropriate winner
			if(checkWinner()){
				gameOver = true;
				if(noughts){
					nineButton.setText("Orange");
					nineButton.setBackground(Color.ORANGE);
					nineButton.setIcon(sabres);


					setVisible(false);


				}
				else{
					nineButton.setText("Blue");
					nineButton.setBackground(Color.BLUE);
					nineButton.setIcon(face);

					setVisible(false);
				}

			}
			//Checks if there is a tie and initiates Joust if players decide to joust
			else if(checkTie()){
				if (JOptionPane.showConfirmDialog(null, 
						"It's a Tie Game! \nPlay again?", "Tie Match!", JOptionPane.YES_NO_OPTION)
						!=JOptionPane.YES_OPTION) {

					setVisible(false);
					if (JOptionPane.showConfirmDialog(null, 
							"Perhaps a more noble challenge? \nWould you like to JOUST for it?", "It just got real.", JOptionPane.YES_NO_OPTION)
							!=JOptionPane.NO_OPTION) {
						JoustWinner check = new JoustWinner();
						check.setLocation(810, 100);;
						check.setWinner(nineButton);
						new JoustScreen(); 

					}

				}
				init();

			}

			//Change player
			noughts =! noughts;

			if(noughts){
				currentMarker.setText("Current Turn: Sabres");
			}
			else{
				currentMarker.setText("Current Turn: CavMan");
			}


		}
	}
}