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
	boolean win;
	Font font = new Font("Arial", Font.BOLD, 15);
	Point mouse = MouseInfo.getPointerInfo().getLocation();
	double mX = MouseInfo.getPointerInfo().getLocation().getX();
	double mY = MouseInfo.getPointerInfo().getLocation().getY();
	double playerVelocity = 150/Player.getMass()+2;
	Color c = new Color(100, 200, 100);
	int sum = 0;
			
	public void paint (Graphics g) {
		g.setColor(c);
		if (Player.living() && insideRect() && !win) {

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
			if (mX > 500) {
				e.updateX(-playerVelocity);
			}
			if (mX < 500) {
				e.updateX(playerVelocity);
			}
			if (mY> 400) {
				e.updateY(-playerVelocity);
			}
			if (mY< 400) {
				e.updateY(playerVelocity);
			}
			e.paint(g);
			if (e.getX()<= world.getRx() || e.getX() + e.getDiam() >= world.getRx()+2000) {
				e.updateVx(-e.getVx());
			}
			if (e.getY()<= world.getRy() || e.getY() + e.getDiam() >= world.getRy()+2000) {
				e.updateVy(-e.getVy());
			}
		}
		for (Food f: food) {
			if (mX > 500) {
				f.updateX(-playerVelocity);
			}
			if (mX < 500) {
				f.updateX(playerVelocity);
			}
			if (mY> 400) {
				f.updateY(-playerVelocity);
			}
			if (mY< 400) {
				f.updateY(playerVelocity);
			}
			f.paint(g);
		}
		
		//status
		if (sum < 250) {
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Avoid being eaten, and avoid the borders!", 190, 50);
			sum++;
		}
		g.setFont(font);
		g.drawString("Remaining Enemies: " + enemies.size(), 790, 720);
		g.drawString("Remaining Food: " + food.size(), 790, 740);
		
		//draw player
		if (Player.living()) {
		Player.paint(g);
		}
		
		//rectangle
		if (mX > 500) {
			world.updateX(-playerVelocity);
		}
		if (mX < 500) {
			world.updateX(playerVelocity);
		}
		if (mY> 400) {
			world.updateY(-playerVelocity);
		}
		if (mY< 400) {
			world.updateY(playerVelocity);
		}
		world.paint(g);
		}
		else if (!win){
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("You've lost!", 340, 390);
		}
		else {
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("You've won!", 360, 390);
		}
	}
	
	public static void main (String[] args) {
		Driver d = new Driver();
		}
	
	public Driver() {
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(1000, 800);
		frame.add(this);
		for (int i = 0; i < 20; i++) {
			enemies.add(new Enemy());
		}
		for (int i = 0; i < 75; i++) {
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
		if ((enemies.get(i)).getX() >= Player.getAccurateX() && enemies.get(i).getX() <= Player.getAccurateX() + Player.getMass() && (enemies.get(i)).getY() >= Player.getAccurateY() && enemies.get(i).getY() <= Player.getAccurateY() + Player.getMass() && Player.getMass() >= enemies.get(i).getDiam()) {
			Player.addMass((int)(enemies.get(i).getDiam())/5);
			enemies.remove(i);
			i--;
			System.out.println(Player.getMass());
		}
		if (enemies.size() == 0) {
			win = true;
		}
		}
		for (int i = 0; i < food.size(); i++) {
			if ((food.get(i)).getX() >= Player.getAccurateX() && food.get(i).getX() <= Player.getAccurateX() + Player.getMass() && (food.get(i)).getY() >= Player.getAccurateY() && food.get(i).getY() <= Player.getAccurateY() + Player.getMass()) {
				Player.addMass(food.get(i).getMass()/3);
				food.remove(i);
				i--;
				System.out.println(Player.getMass());
			}
			}
	}
	
	public void eatPlayer() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getX() < Player.getAccurateX() && enemies.get(i).getX()+enemies.get(i).getDiam() > Player.getAccurateX()+Player.getMass() && enemies.get(i).getY() < Player.getAccurateY() && enemies.get(i).getY()+enemies.get(i).getDiam() > Player.getAccurateY()+Player.getMass() && Player.getMass() < enemies.get(i).getDiam()) {
				Player.setLiving(false);
			}
		}
	}
	
	//ensuring the player does not go out of bounds
	public boolean insideRect() {
		if (Player.getAccurateX() >= world.getRx() && Player.getAccurateX()+Player.getMass() <= world.getRx()+2000 && Player.getAccurateY() >= world.getRy() && Player.getAccurateY()+Player.getMass() <= world.getRy()+2000) {
			return true;
		}
		return false;
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