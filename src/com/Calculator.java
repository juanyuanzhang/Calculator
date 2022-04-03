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
//�p����̥~�hFrame
class CalculatorFrame extends JFrame{
	public CalculatorFrame() {
		setTitle("Calculator"); //�����O��k - �]�w�W��
		setSize(200,200);       //�����O��k - �]�w�����j�p
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�����O��k - �]�w������������
		/*
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		*/
		Container contentPane = getContentPane(); //�����e������
		contentPane.add(new CalculatorPanel()); //��]�w�n�F�p���Panel��J�e��
	}
}
//�]�p�p��������P�޿�
class CalculatorPanel extends JPanel implements ActionListener{
	private JTextField display ; //��ܼƦr��
	private double arg = 0; //�p���`��
	private String op = "="; //��ƲŸ�
	private boolean start = true; //�p�G�O�Ĥ@���p�⬰true,�᭱�֥[�}�l��false
		
	//Constructor
	public CalculatorPanel() {
		//�]�w�D�p����e��
		setLayout(new BorderLayout()); //�Τ����O����k�]�wLayout manager
		display = new JTextField("0"); //��ܼƦr��TEXT����
		display.setEditable(false); //����ק���ܪ���r
		add(display,"North"); //��bLayout�̤W��
		
		//�]�w�Ʀr��e��
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,4)); //4��4�C
		//ø�X�U����
		String buttons = "789/456*123-0.=+";
		for(int i=0; i<buttons.length();i++) {
			addButton(p,buttons.substring(i,i+1)); //substring(0,1)=button[0]
		}
		add(p,"Center");
		JButton b = new JButton("Clear");
		b.addActionListener(this);
		add(b,"South");
	}
	
	//JPanel�OContainer����A�Q�Τ�k�إ߫ܦh���s����å[�W��ť�ƥ�
	private void addButton(Container c , String s) {
		JButton b = new JButton(s); //�إߪ���ó]�w��ܤ�r
		c.add(b); //���ئn������[�J�Ʀr���s��(JPanel)
		b.addActionListener(this); //this = ActionListener
	}
	/** ��@Interface������k
	 *  �p����p���޿�
	 *  �g�b��ť��
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand(); //���U���s��J����r
		if(s.equals("Clear")) {
			//�����k�s
			display.setText("0"); ; //��ܼƦr��
			arg = 0; //�p���`��
			op = "="; //��ƲŸ�
			start = true;
		}
	
		//�p�G�O0~9��dot�åB�O�Ĥ@�ӿ�J�Ʀr��������ܡA���Ĥ@�ӿ�J���Ʀr��ܦb�Ĥ@�ӼƦr�᭱
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
				if(s.equals(".")&&display.getText().contains(".")) ;//��Jdot���O�Ʀr��dot�A�����ʧ@
				else display.setText(display.getText()+s);
			}
			start = false;
			
		}else {
			//���P�_�O�_����J-�åB���Ĥ@�ӡA�P�_���t�ơA
			if(start) {
				if(s.equals("-") && display.getText().equals(0)) {
					display.setText(s);
					start = false;
				}else if(s.equals("Clear")) {
					//�����k�s
					display.setText("0"); ; //��ܼƦr��
					arg = 0; //�p���`��
					op = "="; //��ƲŸ�
					start = true;
				}
				else {
					op = s;
				}
			}else {
				double x = Double.parseDouble(display.getText());//�N��J����r�ഫ��double
				calculate(x); //�i��U���B��
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
