
import java.awt.Rectangle;

public class CollisionBox {
	// Yuesen He (yh5mq)

	// fields
	private Rectangle rect;

	// methods
	public CollisionBox(Rectangle rect) {
		this.rect = rect;
	}

	public CollisionBox(int x, int y, int width, int height) {
		this.rect = new Rectangle(x, y, width, height);
	}

	public Rectangle getRectangle() {
		return this.rect;
	}

	public boolean collidesWith(CollisionBox other) {
		boolean test = this.rect.intersects(other.rect);
		return test;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public void moveTo(int x, int y) {
		this.rect.setLocation(x, y);
	}

	public void moveCenterTo(int x, int y) {
		this.rect.setLocation(x - (int) this.rect.getWidth() / 2, y
				+ (int) this.rect.getHeight() / 2);
	}

	public boolean isHigherThan(CollisionBox other) {
		boolean test = this.rect.getCenterY() < other.rect.getCenterY();
		return test;
	}

	public boolean isLeftOf(CollisionBox other) {
		boolean test = this.rect.getCenterX() < other.rect.getCenterX();
		return test;
	}

	public int verticalDifference(CollisionBox other) {
		if (this.rect.getMinY() > other.rect.getMinY()
				&& this.rect.getMaxY() < other.rect.getMaxY()) {
			return (int) Math.min(this.rect.getMaxY() - other.rect.getMinY(),
					other.rect.getMaxY() - this.rect.getMinY());

		} else if (other.rect.getMinY() > this.rect.getMinY()
				&& other.rect.getMaxY() < this.rect.getMaxY()) {
			return (int) Math.min(other.rect.getMaxY() - this.rect.getMinY(),
					this.rect.getMaxY() - other.rect.getMinY());

		} else {
			return (int) ((int) Math.min(this.rect.getMaxY(),
					other.rect.getMaxY()) - Math.max(this.rect.getMinY(),
					other.rect.getMinY()));
		}
	}

	public int horizontalDifference(CollisionBox other) {

		if (this.rect.getMinX() > other.rect.getMinX()
				&& this.rect.getMaxX() < other.rect.getMaxX()) {
			return (int) Math.min(this.rect.getMaxX() - other.rect.getMinX(),
					other.rect.getMaxX() - this.rect.getMinX());

		} else if (other.rect.getMinX() > this.rect.getMinX()
				&& other.rect.getMaxX() < this.rect.getMaxX()) {
			return (int) Math.min(other.rect.getMaxX() - this.rect.getMinX(),
					this.rect.getMaxX() - other.rect.getMinX());

		} else {
			return (int) ((int) Math.min(this.rect.getMaxX(),
					other.rect.getMaxX()) - Math.max(this.rect.getMinX(),
					other.rect.getMinX()));
		}
	}

	public boolean isSmallerOverlapVertical(CollisionBox other) {
		boolean test = (verticalDifference(other) < horizontalDifference(other));
		return test;
	}
	
}
