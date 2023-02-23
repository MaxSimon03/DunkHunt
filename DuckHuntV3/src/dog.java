import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;


import javax.swing.ImageIcon;
import javax.swing.Timer;

public class dog implements ActionListener{

	private boolean doghit;
	private Timer  dog = new Timer(5000, this);
	
	private int xpos, ypos, xdir, ydir, direction = 1, width, height, seconds;
	private dog d;
	private ImageIcon  imglaughingdog = new ImageIcon ("images/dog.gif") , imgicon;
	public static final int EAST = 0;


// setting the constructor for the dof class
	public dog(){
		
		xdir = 10;
		direction = 0;
		xpos = 10;
		ypos = 30;

		imgicon = new ImageIcon ("images/dogEast.gif");
		height = imgicon.getIconWidth();
		width = imgicon.getIconHeight();	
	}
	//setting the same constructor with different parameters  
	public dog(int x, int y){
		direction = 0;
		xpos = x;
		ypos = y;
		height = 25;
		width = 25;


	}
	//setting the same constructor with different parameters adding direction 
	public dog (int x, int y, int dirc){
		xpos = x;
		ypos = y; 
		direction = dirc;

	}

	
	


    //a method that draws the dog
	public void drawdog(Graphics2D g2){
		g2.drawImage(imgicon.getImage(), xpos, ypos, width, height, null);
		}
	//a method that moves the dog
	public void move (){
		xpos += xdir;
		
	}
	//same method that moves the dog with differnt parameters 
	public void move (int xPixels){
		xdir = xPixels;
		ypos += direction;
	
	
	}
	
	
	
	public void setxdirection(int xd){
		xdir = xd;
		direction *= -1;


	}
	//a method that sets the image of the dog
	public void setimage(ImageIcon img){
		imgicon = img; 

	}
	//a method that sets the position of the dog on the x and y coordinate 
	public void setpostion(int x, int y ){
		xpos = x;
		ypos = y;
	}
	//a method that set the x position 
	public void setx(int x){
    xpos = x;
	}
	//a method that set the y postion
	public void sety(int y){
    ypos = y;
	}
	//the method that shoots the dog and makes him laugh putting him at the coordinate 250 behind the grass 
	public void shootdog (){
		dog.start();
		imgicon = imglaughingdog;
		ypos = 250;
       doghit = true ;
      
}
	//a method that returns the height 
	public int getheight(){
		return height;
	}
	//a method that returned the image 
	public ImageIcon getimage(){
		return imgicon;
	}
	//a method that returns the width 
	public int getwidth(){
		return width;
	}
	//a method that returns the x position 
	public int getx(){
		return xpos;
	}
	//a method that returns the x position 
	public int gety(){
		return xpos;

	}
	// method that checks if the dog is facing east 
	public boolean isfacingeast(){
		if (direction == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	//a method that checks if the dog is facing west 
	public boolean isfacingwest(){
		if (direction == 0){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isshot(){
		return doghit;
	
	}
	public void actionPerformed (ActionEvent e){
   dog.stop();
	 System.out.println("lol");
 ypos = 295;
    doghit = false;
	
   if (direction == 1)
		imgicon = new ImageIcon ("images/dogWest.gif");
	else {
		imgicon = new ImageIcon ("images/dogEast.gif");
	}
	
	}
	
	
	public int getxDirection() {
		
		return xdir ;
	}
	public Rectangle getBounds() {
	
		return new Rectangle((int)xpos, (int)ypos, (int)width, 
				(int)height);
	
	}
	
	public boolean dogtimer () {
		return dog.isRunning();
	}








}	

