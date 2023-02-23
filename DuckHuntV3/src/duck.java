import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;

public class duck {

	boolean dead = true;
	private ImageIcon imgduck, imgfallingduck;
	private Random rnd;
	private int xpos, ypos, xdir, ydir, dir, direction, width, height, seconds;
	private Timer td;
	private duck du;


	public duck() {
		direction = 0;
		xpos = 5;
		ypos = 30;
		imgduck = new ImageIcon("images/duckEast.gif ");
		height = imgduck.getIconWidth();
		width = imgduck.getIconHeight();
	}

	public duck(int x, int y, int dir) {

	}

	public duck(int panelWidth, int panelHeight) {

	}
	public void reset() {
		imgduck.setImage(new ImageIcon("images/duckEast.gif").getImage());
		xdir = +10;
	}
	public void reset2() {
		imgduck.setImage(new ImageIcon("images/duckWest.gif").getImage());
		xdir = -10;
	}

	

	public void draw(Graphics2D g2) {
		g2.drawImage(imgduck.getImage(), xpos, ypos, width, height, null);
	}

	public void moveduck() {
		ypos += ydir;
		xpos += xdir;

	}

	public int getHeight() {
		return height;
	}

	public int getwidth() {
		return width;
	}

	public int getx() {
		return xpos;
	}

	public int gety() {
		return ypos;

	}

	public boolean isdead() {
		return dead;

	}

	public void move() {
		xpos += xdir;
		ypos += ydir;

	}

	public void setxdirection(int xd) {
		xdir = xd;

	}

	public void move(int xpixels, int ypixels) {
		xdir = xpixels;
		ydir = ypixels;
		xpos += xdir;
		ypos += ydir;
	}

	public void setyDirection(int yd) {
		ydir = yd;
	}

	public void setpostion(int x, int y) {
		xpos = x;
		ypos = y;
	}

	public void setimage(ImageIcon img) {
		imgduck = img;
	}

	public void setx(int x) {
		xpos = x;
	}

	public void sety(int y) {
		ypos = y;
	}

	public void killduck() {
		imgduck.setImage(new ImageIcon("images/fallingDuck.png").getImage());

		System.out.println("aaa");

	}

	public void setImgicon(ImageIcon imageIcon) {

	}

	public int getxDirection() {

		return xdir;
	}

	public int getyDirection() {
		return ydir;

	}


	public Rectangle getBounds() {
		return new Rectangle((int) xpos, (int) ypos, (int) width, (int) height);
	}

}
