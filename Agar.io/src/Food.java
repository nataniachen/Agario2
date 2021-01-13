import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food {
	private int x, y;
	private int vx, vy = 0;
	private int rad = (int)(Math.random()*10+1);
	private int mass;
	private Color color;
	Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	int i;
	
	public Food() {
		
		//spawn enemy randomly
		x = (int)(Math.random()*800);
		y = (int)(Math.random()*600);
		
		//random color
		int red = colour(100)+156;
		int green = colour(20);
		int blue = colour(20);
		color = new Color(red, green, blue);
	}

	public int colour(int b) {
		return (int)(Math.random()*b);
	}
	
	public void paint (Graphics g) {
		update();
		g.setColor(color);;
		g.fillOval(x,  y,  rad,  rad);
		/* have the enemy object bounce off of the rectangle
		 * borders using the helper methods (getters) for
		 * min x max x etc
		 * same thing for y
		 */
	}
	
	public void collide() {
		if (x<= 0 || x >= 800) {
			vx = -vx;
		}
		if (y<= 0 || y >=600) {
			vy = -vy;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void update() {
		//make velocities related to radius
		x+=vx;
		y+=vy;
	}
}