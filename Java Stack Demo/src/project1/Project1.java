package project1;
/*
 * File: Project1.java
 * Author: Linden Crandall
 * Purpose: This program demonstrates conversions of prefix expressions
 * 			to postfix expressions and vice-versa through a GUI 
 */

import java.awt.*;
import java.awt.event.*;
import java.util.EmptyStackException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Project1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Initialize GUI components
	JTextField expressionTxtField, resultTxtField;
	JLabel expressionLabel, resultLabel;
	JButton preButton, postButton;
	JPanel master, textPanel1, buttonPanel, textPanel2;

	// initialize Conversion class object
	Conversion c;

	public Project1() {

		/******* MasterPanel ******/
		master = new JPanel();

		// top panel
		textPanel1 = new JPanel(new FlowLayout());
		textPanel1.setBorder(new EmptyBorder(0, 0, 15, 0));
		expressionLabel = new JLabel("Enter Expression");
		expressionTxtField = new JTextField(20);
		textPanel1.add(expressionLabel);
		textPanel1.add(expressionTxtField);

		// middle panel
		buttonPanel = new JPanel(new FlowLayout());
		preButton = new JButton("Prefix to PostFix");
		postButton = new JButton("PostFix to Prefix");
		buttonPanel.add(preButton);
		buttonPanel.add(postButton);
		buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

		// bottom panel
		textPanel2 = new JPanel(new FlowLayout());
		textPanel2.setBorder(new EmptyBorder(15, 0, 0, 0));
		resultLabel = new JLabel("Result");
		resultTxtField = new JTextField(20);
		resultTxtField.setEditable(false);
		textPanel2.add(resultLabel);
		textPanel2.add(resultTxtField);

		// add panels to master panel
		master.add(textPanel1);
		master.add(buttonPanel);
		master.add(textPanel2);
		// add master panel to JFrame
		add(master);

		// event listeners for JButtons
		preButton.addActionListener(new PrefixButton());
		postButton.addActionListener(new PostfixButton());

	}

	/****** Nested classes for action listeners *********/
	class PrefixButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				resultTxtField.setText("");
				String s = expressionTxtField.getText();
				if (s.isEmpty()) {
					throw new IllegalArgumentException();
				}
				// send user input to Conversion class method
				resultTxtField.setText(Conversion.preToPost(s));

			} catch (SyntaxErrorException e1) {
				JOptionPane.showMessageDialog(null,
						"Pop attempted on empty stack, please check your expression input and try again",
						"Syntax Error", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(null, "Please enter a valid postfix or prefix expression", "Syntax Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	class PostfixButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				resultTxtField.setText("");
				String s = expressionTxtField.getText();
				if (s.isEmpty()) {
					throw new IllegalArgumentException();
				}
				// send user input to Conversion class method
				resultTxtField.setText(Conversion.postToPre(s));
			} catch (SyntaxErrorException e1) {
				JOptionPane.showMessageDialog(null,
						"Pop attempted on empty stack, please check your expression input and try again",
						"Syntax Error", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(null, "Please enter a valid postfix or prefix expression", "Empty field",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Show GUI
	public static void main(String[] args) {
		Project1 p1 = new Project1();

		p1.setTitle("Expression Converter");
		p1.setSize(500, 200);
		p1.setVisible(true);
		p1.setLocationRelativeTo(null);
		p1.setResizable(false);
		p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
