import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

import java.awt.*;

import javax.swing.Timer;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class GameCourt extends JPanel{
	
	private Penguin penguin;
	private Austin person;
	private Set<Integer> keys;
	
	public boolean playing = false;
	public boolean mode = false;
	public boolean mode2 = false;
	
	private JLabel status;
	private JLabel scoreA;
	private JLabel scoreP;
	
	private int aus_score;
	private int peng_score;
	
	public static final int COURT_WIDTH = 1000;
	public static final int COURT_HEIGHT = 500;
	public static final int PENGUIN_VELOCITY = 6;
	public static final int AUSTIN_VELOCITY = 6;

	
	public static final int INTERVAL = 35;
	
	public GameCourt(JLabel status, JLabel statusA, JLabel statusP) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
		keys = new TreeSet<Integer>();
		
		Timer timer = new Timer(INTERVAL, new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
		setFocusable(true);
		this.status = status;
		this.scoreA = statusA;
		this.scoreP = statusP;
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyPressed = e.getKeyCode();
				if(!keys.contains(keyPressed));{
					keys.add(keyPressed);
				}
			}
			
			public void keyReleased(KeyEvent e) {
				int keyReleased = e.getKeyCode();
				keys.remove(keyReleased);
//					if (!keys.contains(KeyEvent.VK_A) && !keys.contains(KeyEvent.VK_D)){
//						penguin.v_x = 0;
//					}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_DOWN) {
					person.v_x = 0;
					person.v_y = 0;
				}
				else {
					penguin.v_x = 0;
					penguin.v_y = 0;
				}
			}
		});
	}
			
	
	void tick(){
		if (keys.contains(KeyEvent.VK_A)) {
			penguin.v_x = -PENGUIN_VELOCITY;
			mode = false;
			mode2 = false;
		}
		if (keys.contains(KeyEvent.VK_D)) {
			penguin.v_x = PENGUIN_VELOCITY;
			mode = false;
			mode2 = false;
		}
		if (keys.contains(KeyEvent.VK_W)) {
			penguin.v_x = 0;
			mode = true;
			mode2 = false;
		}
		if (keys.contains(KeyEvent.VK_LEFT )){
			person.v_x = -AUSTIN_VELOCITY;
			mode = false;
			mode2 = false;
		}
		if (keys.contains(KeyEvent.VK_RIGHT)) {
			person.v_x = AUSTIN_VELOCITY;
			mode = false;
			mode2 = false;
		}
		if (keys.contains(KeyEvent.VK_UP)) {
			person.v_x = 0;
			mode = false;
			mode2 = true;
		}
		if(playing) {
			if(mode && penguin.intersects(person)) {
				peng_score++;
				scoreP.setText("Score: " + peng_score);
				person.pos_x += 10;
				penguin.pos_x -= 10;
			}
			if(mode2 && penguin.intersects(person)) {
				aus_score++;
				scoreA.setText("Score: " + aus_score);
				person.pos_x += 10;
				penguin.pos_x -= 10;
			}
			if(aus_score == 100) {
				playing = false;
				status.setText("AUSTIN WINS");
			}
			if(peng_score == 100) {
				playing = false;
				status.setText("PENGUIN WINS");
			}
			penguin.move();
			person.move();
			repaint();
		}
	}

	public void reset() {
		penguin = new Penguin(COURT_WIDTH, COURT_HEIGHT);
		person = new Austin(COURT_WIDTH, COURT_HEIGHT);
		playing = true;
		aus_score = 0;
		peng_score = 0;
		scoreA.setText("Score " + aus_score);
		scoreP.setText("Score " + peng_score);
		status.setText("FIGHT");
		requestFocusInWindow();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		penguin.draw(mode,g);
		person.draw(mode2,g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
	

}
