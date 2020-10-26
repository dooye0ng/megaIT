
# Day25 Homework  
### Quiz01.java
```java
package com.mega.homework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Quiz01 {
	public static void main(String[] args) {
		String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", " ", "0", " "};
		String[] cals = {"+", "-", "*", "/", "="};
		String[] exp = {"0", "0", "0"};
		
		// setting frame(calculator)
		JFrame calculator = new JFrame("Calculator");
		calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calculator.setSize(500, 800);
		calculator.setLocationRelativeTo(null);
		calculator.setLayout(new BorderLayout());
		
		// number panel
		Panel numPanel = new Panel();
		numPanel.setLayout(new GridLayout(4, 3));
		JButton[] numBtns = new JButton[nums.length];
		
		for(int i=0; i<numBtns.length; ++i) {
			numBtns[i] = new JButton(nums[i]);
			numPanel.add(numBtns[i]);
		}
		
		
		// calculate panel
		Panel calPanel = new Panel();
		calPanel.setLayout(new FlowLayout());
		JButton[] calBtns = new JButton[cals.length];
		
		for(int i=0; i<calBtns.length; ++i) {
			calBtns[i] = new JButton(cals[i]);
			calPanel.add(calBtns[i]);
		}
		
		// add panels
		calculator.add(numPanel);
		calculator.add(calPanel, BorderLayout.SOUTH);
		
		
		// listener for number
		
		ActionListener numListener;
		numListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				
				if(exp[0] == "0") {
					exp[0] = b.getText();
				}
				else {
					exp[2] = b.getText();
				}
				
				System.out.println("log: " + exp[0] + " " + exp[1] + " " + exp[2]);
				
			}
		};
		
		for(JButton b : numBtns) {
			b.addActionListener(numListener);
		}
		
		// listener for cals
		ActionListener calListener;
		calListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				if(!b.getText().equals("=")) {
					exp[1] = b.getText();
					System.out.println("log: " + exp[0] + " " + exp[1] + " " + exp[2]);
					return;
				}
				
				
				
				int x = Integer.parseInt(exp[0]);
				int y = Integer.parseInt(exp[2]);
				
				switch (exp[1]) {
				case "+":
					JOptionPane.showMessageDialog(calculator, String.valueOf(x+y));
					break;
				case "-":
					JOptionPane.showMessageDialog(calculator, String.valueOf(x-y));
					break;
				case "*":
					JOptionPane.showMessageDialog(calculator, String.valueOf(x*y));
					break;
				case "/":
					JOptionPane.showMessageDialog(calculator, String.valueOf(x/y));
					break;
				default:
					JOptionPane.showMessageDialog(calculator, "Error");
				}
				
				// "=" �� ��� �ٽ� 0���� �ʱ�ȭ
				for(int i=0; i<exp.length; ++i) {
					exp[i] = "0";
				}
			}
		};
		
		for(JButton b : calBtns) {
			b.addActionListener(calListener);
		}
		// set visible
		calculator.setVisible(true);
	}
}
```
