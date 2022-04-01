package Calc_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CalcTest extends JFrame{
	
	JTextField Result; // 계산결과를 사용자 한테 보여주는 값
	
	JPanel buttonsPanel; // 숫자 및 연산 기호를 붙일 패널
	BorderLayout borderLayout;
	String num = ""; // 사용자가 입력한 값을 저장 (ex : 123 입력 num = 123)

	// 배열선언 후 키 배열에 삽입
	String button_Calc[] = {"C", "÷","×","=","7","8","9","+","4","5","6","-","1","2","3","←"," ","0"," "," "};
	JButton button[] = new JButton[button_Calc.length]; // 버튼에 button_calc 안에 들어있는 값들을 하나씩 삽입
	
	private ArrayList<String> Num_Str = new ArrayList<String>(); // 사용자가 연산할 값을 arraylist 에 저장 [1],[+],[1];
	
	
	public CalcTest() {
		
		initData();
		setInitLayout();
	
	}
	
	private void initData() {
		
		setTitle("계산기");
		setSize(300,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		// GUI 상단에 사각형 테두리 생성
		Result = new JTextField();
		Result.setEditable(false); // 사용자가 text를 수정하지 못하게
		Result.setBackground(Color.white);
		Result.setHorizontalAlignment(JTextField.RIGHT);
		Result.setFont(new Font("Arial", Font.BOLD,50));
		Result.setBounds(10,18,265,100); // x,y,w,h
		Result.setText(""); // 빈값으로 초기화
		//-----------------------------------------------
		
		buttonsPanel = new JPanel();
		
		for(int i = 0; i < button_Calc.length; i++) {
			button[i] = new JButton(button_Calc[i]); // button 버튼에 숫자 및 연산기호를 붙임
			button[i].setFont(new Font("Arial", Font.BOLD,30)); 
			
			buttonsPanel.add(button[i]); // 패널에 추가
			button[i].addActionListener(new NumberPad()); // 사용자가 입력한 버튼이 실행되도록
			if(button_Calc[i] == "←") { // 글자를 크게하니까 나오질 않아서 글자 크기만 수정
				button[i].setFont(new Font("Arial", Font.BOLD, 20));

			}
			
		}
		
	
	}
	
	private void setInitLayout() {
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		

		buttonsPanel.setLayout(new GridLayout(5,4,10,10)); // 버튼배치 5행 4열씩 배치
		buttonsPanel.setBounds(7,140,270,290);             // x,y,w,h 사각테두리 사이즈를 지정후 x,y값으로 위치를 배치
														   
		//buttonsPanel.setBackground(Color.black);
		

		add(buttonsPanel);
		add(Result);
	
	}
	
	// 사용자 클릭 Class
	class NumberPad implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		
			String ClickNum = e.getActionCommand();
			if(ClickNum.equals("C")) {
				Result.setText("");
			}else if(ClickNum.equals("=")) {
					String result = Double.toString(Calculate(Result.getText()));
					Result.setText("" + result); // 화면에 계산된 결과값을 뿌려줌
					num = "";  // 이전에 기억하고 있던 num 값을 초기화
			}else if(ClickNum.equals("←")) { // 백스페이스 
					String temp = Result.getText();
					temp = temp.substring(0,temp.length()-1);
					Result.setText(temp);		
					
			}else {
				// 위 두 조건식에 해당되지 않을시 실행 현재 화면에 있는 값이랑 사용자가 클릭한값을 계속 연결
				Result.setText(Result.getText() + e.getActionCommand()); 
			}
			
		}
	}
	
	public void SymbolConver(String inputText) {
		
		// 배열안에 값을 clear 하지 않으면 사용자가 값을 더하고 난뒤 다시 새로운 값을 더할때 이전에 더한 값을 계속 기억하고있어서 clear 해줘야함
		Num_Str.clear(); 
	
		
		for(int i = 0; i < inputText.length(); i++) {
			char ch = inputText.charAt(i);
			if(ch == '-' | ch == '+' | ch == '×' | ch == '÷') { 
				Num_Str.add(num);  // 연산기호를 눌리기 전의 값을들 기억해서 배열에 추가
				num = ""; // num 값 초기화 
				Num_Str.add(ch + ""); // 눌렀던 연산기호 저장
			}else {
				num = num + ch; // 연산기호를 누르고난뒤 숫자를 클릭한 값을들 기억해서 num 에 추가

			}
			
		}
		Num_Str.add(num); // 연산기호를 눌린 후 숫자들을 배열에 추가 
							// 123 + 45를 입력했을 시 [123,+,45] 출력결과 

	}
	
	// 계산 결과 메소드
	public double Calculate(String inputText) {
		
		SymbolConver(inputText);
		// 사용자가 입력한 숫자 및 연산자들을 가져옴 (문자열)
		
		double pre = 0.0;
		double current = 0;
		String mod = "";
		String error = "0";
		
		for (String s : Num_Str) {
			if(s.contentEquals("+")) {
				mod = "add";
			}else if(s.contentEquals("-")) {
				mod = "sub";
			}else if(s.contentEquals("×")) {
				mod = "mul";
			}else if(s.contentEquals("÷")) {
				mod = "div";
			}else {
				if(!s.isEmpty()) {
					 current = Double.parseDouble(s);
				}
	
				 if(mod == "add") {
						System.out.println(pre);
						System.out.println(current);
						pre += current;
						System.out.println(pre);
					}else if(mod == "sub") {
						pre -= current;
					}else if(mod == "mul") {
						pre *= current;
					}else if(mod == "div") {
							pre /= current;	
					}else {
						pre = current;
					}
					
			}
		
			
		}		
		

			return pre;	
		

	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		new CalcTest();
	}
	
	
	
	

}
