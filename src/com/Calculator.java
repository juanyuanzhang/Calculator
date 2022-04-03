package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new CalculatorFrame();
		//frame.show();
		frame.setVisible(true);
	}
	
	
		
}
//計算機最外層Frame
class CalculatorFrame extends JFrame{
	public CalculatorFrame() {
		setTitle("Calculator"); //父類別方法 - 設定名稱
		setSize(200,200);       //父類別方法 - 設定視窗大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //父類別方法 - 設定關閉視窗反應
		/*
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		*/
		Container contentPane = getContentPane(); //取的容器物件
		contentPane.add(new CalculatorPanel()); //把設定好了計算機Panel塞入容器
	}
}
//設計計算機版面與邏輯
class CalculatorPanel extends JPanel implements ActionListener{
	private JTextField display ; //顯示數字用
	private double arg = 0; //計算總數
	private String op = "="; //算數符號
	private boolean start = true; //如果是第一次計算為true,後面累加開始為false
		
	//Constructor
	public CalculatorPanel() {
		//設定主計算機畫面
		setLayout(new BorderLayout()); //用父類別的方法設定Layout manager
		display = new JTextField("0"); //顯示數字的TEXT物件
		display.setEditable(false); //不能修改顯示的文字
		add(display,"North"); //放在Layout最上方
		
		//設定數字鍵畫面
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,4)); //4行4列
		//繪出各按鍵
		String buttons = "789/456*123-0.=+";
		for(int i=0; i<buttons.length();i++) {
			addButton(p,buttons.substring(i,i+1)); //substring(0,1)=button[0]
		}
		add(p,"Center");
		JButton b = new JButton("Clear");
		b.addActionListener(this);
		add(b,"South");
	}
	
	//JPanel是Container物件，利用方法建立很多按鈕物件並加上監聽事件
	private void addButton(Container c , String s) {
		JButton b = new JButton(s); //建立物件並設定顯示文字
		c.add(b); //把剛建好的按鍵加入數字按鈕區(JPanel)
		b.addActionListener(this); //this = ActionListener
	}
	/** 實作Interface內的方法
	 *  計算機計算邏輯
	 *  寫在監聽內
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand(); //按下按鈕輸入的文字
		if(s.equals("Clear")) {
			//全部歸零
			display.setText("0"); ; //顯示數字用
			arg = 0; //計算總數
			op = "="; //算數符號
			start = true;
		}
	
		//如果是0~9跟dot並且是第一個輸入數字的直接顯示，不第一個輸入的數字顯示在第一個數字後面
		if('0'<=s.charAt(0)&&s.charAt(0)<='9'||s.equals(".")) {
			if(start) {
				if(s.equals(".")) {
					display.setText("0"+s);
				}
				else {
					display.setText(s);
				}
			}
			else {
				if(s.equals(".")&&display.getText().contains(".")) ;//輸入dot但是數字有dot，不做動作
				else display.setText(display.getText()+s);
			}
			start = false;
			
		}else {
			//先判斷是否為輸入-並且為第一個，判斷為負數，
			if(start) {
				if(s.equals("-") && display.getText().equals(0)) {
					display.setText(s);
					start = false;
				}else if(s.equals("Clear")) {
					//全部歸零
					display.setText("0"); ; //顯示數字用
					arg = 0; //計算總數
					op = "="; //算數符號
					start = true;
				}
				else {
					op = s;
				}
			}else {
				double x = Double.parseDouble(display.getText());//將輸入的文字轉換為double
				calculate(x); //進行各式運算
				op = s;
				start = true;
			}
		}
		
	}
	
	public void calculate(double n) {
		if(op.equals("+"))arg+=n;
		else if(op.equals("-"))arg-=n;
		else if(op.equals("*"))arg*=n;
		else if(op.equals("/"))arg/=n;
		else if(op.equals("="))arg=n;
		display.setText(""+arg);
	}
	
}
