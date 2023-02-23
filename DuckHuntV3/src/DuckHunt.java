import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DuckHunt extends JPanel implements ActionListener {

	private ImageIcon imgBackground, imgForeground, imgCursor;
	private Cursor cursor;
	private int score, hits;
	private double hit = 1, tshot = 1;
	private double accuracy;
	private DecimalFormat df;
	private Font f;
	private dog d;
	private duck[] du;
	private int ydir = -10;
	private Random rnd;
	private int loc;
	private int dir = 10;
	private boolean dead = false;
	private boolean dead1 = false;

	private static final int PANEL_WIDTH = 640;
	private static final int PANEL_HEIGHT = 480;
	private boolean start = false;
	private Timer dog;
	private Timer duck;

	public static void main(String[] args) {

		new DuckHunt();
	}

	public DuckHunt() {
		rnd = new Random();
		int t = rnd.nextInt(6) + 1;
//    if (t > 3) {
//    	loc =  (int)(Math.random() * 40 + 2);
//    }
//    else {
//    	loc = PANEL_WIDTH;
//    }
//    	

		d = new dog();
		d.setpostion(0, 295);

		du = new duck[2];
		for (int index = 0; index < du.length; index++) {
			// if(loc == PANEL_WIDTH ) {
			// dir = -10;
			// du[index].setimage(new ImageIcon ("images/duckWest.gif"));
			// }

			du[0] = new duck();
			du[0].setxdirection((int) (Math.random() * 20 + 2));
			du[0].setyDirection((int) (Math.random() * 24 + 2));
			du[1] = new duck();
			du[1].setyDirection((int) (Math.random() * 10 + 2));
			du[1].setxdirection((int) (Math.random() * 14 + 2));
		}

		df = new DecimalFormat("#%");
		f = new Font("Neuropol", Font.BOLD, 18);
		dog = new Timer(100, this);

		imgBackground = new ImageIcon("images\\background.png");
		imgForeground = new ImageIcon("images\\foreground.png");

		imgCursor = new ImageIcon("images\\cursor.png");

		cursor = Toolkit.getDefaultToolkit().createCustomCursor(imgCursor.getImage(),
				new Point(imgCursor.getIconWidth() / 2, imgCursor.getIconHeight() / 2), "");
		this.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					dog.start();
					start = true;
				}
				repaint();
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}

		});
		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {

				if ((d.getBounds().contains(arg0.getPoint()))) {
					d.shootdog();
					score -= 10;
					tshot += 1;
				}

				else if ((du[0].getBounds().contains(arg0.getPoint()))) {
					du[0].killduck();
					dead = true;
					du[0].setxdirection(0);
					du[0].setyDirection(12);
					score += 10;
					hits += 1;
					tshot += 1;
					hit += 1
				} else if ((du[1].getBounds().contains(arg0.getPoint()))) {
					du[1].killduck();
					du[1].killduck();
					dead1 = true;
					du[1].setxdirection(0);
					du[1].setyDirection(12);
					score += 10;
					hits += 1;
					tshot += 1;
					hit += 1;
					
				}
				else {
					tshot += 1;
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		setLayout(null);
		setCursor(cursor);
		setFocusable(true);
		requestFocus();

		JFrame frame = new JFrame();
		frame.setContentPane(this);
		frame.setTitle("Duck Hunt © Nintendo 1985");
		frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(false);
		frame.setVisible(true);
		while (true) {
			accuracy = hit / tshot;
		}
		}
	
	

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Font f = null;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File("Pokemon Solid.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(f);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		g2.setFont(new Font("Pokemon Solid", Font.BOLD, 10));
		g2.drawImage(imgBackground.getImage(), 0, 0, this);
		g2.setFont(f);
		g2.setColor(new Color(128, 208, 16));

		g2.drawImage(imgForeground.getImage(), 0, 0, this);

		g2.drawString("   CLICK SPACEBAR TO BEGIN " + "", 160, PANEL_HEIGHT - 50);

		if (start == true) {

			g2.drawImage(imgBackground.getImage(), 0, 0, this);
			g2.setFont(f);
			g2.setColor(new Color(128, 208, 16));
			if (d.isshot())
				d.drawdog(g2);
			g2.drawImage(imgForeground.getImage(), 0, 0, this);
			g2.drawString("SCORE:  " + score, 20, PANEL_HEIGHT - 50);
			g2.drawString("HITS:  " + hits, 250, PANEL_HEIGHT - 50);
			g2.drawString("ACCURACY:  " + df.format(accuracy), 420, PANEL_HEIGHT - 50);

			for (int index = 0; index < du.length; index++) {
				du[index].draw(g2);

			}

		}

		if (!d.isshot())
			d.drawdog(g2);

	}

	public void actionPerformed(ActionEvent e) {
		// System.out.println(d.dogtimer());

		if (d.isshot() == false)

			d.move();
		if (d.getx() >= this.getWidth()) {
			d.setx(this.getWidth() - d.getwidth());
			d.setimage(new ImageIcon("images/dogWest.gif"));
			d.setxdirection(-d.getxDirection());
		}
		if (d.getx() <= 0) {
			d.setx(0);
			d.setimage(new ImageIcon("images/dogEast.gif"));
			d.setxdirection(-d.getxDirection());
		}

		for (int index = 0; index < du.length; index++) {
			du[index].move();
			

		}
		// If the duck hits the right wall
					if (du[0].getx() + du[0].getwidth() >= this.getWidth()) {
						du[0].setx(this.getWidth() - du[0].getwidth());
						du[0].setxdirection(-du[0].getxDirection());
						du[0].reset2();
					}
					// If the duck hits the left wall
					if (du[0].getx() <= 0) {
						du[0].setx(0);
						du[0].setxdirection(-du[0].getxDirection());
						du[0].reset();
					}
					if (du[1].getx() + du[1].getwidth() >= this.getWidth()) {
						du[1].setx(this.getWidth() - du[1].getwidth());
						du[1].setxdirection(-du[1].getxDirection());
						du[1].reset2();
					}
					// If the duck hits the left wall
					if (du[1].getx() <= 0) {
						du[1].setx(0);
						du[1].setxdirection(-du[1].getxDirection());
						du[1].reset();
					}

					
		// If the ball hits the bottom wall
		if (dead == true) {
			if (du[0].gety() + du[0].getHeight() >= 600) {
				du[0].sety(600 - du[0].getHeight());
				du[0].setyDirection(-du[0].getyDirection());
				du[0].setpostion(0, 0);
				dead = false;
			}
		} else if (du[0].gety() + du[0].getHeight() >= 295) {
			du[0].sety(295 - du[0].getHeight());
			du[0].setyDirection(-du[0].getyDirection());
		}
		if (dead1 == true) {
			if (du[1].gety() + du[1].getHeight() >= 600) {

				du[1].sety(600 - du[1].getHeight());
				du[1].setyDirection(-du[1].getyDirection());
				du[1].setpostion(640, 0);
				dead1 = false;
			}
		} else if (du[1].gety() + du[1].getHeight() >= 295) {
			du[1].sety(295 - du[1].getHeight());
			du[1].setyDirection(-du[1].getyDirection());
		}

		// If the ball hits the top wall
		if (du[0].gety() <= 0) {
			du[0].sety(0);
			du[0].setyDirection(-du[0].getyDirection());

		}
		if (du[1].gety() <= 0) {
			du[1].sety(0);
			du[1].setyDirection(-du[1].getyDirection());

		}

		repaint();
	}

}
