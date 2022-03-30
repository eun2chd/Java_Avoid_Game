package avoidGameProject;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Rock {
	
	ImageIcon rockImg = new ImageIcon("images/ball.png");
	
	Image rock_Img = rockImg.getImage();
	Image rock_change = rock_Img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
	int x,y;
	
	int power = 10;
	
	
	private int rockWidth =  30;
	private int rockHeight = 30;
	
	
	public Rock(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void move() {
		this.x -= 7;		
	}
	public void moveSpeed_Up() {
		this.x -= 12;		
	}

	

	
	

	
	
	
	
}
