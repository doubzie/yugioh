package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import eg.edu.guc.yugioh.gui.SecondInterface.JLabelWithIcon;

public class ThirdInterface extends JFrame {
	
	private ButtonPlayAgain btnPlayAgain;
	private ButtonQuitGame btnQuitGame;

	public ThirdInterface() {
		
		setBounds(500, 200, 800, 501);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		btnPlayAgain = new ButtonPlayAgain("Play Again");
		btnPlayAgain.setToolTipText("Proceed to another game");
		btnPlayAgain.setBackground(new Color(255, 215, 0));
		btnPlayAgain.setFont(new Font("Agency FB", Font.BOLD, 27));
		btnPlayAgain.setBounds(116, 369, 137, 36);
		btnPlayAgain.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnPlayAgain);
		getRootPane().setDefaultButton(btnPlayAgain);
		
		btnQuitGame = new ButtonQuitGame("Quit Game");
		btnQuitGame.setToolTipText("Close application");
		btnQuitGame.setBackground(new Color(255, 215, 0));
		btnQuitGame.setFont(new Font("Agency FB", Font.BOLD, 27));
		btnQuitGame.setBounds(547, 369, 137, 36);
		btnQuitGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnQuitGame);
		
		JLabelWithIcon lblBackground = new JLabelWithIcon("EndGameBackground.jpg");
		lblBackground.setBounds(0, 0, 794, 466);
		getContentPane().add(lblBackground);
	
		
	}
	

	public ButtonPlayAgain getBtnPlayAgain() {
		return btnPlayAgain;
	}


	public ButtonQuitGame getBtnQuitGame() {
		return btnQuitGame;
	}
	
	
	/*
	 * Label with background class
	 */
	class JLabelWithIcon extends JLabel {
		
		public JLabelWithIcon(String path){
			super(new ImageIcon(path));
		}
		
	}

}
