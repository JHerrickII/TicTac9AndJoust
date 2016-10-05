
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
//Yuesen He (yh5mq) John Herrick (jah6vg) Alaz Tenyeri (at9fn)
public class JoustScreen extends KeyAdapter implements ActionListener {
	
	/**
	 * A simple method to make the game runnable. You should not modify this
	 * main method: it should print out a list of extras you added and then say
	 * "new JoustScreen();" -- nothing more than that.
	 */
	/*public static void main(String[] args) {

		// add a list of all extras you did, such as
		// System.out.println("Moving obstacles");
		// System.out.println("Birds leave trails of glowing faerie dust");
		// System.out.println("Press left-right-left-left-down to open a game of Mahjong");
		//System.out.println("Flapping wings");
		//System.out.println("Ending Game after one wins 7");
		
		System.out.println("Moving obstacles");
		System.out.println("Visually flapping wings");
		System.out.println("Scoreboard and Game Over mechanism");
		System.out.println("Bounce off when not clearly above one another");
		new JoustScreen();
	}*/

	// DO NOT CHANGE the next four fields (the window and timer)
	private JFrame window; // the window itself
	private BufferedImage content; // the current game graphics
	private Graphics2D paintbrush; // for drawing things in the window
	private Timer gameTimer; // for keeping track of time passing
	// DO NOT CHANGE the previous four fields (the window and timer)
	private Timer gameTimer2;

	private Particle red;
	private Particle green;
	private Rectangle world;
	private Bird redBird;
	private Bird greenBird;
	private CollisionBox black1;
	private CollisionBox black2;
	private Scoreboard score;
	private int i;
	private int j;
	private String msg;
	private int flap;
	private int movement;
	private int movement2;
	
	private boolean cavWins;
	
	private Board board;

	
	public JoustScreen() {
		// DO NOT CHANGE the window, content, and paintbrush lines below
		this.window = new JFrame("Joust Clone");
		this.content = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		this.paintbrush = (Graphics2D) this.content.getGraphics();
		this.window.setContentPane(new JLabel(new ImageIcon(this.content)));
		this.window.pack();
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.window.addKeyListener(this);
		// DO NOT CHANGE the window, content, and paintbrush lines above

		// TODO: add anything else you might need (e.g., a couple of Bird
		// objects, some walls)
		this.red = new Particle(50, 50);
		this.green = new Particle(750, 50);
		this.redBird = new Bird("birdr");
		this.greenBird = new Bird("birdg");
		this.world = new Rectangle(20, 20, 760, 560);
		this.black1 = new CollisionBox(new Rectangle(100, 200, 200, 50));// Block
																			// 1
		this.black2 = new CollisionBox(new Rectangle(100, 400, 100, 10));// Block
																			// 2
		this.score = new Scoreboard();
		this.msg = "";
		this.flap = 10;
		this.movement = 4;
		this.movement2 = 7;

		// DO NOT CHANGE the next two lines nor add lines after them
		this.gameTimer = new Timer(20, this); // tick at 1000/20 fps
		this.gameTimer.start(); // and start ticking now
		// DO NOT CHANGE the previous two lines nor add lines after them
		this.gameTimer2 = new Timer(20, this);
	}
	
	public boolean getCavWins(){
		return this.cavWins;
	}
	
	public void close(){
		this.close();
	}
	
	public boolean gameOver(){
		if (this.gameTimer.isRunning()){
			return false;
		}
		return true;
	}
	
	public void setBoard(Board t){
		this.board = t;
	}

	/**
	 * This method gets called each time a player presses a key. You can find
	 * out what key the pressed by comparing event.getKeyCode() with
	 * KeyEvent.VK_...
	 */
	public void keyPressed(KeyEvent event) {

		// TODO: handle the keys you want to use to run your game

		if (event.getKeyCode() == KeyEvent.VK_A) { // example
			//System.out.println("The 'a' key was pressed");
			red.applyForce(-800, -1000, 0.5);
			if (red.getB().isLeftOf(green.getB())) {
				if (flap > 0) {
					i = 2;
					this.flap = -10;
				}
			} else {
				if (flap > 0) {
					i = 5;
					this.flap = -10;
				}
			}
		}

		if (event.getKeyCode() == KeyEvent.VK_S) { // example
			//System.out.println("The 's' was pressed");
			red.applyForce(800, -1000, 0.5);
			if (red.getB().isLeftOf(green.getB())) {
				if (flap > 0) {
					i = 1;
					this.flap = -10;
				}
			} else {
				if (flap > 0) {
					i = 4;
					this.flap = -10;
				}
			}
		}

		if (event.getKeyCode() == KeyEvent.VK_K) { // example
			//System.out.println("The 'k' was pressed");
			green.applyForce(-800, -1000, 0.5);
			if (green.getB().isLeftOf(red.getB())) {
				if (flap > 0) {
					j = 1;
					this.flap = -10;
				}
			} else {
				if (flap > 0) {
					j = 4;
					this.flap = -10;
				}
			}

		}

		if (event.getKeyCode() == KeyEvent.VK_L) { // example
			//System.out.println("The 'l' was pressed");
			green.applyForce(800, -1000, 0.5);
			if (green.getB().isLeftOf(red.getB())) {
				if (flap > 0) {
					j = 2;
					this.flap = -10;
				}
			} else {
				if (flap > 0) {
					j = 5;
					this.flap = -10;
				}
			}
		}
	}

	/**
	 * Java will call this every time the gameTimer ticks (50 times a second).
	 * If you want to stop the game, invoke this.gameTimer.stop() in this
	 * method.
	 */
	public void actionPerformed(ActionEvent event) {
		// DO NOT CHANGE the next four lines, and add nothing above them
		if (!this.window.isValid()) { // the "close window" button
			this.gameTimer.stop(); // should stop the timer
			return; // and stop doing anything else
		}
		// DO NOT CHANGE the previous four lines

		// TODO: add every-frame logic in here (gravity, momentum, collisions,
		// etc)
		this.red.getB().setRect(
				new Rectangle(this.red.getIntX() - 20, this.red.getIntY() - 30,
						40, 50));
		this.green.getB().setRect(
				new Rectangle(this.green.getIntX() - 20,
						this.green.getIntY() - 30, 40, 50));
		this.red.getM().setRect(
				new Rectangle(this.red.getIntX() - 5, this.red.getIntY() - 30,
						10, 20));
		this.green.getM().setRect(
				new Rectangle(this.green.getIntX() - 5,
						this.green.getIntY() - 30, 10, 20));

		this.flap++;
		if (flap > 0) {
			if (green.getB().isLeftOf(red.getB())) {
				i = 3;
				j = 0;
			} else {
				i = 0;
				j = 3;
			}
		}
		
		
		
		 if(this.black1.getRectangle().getMaxX() >= world.getMaxX()){
			movement = -4;
			black1.getRectangle().setLocation((int)black1.getRectangle().getX()+movement,(int)black1.getRectangle().getY());
		}
		 if(this.black1.getRectangle().getMinX() <= world.getMinX()){
			movement = 4;
			black1.getRectangle().setLocation((int)black1.getRectangle().getX()+movement,(int)black1.getRectangle().getY());
		}
		 else if(!(this.black1.getRectangle().getMaxX() == world.getMaxX()) && !(this.black1.getRectangle().getMinX() == world.getMinX())){
			//movement = 4;
			
			black1.getRectangle().setLocation((int)black1.getRectangle().getX()+movement,(int)black1.getRectangle().getY());
		}
		 
		 if(this.black2.getRectangle().getMaxX() >= world.getMaxX()){
				movement2 = -7;
				black2.getRectangle().setLocation((int)black2.getRectangle().getX()+movement2,(int)black2.getRectangle().getY());
			}
		 if(this.black2.getRectangle().getMinX() <= world.getMinX()){
				movement2 = 7;
				black2.getRectangle().setLocation((int)black2.getRectangle().getX()+movement2,(int)black2.getRectangle().getY());
			}
		 else if(!(this.black2.getRectangle().getMaxX() == world.getMaxX()) && !(this.black2.getRectangle().getMinX() == world.getMinX())){
				//movement = 4;
				
				black2.getRectangle().setLocation((int)black2.getRectangle().getX()+movement2,(int)black2.getRectangle().getY());
			}
		 
	
		
		this.red.applyForce(0, 1000, 0.02);// Gravity
		this.red.timePasses(0.02);// Time
		this.green.applyForce(0, 1000, 0.02);// Gravity
		this.green.timePasses(0.02);// Time
		this.red.bounceIfOutsideOf(world, 0.5);// Boundary
		this.green.bounceIfOutsideOf(world, 0.5);// Boundary
		this.red.applyDrag(0.01, 0.02);// Drag
		this.green.applyDrag(0.01, 0.02);// Drag

		this.red.bounceBox(black1, 0.2);
		this.green.bounceBox(black1, 0.2);
		this.red.bounceBox(black2, 0.2);
		this.green.bounceBox(black2, 0.2);
		// this.red.bounceBird(green, 0.5);
		// this.green.bounceBird(red, 0.5);

		this.red.bounce(green.getB(), 0.5);
		this.green.bounce(red.getB(), 0.5);

		if (green.getM().getRectangle().intersects(red.getB().getRectangle()) == true) {
			this.score.score1score();
			this.green.move(750, 50);
		}
		if (red.getM().getRectangle().intersects(green.getB().getRectangle()) == true) {
			this.score.score2score();
			this.red.move(50, 50);
		}

		if (this.score.getScore1() > 2
		// && this.score.getScore1() > this.score.getScore2() + 2
		) {
			cavWins = true;
			this.gameTimer.stop();
			this.msg = "Red wins!";
			
			
			
		}
		if (this.score.getScore2() > 2
		// && this.score.getScore2() > this.score.getScore1() + 2
		) {
			cavWins = false;
			this.gameTimer.stop();
			this.msg = "Green wins!";
			
		}

		this.refreshScreen(); // redraws the screen after things move
	}

	/**
	 * Re-draws the screen. You should erase the old image and draw a new one,
	 * but you should not change anything in this method (use actionPerformed
	 * instead if you need something to change).
	 */
	private void refreshScreen() {
		this.paintbrush.setColor(new Color(150, 210, 255)); // pale blue
		this.paintbrush.fillRect(0, 0, this.content.getWidth(),
				this.content.getHeight()); // erases the previous frame

		// TODO: replace the following example code with code that does
		// the right thing (i.e., draw the birds, walls, and score)

		// example bird drawing; replace with something sensible instead of
		// making a new bird every frame
		// new Bird("birdg").draw(this.paintbrush);
		redBird.draw(this.paintbrush, red.getX(), red.getY(), i);
		greenBird.draw(this.paintbrush, green.getX(), green.getY(), j);

		// example wall drawing; replace with something sensible instead of
		// making a new wall every frame
		this.paintbrush.setColor(Color.BLACK);
		this.paintbrush.fill(black1.getRectangle());
		this.paintbrush.fill(black2.getRectangle());

		// example text drawing, for scores and/or other messages
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		this.paintbrush.setFont(f);
		this.paintbrush.setColor(new Color(127, 0, 0)); // dark red
		this.paintbrush.drawString("" + score.getScore1(), 30, 30);
		this.paintbrush.setColor(new Color(0, 127, 0)); // dark green
		this.paintbrush.drawString("" + score.getScore2(), 760, 30);
		// this.msg = "Unimplemented";
		f = new Font(Font.SANS_SERIF, Font.BOLD, 90);
		Rectangle2D r = f.getStringBounds(msg,
				this.paintbrush.getFontRenderContext());
		this.paintbrush.setFont(f);
		this.paintbrush.setColor(Color.BLUE);
		this.paintbrush.drawString(msg, 400 - (int) r.getWidth() / 2, 300);

		this.window.repaint(); // displays the frame to the screen
	}

}

