import java.awt.Font;
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
	ArrayList<Food> food = new ArrayList<Food>();
	Cell Player = new Cell();
	Font font = new Font("Arial", Font.BOLD, 35);

	public void paint (Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
		g.drawString("Remaining Enemies: " + enemies.size(), 10, 50);
	
		eat();
		for (Enemy e: enemies) {
			e.paint(g);
			e.collide();
		}
		for (Food f: food) {
			f.paint(g);
		}
		Player.paint(g);
		Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	}
	
	public static void main (String[] args) {
		Driver d = new Driver();
		}
	
	public Driver() {
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		for (int i = 0; i < 20; i++) {
			enemies.add(new Enemy());
		}
		for (int i = 0; i < 20; i++) {
			food.add(new Food());
		}
		Timer t = new Timer(16, this);
		t.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public int getRadius (Enemy ez) {
		return 5;
	}
	
	public void eat() {
		for (int i = 0; i < enemies.size(); i++) {
		if ((enemies.get(i)).getX() >= Player.getX()-Player.getMass()/2 && enemies.get(i).getX() <= Player.getX() + Player.getMass()/2 && (enemies.get(i)).getY() >= Player.getY() - Player.getMass()/2 && enemies.get(i).getY() <= Player.getY() + Player.getMass()/2) {
			Player.addMass((int)(enemies.get(i).getRad())/2);
			enemies.remove(i);
			i--;
			System.out.println(Player.getMass());
		}
		}
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