package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MediaTracker;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FirstInterface extends JFrame {

	private JTextField txtFldPlayer1;
	private JTextField txtFldPlayer2;
	private ButtonDuelMode btnDuelMode;
	private ButtonQuitGame btnQuitGame;

	public FirstInterface() {

		setTitle("Yu-Gi-Oh!");
		setBounds(500, 200, 630, 770);
		setLocation(610, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel(new GridLayout(2, 1));
		outerPanel.setBackground(new Color(240, 240, 240));
		outerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		JPanel downHalfPanel = new JPanel();
		downHalfPanel.setBackground(new Color(240, 240, 240));
		downHalfPanel.setLayout(null);
		downHalfPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

		JLabel lblItsTimeToDuel = new JLabel("IT'S TIME TO DUEL!");
		lblItsTimeToDuel.setFont(new Font("Agency FB", Font.BOLD, 27));
		lblItsTimeToDuel.setBounds(160, 20, 300, 28);
		downHalfPanel.add(lblItsTimeToDuel);

		JLabel lblPlayer1Name = new JLabel("Player 1");
		lblPlayer1Name.setFont(new Font("Agency FB", Font.BOLD, 27));
		lblPlayer1Name.setBounds(101, 79, 200, 43);
		downHalfPanel.add(lblPlayer1Name);

		JLabel lblPlayer2Name = new JLabel("Player 2");
		lblPlayer2Name.setFont(new Font("Agency FB", Font.BOLD, 27));
		lblPlayer2Name.setBounds(400, 79, 200, 43);
		downHalfPanel.add(lblPlayer2Name);

		txtFldPlayer1 = new JTextField();
		txtFldPlayer1.setFont(new Font("Agency FB", Font.BOLD, 22));
		txtFldPlayer1.setColumns(10);
		txtFldPlayer1.setBounds(101, 130, 170, 28);
		downHalfPanel.add(txtFldPlayer1);

		txtFldPlayer2 = new JTextField();
		txtFldPlayer2.setFont(new Font("Agency FB", Font.BOLD, 22));
		txtFldPlayer2.setColumns(10);
		txtFldPlayer2.setBounds(353, 130, 170, 28);
		downHalfPanel.add(txtFldPlayer2);

		btnDuelMode = new ButtonDuelMode("Duel Mode");
		btnDuelMode.setToolTipText("Proceed to game");
		btnDuelMode.setBackground(new Color(255, 215, 0));
		btnDuelMode.setFont(new Font("Agency FB", Font.BOLD, 27));
		btnDuelMode.setBounds(210, 234, 200, 36);
		downHalfPanel.add(btnDuelMode);
		getRootPane().setDefaultButton(btnDuelMode);

		btnQuitGame = new ButtonQuitGame("Quit Game");
		btnQuitGame.setToolTipText("Close application");
		btnQuitGame.setBackground(new Color(255, 215, 0));
		btnQuitGame.setFont(new Font("Agency FB", Font.BOLD, 27));
		btnQuitGame.setBounds(210, 283, 200, 36);
		downHalfPanel.add(btnQuitGame);

		JSeparator separator = new JSeparator();
		separator.setBounds(53, 191, 535, 2);
		downHalfPanel.add(separator);

		outerPanel.add(new ContentPanel());
		outerPanel.add(downHalfPanel);

		setContentPane(outerPanel);

	}

	public JTextField getTxtFldPlayer1() {
		return txtFldPlayer1;
	}

	public JTextField getTxtFldPlayer2() {
		return txtFldPlayer2;
	}

	public ButtonDuelMode getBtnDuelMode() {
		return btnDuelMode;
	}

	public ButtonQuitGame getBtnQuitGame() {
		return btnQuitGame;
	}

	/*
	 * Background painting class
	 */
	class ContentPanel extends JPanel {

		ImageIcon bgimage = null;

		ContentPanel() {

			MediaTracker mt = new MediaTracker(this);
			bgimage = new ImageIcon(this.getClass().getResource("/resources/YugiBackground.jpg"));
			mt.addImage(bgimage.getImage(), 0);
			try {
				mt.waitForAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(bgimage.getImage(), 0, 0, getWidth(), getHeight(), null);

		}

	}

}
