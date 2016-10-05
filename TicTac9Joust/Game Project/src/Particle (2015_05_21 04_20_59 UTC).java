
import java.awt.Rectangle;

//Yuesen He (yh5mq)
public class Particle {
	// Fields
	private double x;
	private double y;
	private double vx;
	private double vy;
	private double mass;
	private CollisionBox b;
	private CollisionBox m;

	// Methods
	public Particle(double x, double y) {
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
		this.mass = 1;
		this.b = new CollisionBox(this.getIntX() - 40, this.getIntY(), 40, 20);
		this.m = new CollisionBox(this.getIntX() - 20, this.getIntY() - 30, 20,
				30);
	}

	public Particle(double x, double y, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.mass = 1;
	}

	public Particle(double x, double y, double vx, double vy, double mass) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.mass = mass;
	}

	public void move(double x, double y) {
		this.vx = 0;
		this.vy = 0;
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public int getIntX() {
		return (int) this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public int getIntY() {
		return (int) this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void applyForce(double fx, double fy, double dt) {
		this.vx = this.vx + fx / this.mass * dt;
		this.vy = this.vy + fy / this.mass * dt;
	}

	public void timePasses(double dt) {
		this.x = this.x + vx * dt;
		this.y = this.y + vy * dt;
	}

	public double getSpeed() {
		return Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
	}

	public void applyDrag(double drag, double dt) {
		// this.vx = this.vx - this.vx * this.getSpeed() * drag * dt /
		// this.mass;
		// this.vy = this.vy - this.vy * this.getSpeed() * drag * dt /
		// this.mass;
		this.applyForce(-this.vx * this.getSpeed() * drag,
				-this.vy * this.getSpeed() * drag, dt);
	}

	public void bounceIfOutsideOf(Rectangle r, double bounciness) {
		double vxPositive = 0;
		double vxNegative = 0;
		double vyPositive = 0;
		double vyNegative = 0;
		if (vx * bounciness > 0) {
			vxPositive = vx * bounciness;
			vxNegative = -vx * bounciness;
		} else {
			vxPositive = -vx * bounciness;
			vxNegative = vx * bounciness;
		}

		if (vy * bounciness > 0) {
			vyPositive = vy * bounciness;
			vyNegative = -vy * bounciness;
		} else {
			vyPositive = -vy * bounciness;
			vyNegative = vy * bounciness;
		}

		if (this.x <= r.getMinX()) {
			this.x = r.getMinX();
			this.vx = vxPositive;
		}
		if (this.x >= r.getMaxX()) {
			this.x = r.getMaxX();
			this.vx = vxNegative;
		}

		if (this.y >= r.getMaxY()) {
			this.y = r.getMaxY();
			this.vy = vyNegative;
		}

		if (this.y <= r.getMinY()) {
			this.y = r.getMinY();
			this.vy = vyPositive;
		}
	}

	public String toString() {
		return "Particle at <" + this.x + "," + this.y + "> with velocity <"
				+ this.vx + "," + this.vy + "> and mass " + this.mass;
	}

	public CollisionBox getB() {
		return b;
	}

	public void bounce(CollisionBox other, double bounciness) {
		if (this.getB().getRectangle().intersects(other.getRectangle())) {
			Rectangle rect = this.getB().getRectangle()
					.intersection(other.getRectangle());
			if (rect.getWidth() > rect.getHeight()) {
				if (this.getB().getRectangle().getMinY() == rect.getMinY()) {
					this.y -= rect.getHeight();
					this.vy = -bounciness * Math.abs(this.vy);
				} else {
					this.y += rect.getHeight();
					this.vy = bounciness * Math.abs(this.vy);
				}
			} else

			if (this.getB().getRectangle().getMaxX() == rect.getMaxX()) {
				this.x -= rect.getWidth();
				this.vx = -bounciness * Math.abs(this.vx);
			} else {
				this.x += rect.getWidth();
				this.vx = bounciness * Math.abs(this.vx);
			}

		}
	}

	public void bounceBox(CollisionBox other, double bounciness) {

		if (this.b.getRectangle().intersects(other.getRectangle()) == true) {
			Rectangle rect = this.b.getRectangle().intersection(
					other.getRectangle());
			if (rect.getMinX() == other.getRectangle().getMinX()
					&& rect.getMinY() != other.getRectangle().getMinY()
					&& rect.getMaxY() != other.getRectangle().getMaxY()) {
				this.x = other.getRectangle().getMinX() - 40;
				this.vx = -vx * bounciness;
			}
			if (rect.getMaxX() == other.getRectangle().getMaxX()
					&& rect.getMinY() != other.getRectangle().getMinY()
					&& rect.getMaxY() != other.getRectangle().getMaxY()) {
				this.x = other.getRectangle().getMaxX() + 40;
				this.vx = -vx * bounciness;
			}

			if (rect.getMinY() == other.getRectangle().getMinY()) {
				this.y = other.getRectangle().getMaxY() - 80;
				this.vy = -vy * bounciness;
			}

			if (rect.getMaxY() == other.getRectangle().getMaxY()) {
				this.y = other.getRectangle().getMaxY() + 40;
				this.vy = -vy * bounciness;
			}

		}
	}

	public CollisionBox getM() {
		return m;
	}

	public void setM(CollisionBox m) {
		this.m = m;
	}

}

