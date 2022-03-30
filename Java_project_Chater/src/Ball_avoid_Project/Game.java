package Ball_avoid_Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{

	private int delay = 20; // 딜레이
	private long pretime; // 현재시간을 담음
	private int cnt; // cnt 값을 증가시키면서 적플레이어를 생성할예정
	private int score; // 플레이어 점수
	
	ImageIcon player = new ImageIcon("images/among.png");
	
	Image play_img = player.getImage();
	Image play_change = play_img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	
    int playwidth = 80;
	int playheight = 80;
	

	
	
	
	
	private Rock rock;
	
	private int playerX, playerY; // 플레이어 좌표값
	private int playerSpeed = 11; // 플레이어 속도
	private int playerHp = 100; // 플레이어 체력
	
	private boolean up,down,left,right; // 플레이어 방향키
	
	private boolean isover;
	
	// 장애물이 나오는 방향마다 배열을 선언하고 장애물을 저장
	private ArrayList<Rock> rockList = new ArrayList<Rock>(); 

	
	
	@Override
	public void run() {
			
		reset();
		
		while(true) {
			while(!isover) {
				
				pretime = System.currentTimeMillis();
				if(System.currentTimeMillis() - pretime < delay) { // 현재시간 - cnt증가하기전 시간 < delay 일 경우 그차이만큼 쓰레드에 sleep 를 적용
					try {
						Thread.sleep(30);
						keyProcess();
						rockApperProcess();
						rockMoveProcess();
						

						hitAttackProcess();
						
						cnt++;
						score += 50;
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
		
			}
			
			try {
				
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void reset() {
		
		isover = false;
		cnt = 0;
		score = 0;
		playerHp = 100;
		playerX = 300;
		playerY = 300;
		
		rockList.clear();
		
	}
	

	private void keyProcess() {
		
		if(up && playerY - playerSpeed > 15) {
			playerY -= playerSpeed;
		}
		if(down && playerY + playheight + playerSpeed < 495) {
			playerY += playerSpeed;
			
		}
		if(left && playerX - playerSpeed > 0) {
			playerX -= playerSpeed;
		}
		if(right && playerX + playwidth + playerSpeed < 652) {
			playerX += playerSpeed;
		}
		
		
	}
	
	// 게임안의 요소들을 그려줌
	public void gameDraw(Graphics g) {
		playerDraw(g);
		rockDraw(g);
		infoDraw(g);
	}
	
	// 플레이어 그리기
	public void playerDraw(Graphics g) {
	
		g.drawImage(play_change, playerX, playerY, null);		
		g.setColor(Color.GREEN);
		g.fillRect(playerX+10, playerY - 5 , playerHp / 2, 10);

		
	}
	
	// 플레이어가 맞았을때
	private void hitAttackProcess() {
	
		
		for(int i = 0; i < rockList.size(); i++) {
			rock = rockList.get(i);
			if(rock.x > playerX && rock.x < playerX + playwidth && rock.y > playerY && rock.y < playerY + playheight) {
				
				playerHp -= rock.power;
				rockList.remove(rock);
				if(playerHp <= 0) {
					isover = true;
				}
			}
			
		}
		

	}
	
	
	// 장애물 그리기
	public void rockDraw(Graphics g) {
		for(int i = 0; i < rockList.size(); i++) {
			rock = rockList.get(i);
			g.drawImage(rock.rock_change, rock.x, rock.y, null);
		}
	
	
	}
	

	
	
	// 장애물 생성
	public void rockApperProcess() {
		if(cnt % 20 == 0) {
			
			int x = (int)(Math.random() * 720);
			int y = (int)(Math.random() * 500) + 18;
			
			rock = new Rock(x,y);
			rockList.add(rock);
			
		}
		
		if(score > 500) {
			if(cnt % 10 == 0) {
				
				int x = (int)(Math.random() * 720);
				int y = (int)(Math.random() * 500) + 18;
				
				rock = new Rock(x,y);
				rockList.add(rock);
				
			}
		}
		

		
	}
	

		// --------------------------------------------------------------------
		// --------------------------------------------------------------------
		
		
	
	// 장애물 이동
	public void rockMoveProcess() {
		for(int i = 0; i < rockList.size(); i++) {
			rock = rockList.get(i);
				rock.move();
			if(score > 1000) {
				rock.moveSpeed_Up(); 
			}
		
		}
		
	}
	
	
	public void infoDraw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("SCORE : " + score, 10, 50);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("HP : " + playerHp, 150, 50);
		
		if(isover) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 35));
			g.drawString("Game over -> R key to reset", 130,250);
		}
		
		
	}


	

	public void setUp(boolean up) {
		this.up = up;
	}


	public void setDown(boolean down) {
		this.down = down;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isIsover() {
		return isover;
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	

	
	
}

