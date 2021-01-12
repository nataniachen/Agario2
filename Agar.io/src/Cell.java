import java.awt.Color;
import java.awt.Graphics;

public class Cell {
private int x, y;
private Color color;
private boolean alive;
private int xv, yv;
private int mass;

public Cell() {
	x = 400;
	y = 300;
}

public void paint (Graphics g) {
	g.setColor(color);;
	g.fillOval(x,  y,  50,  50);
	/* have the enemy object bounce off of the rectangle
	 * borders using the helper methods (getters) for
	 * min x max x etc
	 * same thing for y
	 */
}
}