import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	private int x, y;
	private int vx, vy = 0;
	private int diam = (int)(Math.random()*150+1);
	private Color color;
	int i;
	
	public Enemy() {
		//enemy moves randomly because vx and vy are some random nonzero value between -3 and 3
		while (vx == 0) {
			i = (int)(Math.random()*6)+1;
			if (i >= 4) {
				vx = (int)(.5*(i-(int)(diam/13)));
				//^ bigger it is, the slower it is
			}
			else if (i <4) {
				vx = (int)(.5*(-i+(int)(diam/17)));
			}
		}
		while (vy == 0) {
			i = (int)(Math.random()*6)+1;
			if (i >= 4) {
				vy = (int)(.5*(-i+(int)(diam/13)));
				//^ bigger it is, the slower it is
			}
			else if (i <4) {
				vy = (int)(.5*(-i+(int)(diam/17)));
			}
		}
		//spawn enemy in random spot that's not where player spawns. gives player 50 room for movement.
		x = (int)(Math.random()*2000-(150+diam)) +150;
		while (x<=475 && x>=325) {
			x = (int)(Math.random()*2000-(150+diam)) +150;
		}
		y = (int)(Math.random()*2000-(150+diam)) +150;
		while (x>= 225 && x<=375) {
			x = (int)(Math.random()*2000-(150+diam)) +150;
		}
		
		//random color
		int red = colour(156)+100;
		int green = colour(20);
		int blue = colour(156)+100;
		color = new Color(red, green, blue);
	}

	public int colour(int b) {
		return (int)(Math.random()*b);
	}
	
	public void paint (Graphics g) {
		update();
		g.setColor(color);;
		g.fillOval(x,  y,  diam,  diam);
		/* have the enemy object bounce off of the rectangle
		 * borders using the helper methods (getters) for
		 * min x max x etc
		 * same thing for y
		 */
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getDiam() {
		return diam;
	}
	
	public void updateX(double value) {
		x+=value;
	}
	
	public void updateY(double value) {
		y+= value;
	}
	
	public void updateVx(int vx) {
		this.vx = vx;
	}
	
	public void updateVy(int vy) {
		this.vy = vy;
	}
	
	public int getVx() {
		return vx;
	}
	
	public int getVy() {
		return vy;
	}
	
	public void update() {
		x+=vx;
		y+=vy;
	}
}