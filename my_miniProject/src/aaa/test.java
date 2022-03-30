package aaa;

import java.awt.Image;

import javax.swing.ImageIcon;

public class test extends Thread{
	
	long pretime;
	int delay = 20;
	int cnt;
	boolean flag;
	
	
	@Override
	public void run() {

		while(true) {
			
			pretime = System.currentTimeMillis();
			System.out.println(System.currentTimeMillis() - pretime);
			if(System.currentTimeMillis() - pretime < delay) {
				
				try {
					Thread.sleep(delay - System.currentTimeMillis() + pretime);
					System.out.println(delay - System.currentTimeMillis() + pretime);
					cnt++;
					
					System.out.println("cnt °ª : " + cnt);	
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		
			}
			
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis() - pretime);
			flag = System.currentTimeMillis() - pretime < delay;
			System.out.println(flag);
			System.out.println("");
		}
	
	
	}
	

	public static void main(String[] args) {
		
		test mytest = new test();
				
	
		mytest.run();
		
	
	}
}
