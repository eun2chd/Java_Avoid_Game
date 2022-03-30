package aaa;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class testcode3 extends JFrame{
	
	JButton button;
	
	
	
	public testcode3() {

			initData();
			setInitLayout();
	
	}
	
	private void initData() {
		
		setTitle("테스트");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER,1,1));

		
		button = new JButton("테스트 버튼");
		
		
	}
	
	private void setInitLayout() {
		
		setVisible(true);
		setResizable(false);
		
		button.setSize(10,10);
		
		add(button);
	
	}

	public static void main(String[] args) {
		new testcode3();
	}
}
