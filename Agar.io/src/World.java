import java.awt.Graphics;

public class World {
	private int rx, ry = 0;
	private double vx, vy;
	/*
	 * public void updateVx(int vx) {
		this.vx = vx;
	}
	 */

	public World() {
		
	}
	
	public void paint (Graphics g) {
		g.drawRect(rx,  ry,  2000,  2000);
	}
	
	public void updateX(double value) {
		vx = value;
		rx += vx;
	}
	
	public void updateY(double value) {
		vy = value;
		ry += vy;
	}
	
	public int getRx() {
		return rx;
	}
	
	public int getRy() {
		return ry;
	}
}