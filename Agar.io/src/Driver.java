import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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
	World world = new World();
	Font font = new Font("Arial", Font.BOLD, 30);
	Point mouse = MouseInfo.getPointerInfo().getLocation();
	double mX = MouseInfo.getPointerInfo().getLocation().getX();
	double mY = MouseInfo.getPointerInfo().getLocation().getY();
	double pX = mX+Player.getMass()/2;
	double pY = mY+Player.getMass()/2;
	double playerVelocity = 150/Player.getMass()+1;
	Color c = new Color(100, 200, 100);
			
	public void paint (Graphics g) {
		if (Player.living()) {

		//updating mouse location
		mX = MouseInfo.getPointerInfo().getLocation().getX();
		mY = MouseInfo.getPointerInfo().getLocation().getY();
		
		//updating player velocity
		playerVelocity = 150/Player.getMass()+1;
		
		super.paintComponent(g);
		
		//eating
		eat();
		eatPlayer();
		
		for (Enemy e: enemies) {
			if (mX > 400) {
				e.updateX(-playerVelocity);
			}
			if (mX < 400) {
				e.updateX(playerVelocity);
			}
			if (mY> 300) {
				e.updateY(-playerVelocity);
			}
			if (mY< 300) {
				e.updateY(playerVelocity);
			}
			e.paint(g);
			if (e.getX()<= world.getRx() || e.getX() >= world.getRx()+2000) {
				e.updateVx(-e.getVx());
			}
			if (e.getY()<= world.getRy() || e.getY() >= world.getRy()+2000) {
				e.updateVy(-e.getVy());
			}
		}
		for (Food f: food) {
			if (mX > 400) {
				f.updateX(-playerVelocity);
			}
			if (mX < 400) {
				f.updateX(playerVelocity);
			}
			if (mY> 300) {
				f.updateY(-playerVelocity);
			}
			if (mY< 300) {
				f.updateY(playerVelocity);
			}
			f.paint(g);
		}
		//status
		g.setColor(c);
		g.setFont(font);
		g.drawString("Remaining Enemies: " + enemies.size(), 15, 40);
		g.drawString("Remaining Food: " + food.size(), 15, 70);
		
		//draw player
		if (Player.living()) {
		Player.paint(g);
		}
		else {
			g.drawString("You've lost!", 300, 275);
		}
		
		//rectangle
		if (mX > 400) {
			world.updateX(-playerVelocity);
		}
		if (mX < 400) {
			world.updateX(playerVelocity);
		}
		if (mY> 300) {
			world.updateY(-playerVelocity);
		}
		if (mY< 300) {
			world.updateY(playerVelocity);
		}
		world.paint(g);
		}
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
		for (int i = 0; i < 40; i++) {
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
		if ((enemies.get(i)).getX() >= Player.getX()-Player.getMass()/2 && enemies.get(i).getX() <= Player.getX() + Player.getMass()/2 && (enemies.get(i)).getY() >= Player.getY() - Player.getMass()/2 && enemies.get(i).getY() <= Player.getY() + Player.getMass()/2 && Player.getMass() >= enemies.get(i).getRad()) {
			Player.addMass((int)(enemies.get(i).getRad())/5);
			enemies.remove(i);
			i--;
			System.out.println(Player.getMass());
		}
		}
		for (int i = 0; i < food.size(); i++) {
			if ((food.get(i)).getX() >= Player.getX()-Player.getMass()/2 && food.get(i).getX() <= Player.getX() + Player.getMass()/2 && (food.get(i)).getY() >= Player.getY() - Player.getMass()/2 && food.get(i).getY() <= Player.getY() + Player.getMass()/2) {
				Player.addMass(food.get(i).getMass()/3);
				food.remove(i);
				i--;
				System.out.println(Player.getMass());
			}
			}
	}
	
	public void eatPlayer() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getX() < Player.getX()+Player.getMass()/2 && enemies.get(i).getX()+enemies.get(i).getRad() > Player.getX()+Player.getMass()*(3/2) && enemies.get(i).getY() < Player.getY()+Player.getMass()/2 && enemies.get(i).getY()+enemies.get(i).getRad() > Player.getY()+Player.getMass()*(3/2) && Player.getMass() < enemies.get(i).getRad()) {
				Player.setLiving(false);
			}
		}
	}

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