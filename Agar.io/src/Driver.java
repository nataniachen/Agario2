import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
public class Driver extends JPanel implements MouseListener, ActionListener{
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	Cell Player = new Cell();
	
	public void paint (Graphics g) {
		g.fillOval(50,  50, 100, 200);
		g.drawString("Mass", 100, 300);
		super.paintComponent(g);
		Player.paint(g);
		for (Enemy e: enemies) {
			e.paint(g);
			e.collide();
		}
		Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	}
	
	public static void main (String[] args) {
		Driver d = new Driver();
		}
	
	public Driver() {
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		for (int i = 0; i < 10; i++) {
			enemies.add(new Enemy());
		}
		Timer t = new Timer(16, this);
		t.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public int getRadius (Enemy ez) {
		return 5;
	}
	//test
	//if (follow) {
		//vx = (int) (MouseInfo.getPointerInfo().getLocation().getX());
		//vy = (int) (MouseInfo.getPointerInfo().getLocation().getY());
		//System.out.println(vx + "" + vy);
	//}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void actionPerformed (ActionEvent arg0) {
		repaint(); //timer will invoke this method which then refreshes the screen for the animation
	}
}