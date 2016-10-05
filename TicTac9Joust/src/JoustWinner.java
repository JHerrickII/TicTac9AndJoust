import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class JoustWinner extends JFrame {

	//Creates variables used later
	private Container content;
	
	//Creates the icons used later
	ImageIcon face = new ImageIcon("illustrationCavMan.jpg");
	ImageIcon sabres = new ImageIcon("sabres.jpg");
	
	//Creates the JButtons displayed
	JButton yes = new JButton("");
	JButton no = new JButton("");
	
	//Creates the JButton that updates the Board class which updates Tic Tac Nine
	JButton winner = new JButton("");

	//Creates Button handlers
	YesButtonHandler yes1 = new YesButtonHandler();
	NoButtonHandler no1 = new NoButtonHandler();

	//Creates the Joust winner pop up screen
	public JoustWinner(){
		
		//creates the JFrame and grid
		setTitle("Who won JOUST?");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		content = getContentPane();
		content.setLayout(new GridLayout(2,1));
		
		//Adds buttons and action listeners
		add(yes);
		yes.addActionListener(yes1);
		
		add(no);
		no.addActionListener(no1);
		
		//Initializes the Board
		init();
		setVisible(true);

	}

	//Relevent Getters and Setters
	public JButton getWinner() {
		return winner;
	}

	public void setWinner(JButton winner) {
		this.winner = winner;
	}

	//Initializes variables for buttons
	public void init(){
		yes.setText("Cav Man");
		no.setText("Sabres");
	}
	
	//Determines the action for the CavMan button
	private class YesButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e){
			
			winner.setText("Blue");
			winner.setBackground(Color.BLUE);
			winner.setIcon(face);
			setVisible(false);
		}

	}
	
	//Determines the action for the sabres button
	private class NoButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e){
			
			winner.setText("Orange");
			winner.setBackground(Color.ORANGE);
			winner.setIcon(sabres);
			setVisible(false);
		}

	}
}