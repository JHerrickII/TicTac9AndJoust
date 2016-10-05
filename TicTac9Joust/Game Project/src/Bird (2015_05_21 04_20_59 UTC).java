import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Bird {
//Yuesen He (yh5mq)	
	/// imgs: default storage for the pictures of the bird
	private BufferedImage[] imgs;
	// TODO: add your own fields here

	
	/**
	 * Creates a bird object with the given image set 
	 * @param basename should be "birdg" or "birdr" (assuming you use the provided images)
	 */
	public Bird(String basename) {
		// You may change this method if you wish, including adding 
		// parameters if you want; however, the existing image code works as is.
		this.imgs = new BufferedImage[6];
		try {
			// 0-2: right-facing (folded, back, and forward wings)
			this.imgs[0] = ImageIO.read(new File(basename+".png"));  
			this.imgs[1] = ImageIO.read(new File(basename+"f.png"));
			this.imgs[2] = ImageIO.read(new File(basename+"b.png"));
			// 3-5: left-facing (folded, back, and forward wings)
			this.imgs[3] = Bird.makeFlipped(this.imgs[0]);
			this.imgs[4] = Bird.makeFlipped(this.imgs[1]);
			this.imgs[5] = Bird.makeFlipped(this.imgs[2]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}
	
	
	/**
	 * A helper method for flipping in image left-to-right into a mirror image.
	 * There is no reason to change this method.
	 * 
	 * @param original The image to flip
	 * @return A left-right mirrored copy of the original image
	 */
	private static BufferedImage makeFlipped(BufferedImage original) {
		AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
		af.translate(-original.getWidth(), 0);
		BufferedImage ans = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
		Graphics2D g = (Graphics2D)ans.getGraphics();
		g.drawImage(original, af, null);
		return ans;
	}
	
	
	
	/**
	 * Draws this bird
	 * @param g the paintbrush to use for the drawing
	 */
	public void draw(Graphics g, double x, double y, int i) {
		
		//int i = 0; // between 0 and 6, depending on facing and wing state
		//x = 400; // where to center the picture 
		//y = 300;
		
		// TODO: find the right x, y, and i instead of the examples given here
		
		g.drawImage(this.imgs[i], (int)x-this.imgs[i].getWidth()/2, (int)y-this.imgs[i].getHeight()/2, null);
	}
}
