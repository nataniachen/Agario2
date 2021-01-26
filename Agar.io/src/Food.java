import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//read over movement stuff
public class Food {
	private int x, y;
	private double vx, vy = 0;
	private int rad = (int)(Math.random()*10+5);
	private Color color;
	int i;
	
	public Food() {
		vx = 0;
		vy = 0;
		
		//spawn enemy randomly
		x = (int)(Math.random()*2000);
		y = (int)(Math.random()*2000);
		
		//random color
		int red = colour(20);
		int green = colour(100)+156;
		int blue = colour(100)+156;
		color = new Color(red, green, blue);
	}

	public int colour(int b) {
		return (int)(Math.random()*b);
	}
	
	public void paint (Graphics g) {
		g.setColor(color);;
		g.fillOval(x,  y,  rad,  rad);
		/* have the enemy object bounce off of the rectangle
		 * borders using the helper methods (getters) for
		 * min x max x etc
		 * same thing for y
		 */
	}
	
	public int getMass() {
		return rad;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void updateX(double value) {
		vx = value;
		x += vx;
	}
	
	public void updateY(double value) {
		vy = value;
		y += vy;
	}
}