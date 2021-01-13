import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cell {
private int x, y;
private Color color = new Color(50, 250, 150);
private boolean alive;
private int xv, yv;
private int mass = 50;

public Cell() {
	x = 400;
	y = 300;
}

public int getX() {
	return x;
}

public int getY() {
	return y;
}

public void addMass(int a) {
	mass += a;
}

public int getMass() {
	return mass;
}

public void drawCenteredCircle(Graphics g, int x, int y, int r) {
	  x = x-(r/2);
	  y = y-(r/2);
	  g.fillOval(x,y,r,r);
	}

public void paint (Graphics g) {
	g.setColor(color);;
	drawCenteredCircle(g, x , y, mass);
	/* have the enemy object bounce off of the rectangle
	 * borders using the helper methods (getters) for
	 * min x max x etc
	 * same thing for y
	 */
}
}