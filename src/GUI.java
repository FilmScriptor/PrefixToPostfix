/*
 * Author: Cuong Nguyen
 * Class: CMSC350
 * Date: 20220120
 * Purpose: Generate GUI for the application
 */


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.EmptyStackException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {
	
	//Set variables
	String expression;
	String result;
	
	//instantiate JFrame components
	JPanel frame = new JPanel();
	JPanel expPanel = new JPanel();
	JPanel butnPanel = new JPanel();
	JPanel resPanel = new JPanel();
	JLabel expLabel = new JLabel("Enter Expression");
	JLabel resLabel = new JLabel("Result");
	JTextField expField = new JTextField(null, 40); 
	JTextField resField = new JTextField(null, 40);
	
	//instantiate button for two classes
	JButton preBtn = new JButton("Prefix to Postfix");
	JButton postBtn = new JButton("Postfix to Prefix");
	
	//instantiate PostToPre and PreToPost classes
	PostToPre post = new PostToPre();
	PreToPost pre = new PreToPost();
	
	
	public GUI() {
		
		//set up expression input panel
		expPanel.add(expLabel);
		expPanel.add(expField);
		
		//set up button panel
		butnPanel.add(preBtn);
		butnPanel.add(postBtn);
		
		//set up result panel
		resField.setEditable(false);
		resPanel.add(resLabel);
		resPanel.add(resField);
		
		//adding component to frame
		add(frame, BorderLayout.CENTER);
		frame.add(expPanel);
		frame.add(butnPanel);
		frame.add(resPanel);
		
		//set up frame
		setTitle("Expression Converter");
		setSize(500, 200);
		setResizable(false);
		setLocation(500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		preBtn.addActionListener(this);
		postBtn.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			String expressionField = expField.getText();
			
			if (e.getSource() == postBtn) {
				
				String converted = PostToPre.postConvert(expressionField);
				resField.setText(converted);
			}
			else if (e.getSource() == preBtn) {
				String converted = PreToPost.preConvert(expressionField);
				resField.setText(converted);
			}
		}
		catch (NullPointerException | IOException | SyntaxErrorException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	
	}
	
	public static void main(String[] args) {
		GUI simpleConversion = new GUI();	
	}
}






