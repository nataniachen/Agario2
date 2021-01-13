import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	private int x, y;
	private int vx, vy = 0;
	private int rad = (int)(Math.random()*50+1);
	private int mass;
	private Color color;
	Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	int i;
	
	public Enemy() {
		//enemy moves randomly because vx and vy are some random nonzero value between -3 and 3
		while (vx == 0) {
			i = (int)(Math.random()*6)+1;
			if (i >= 4) {
				vx = (int)(.5*(i-(int)(rad/13)));
				//^ bigger it is, the slower it is
			}
			else if (i <4) {
				vx = (int)(.5*(-i+(int)(rad/17)));
			}
		}
		while (vy == 0) {
			i = (int)(Math.random()*6)+1;
			if (i >= 4) {
				vy = -i+(int)(rad/13);
				//^ bigger it is, the slower it is
			}
			else if (i <4) {
				vy = -i+(int)(rad/17);
			}
		}
		//spawn enemy randomly
		x = (int)(Math.random()*800);
		y = (int)(Math.random()*600);
		
		//random color
		int red = colour(20);
		int green = colour(20);
		int blue = colour(256);
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
		if (x<= 0 || x >= 600) {
			vx = -vx;
		}
		if (y<= 0 || y >= 800) {
			vy = -vy;
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getRad() {
		return rad;
	}
	
	public void update() {
		x+=vx;
		y+=vy;
	}
}