package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SecondInterface extends JFrame {

	private JPanel panelPlayer1;
	private JPanel panelPlayer2;
	private JPanel panelPlayer1Field;
	private JPanel panelPlayer2Field;
	private JPanel panelPlayer1Hand;
	private JPanel panelPlayer2Hand;
	private ButtonNextPhase btnPlayer1NextPhase;
	private ButtonNextPhase btnPlayer2NextPhase;
	private ButtonEndTurn btnPlayer1EndTurn;
	private ButtonEndTurn btnPlayer2EndTurn;
	private JButton btnPlayer1Graveyard;
	private JButton btnPlayer2Graveyard;
	private JLabel lblPlayer1Name;
	private JLabel lblPlayer2Name;
	private JLabel lblPlayer1LifePoints;
	private JLabel lblPlayer2LifePoints;
	private JLabel lblPlayer1Phase;
	private JLabel lblPlayer2Phase;
	private JLabel lblPlayer1DeckCount;
	private JLabel lblPlayer2DeckCount;
	private JLabel lblPlayer1YourTurn;
	private JLabel lblPlayer2YourTurn;

	private ArrayList<ButtonMonster> arrayPlayer1Monsters;
	private ArrayList<ButtonSpell> arrayPlayer1Spells;

	private ArrayList<ButtonMonster> arrayPlayer2Monsters;
	private ArrayList<ButtonSpell> arrayPlayer2Spells;

	private ButtonDeck btnPlayer1Deck;
	private ButtonDeck btnPlayer2Deck;

	private JButton btnPlayer2Hand;
	private JButton btnPlayer1Hand;

	private JProgressBar progressBarPlayer2;
	private JProgressBar progressBarPlayer1;

	public SecondInterface() {

		setBounds(100, 100, 1301, 1036);
		setLocation(280, 7);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2, 0));

		panelPlayer2 = new JPanel();
		panelPlayer2.setBackground(new Color(105, 105, 105));
		this.getContentPane().add(panelPlayer2);
		panelPlayer2.setLayout(null);

		panelPlayer1 = new JPanel();
		panelPlayer1.setBackground(new Color(105, 105, 105));
		this.getContentPane().add(panelPlayer1);
		panelPlayer1.setLayout(null);

		panelPlayer2.setPreferredSize(new Dimension(1300, 493));
		panelPlayer1.setPreferredSize(new Dimension(1300, 493));

		panelPlayer1Field = new JPanel();
		panelPlayer1Field.setBackground(new Color(192, 192, 192));
		panelPlayer1Field.setBounds(320, 0, 575, 340);
		panelPlayer1.add(panelPlayer1Field);
		panelPlayer1Field.setLayout(new GridLayout(2, 5));

		panelPlayer1Hand = new JPanel();
		panelPlayer1Hand.setBounds(379, 372, 460, 121);
		panelPlayer1.add(panelPlayer1Hand);
		panelPlayer1Hand.setLayout(new GridLayout(1, 0, 0, 0));

		btnPlayer1Hand = new JButton("Hand");
		btnPlayer1Hand.setBackground(new Color(211, 211, 211));
		panelPlayer1Hand.add(btnPlayer1Hand);

		arrayPlayer1Monsters = new ArrayList<ButtonMonster>(5);
		arrayPlayer1Spells = new ArrayList<ButtonSpell>(5);

		for (int i = 0; i < 5; i++) {
			ButtonMonster monsterButton = new ButtonMonster();
			monsterButton.setOpaque(false);
			monsterButton.setContentAreaFilled(false);
			monsterButton.setEnabled(false);
			arrayPlayer1Monsters.add(monsterButton);
			panelPlayer1Field.add(monsterButton);
		}

		for (int i = 0; i < 5; i++) {
			ButtonSpell spellButton = new ButtonSpell();
			spellButton.setOpaque(false);
			spellButton.setContentAreaFilled(false);
			spellButton.setEnabled(false);
			arrayPlayer1Spells.add(spellButton);
			panelPlayer1Field.add(spellButton);
		}

		JLabelWithIcon imageLabel = new JLabelWithIcon("src/resources/YugiProfile.png");
		imageLabel.setBounds(0, 178, 322, 340);
		panelPlayer1.add(imageLabel);

		lblPlayer1Name = new JLabel("Yugi");
		lblPlayer1Name.setFont(new Font("Agency FB", Font.BOLD, 50));
		lblPlayer1Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer1Name.setBounds(1054, 63, 200, 65);
		panelPlayer1.add(lblPlayer1Name);

		lblPlayer1LifePoints = new JLabel("8000");
		lblPlayer1LifePoints.setFont(new Font("Agency FB", Font.BOLD, 50));
		lblPlayer1LifePoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer1LifePoints.setBounds(1071, 141, 174, 41);
		panelPlayer1.add(lblPlayer1LifePoints);

		lblPlayer1Phase = new JLabel("MAIN1");
		lblPlayer1Phase.setFont(new Font("Agency FB", Font.BOLD, 45));
		lblPlayer1Phase.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer1Phase.setBounds(1083, 249, 152, 41);
		panelPlayer1.add(lblPlayer1Phase);

		btnPlayer1NextPhase = new ButtonNextPhase("Next Phase");
		btnPlayer1NextPhase.setBackground(new Color(169, 169, 169));
		btnPlayer1NextPhase.setFont(new Font("Agency FB", Font.BOLD, 35));
		btnPlayer1NextPhase.setBounds(1073, 303, 183, 78);
		panelPlayer1.add(btnPlayer1NextPhase);

		lblPlayer1YourTurn = new JLabelWithIcon("src/resources/YourTurn.jpg");
		lblPlayer1YourTurn.setBounds(1083, 20, 157, 40);
		panelPlayer1.add(lblPlayer1YourTurn);

		btnPlayer1EndTurn = new ButtonEndTurn("End Turn");
		btnPlayer1EndTurn.setBackground(new Color(169, 169, 169));
		btnPlayer1EndTurn.setFont(new Font("Agency FB", Font.BOLD, 35));
		btnPlayer1EndTurn.setBounds(1073, 394, 183, 78);
		panelPlayer1.add(btnPlayer1EndTurn);

		lblPlayer1DeckCount = new JLabel("20");
		lblPlayer1DeckCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer1DeckCount.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblPlayer1DeckCount.setBounds(944, 432, 83, 30);
		panelPlayer1.add(lblPlayer1DeckCount);

		JPanel panelPlayer1DeckAndGraveyard = new JPanel();
		panelPlayer1DeckAndGraveyard.setBounds(927, 79, 115, 340);
		panelPlayer1DeckAndGraveyard.setBorder(null);
		panelPlayer1DeckAndGraveyard.setBackground(new Color(105, 105, 105));
		panelPlayer1DeckAndGraveyard.setLayout(new GridLayout(2, 1));
		panelPlayer1.add(panelPlayer1DeckAndGraveyard);

		btnPlayer1Graveyard = new JButton();
		btnPlayer1Graveyard.setIcon(new ImageIcon("src/resources/CardBack.png"));
		btnPlayer1Graveyard.setOpaque(false);
		btnPlayer1Graveyard.setContentAreaFilled(false);
		btnPlayer1Graveyard.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelPlayer1DeckAndGraveyard.add(btnPlayer1Graveyard);

		btnPlayer1Deck = new ButtonDeck();
		btnPlayer1Deck.setToolTipText("Player 1 Deck");
		btnPlayer1Deck.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnPlayer1Deck.setForeground(Color.BLACK);
		btnPlayer1Deck.setIcon(new ImageIcon("src/resources/CardBack.png"));
		btnPlayer1Deck.setOpaque(false);
		btnPlayer1Deck.setContentAreaFilled(false);
		panelPlayer1DeckAndGraveyard.add(btnPlayer1Deck);

		progressBarPlayer1 = new JProgressBar(0, 8000);
		progressBarPlayer1.setValue(8000);
		progressBarPlayer1.setBounds(1071, 203, 184, 19);
		panelPlayer1.add(progressBarPlayer1);

		//////////////////////////////////////////////////////////////////////////////

		panelPlayer2Field = new JPanel();
		panelPlayer2Field.setBackground(new Color(192, 192, 192));
		panelPlayer2Field.setBounds(320, 153, 575, 340);
		panelPlayer2.add(panelPlayer2Field);
		panelPlayer2Field.setLayout(new GridLayout(2, 5));

		panelPlayer2Hand = new JPanel();
		panelPlayer2Hand.setBounds(379, 0, 460, 121);
		panelPlayer2.add(panelPlayer2Hand);
		panelPlayer2Hand.setLayout(new GridLayout(1, 5));

		btnPlayer2Hand = new JButton("Hand");
		btnPlayer2Hand.setBackground(new Color(211, 211, 211));
		panelPlayer2Hand.add(btnPlayer2Hand);

		arrayPlayer2Monsters = new ArrayList<ButtonMonster>(5);
		arrayPlayer2Spells = new ArrayList<ButtonSpell>(5);

		for (int i = 0; i < 5; i++) {
			ButtonSpell spellButton = new ButtonSpell();
			spellButton.setOpaque(false);
			spellButton.setContentAreaFilled(false);
			spellButton.setEnabled(false);
			arrayPlayer2Spells.add(spellButton);
			panelPlayer2Field.add(spellButton);
		}

		for (int i = 0; i < 5; i++) {
			ButtonMonster monsterButton = new ButtonMonster();
			monsterButton.setOpaque(false);
			monsterButton.setContentAreaFilled(false);
			monsterButton.setEnabled(false);
			arrayPlayer2Monsters.add(monsterButton);
			panelPlayer2Field.add(monsterButton);
		}

		JLabelWithIcon kaibaProfile = new JLabelWithIcon("src/resources/KaibaProfile.png");
		kaibaProfile.setBounds(0, 0, 319, 307);
		panelPlayer2.add(kaibaProfile);

		lblPlayer2Name = new JLabel("Kaiba");
		lblPlayer2Name.setFont(new Font("Agency FB", Font.BOLD, 50));
		lblPlayer2Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer2Name.setBounds(1061, 297, 189, 54);
		panelPlayer2.add(lblPlayer2Name);

		lblPlayer2LifePoints = new JLabel("8000");
		lblPlayer2LifePoints.setFont(new Font("Agency FB", Font.BOLD, 50));
		lblPlayer2LifePoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer2LifePoints.setBounds(1066, 364, 174, 41);
		panelPlayer2.add(lblPlayer2LifePoints);

		lblPlayer2Phase = new JLabel("MAIN1");
		lblPlayer2Phase.setFont(new Font("Agency FB", Font.BOLD, 45));
		lblPlayer2Phase.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer2Phase.setBounds(1080, 208, 152, 41);
		panelPlayer2.add(lblPlayer2Phase);

		btnPlayer2NextPhase = new ButtonNextPhase("Next Phase");
		btnPlayer2NextPhase.setBackground(new Color(169, 169, 169));
		btnPlayer2NextPhase.setFont(new Font("Agency FB", Font.BOLD, 35));
		btnPlayer2NextPhase.setBounds(1067, 117, 183, 78);
		panelPlayer2.add(btnPlayer2NextPhase);

		lblPlayer2YourTurn = new JLabel(new ImageIcon("src/resources/YourTurn.jpg"));
		lblPlayer2YourTurn.setBounds(1083, 427, 157, 40);
		panelPlayer2.add(lblPlayer2YourTurn);

		btnPlayer2EndTurn = new ButtonEndTurn("End Turn");
		btnPlayer2EndTurn.setBackground(new Color(169, 169, 169));
		btnPlayer2EndTurn.setFont(new Font("Agency FB", Font.BOLD, 35));
		btnPlayer2EndTurn.setBounds(1067, 23, 183, 78);
		panelPlayer2.add(btnPlayer2EndTurn);

		lblPlayer2DeckCount = new JLabel("20");
		lblPlayer2DeckCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer2DeckCount.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblPlayer2DeckCount.setBounds(939, 23, 83, 32);
		panelPlayer2.add(lblPlayer2DeckCount);

		JSeparator separator = new JSeparator();
		separator.setBounds(922, 491, 353, 2);
		panelPlayer2.add(separator);

		JPanel panelPlayer2DeckAndGraveyard = new JPanel();
		panelPlayer2DeckAndGraveyard.setLayout(new GridLayout(2, 1));
		panelPlayer2DeckAndGraveyard.setBounds(927, 68, 115, 340);
		panelPlayer2DeckAndGraveyard.setBorder(null);
		panelPlayer2DeckAndGraveyard.setBackground(new Color(105, 105, 105));
		panelPlayer2.add(panelPlayer2DeckAndGraveyard);

		btnPlayer2Deck = new ButtonDeck();
		btnPlayer2Deck.setToolTipText("Player 2 Deck");
		btnPlayer2Deck.setForeground(Color.BLACK);
		btnPlayer2Deck.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnPlayer2Deck.setIcon(new ImageIcon("src/resources/CardBack.png"));
		btnPlayer2Deck.setOpaque(false);
		btnPlayer2Deck.setContentAreaFilled(false);
		panelPlayer2DeckAndGraveyard.add(btnPlayer2Deck);

		btnPlayer2Graveyard = new JButton();
		btnPlayer2Graveyard.setIcon(new ImageIcon("src/resources/CardBack.png"));
		btnPlayer2Graveyard.setOpaque(false);
		btnPlayer2Graveyard.setContentAreaFilled(false);
		btnPlayer2Graveyard.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelPlayer2DeckAndGraveyard.add(btnPlayer2Graveyard);

		progressBarPlayer2 = new JProgressBar(0, 8000);
		progressBarPlayer2.setValue(8000);
		progressBarPlayer2.setBounds(1066, 272, 184, 19);

		panelPlayer2.add(progressBarPlayer2);

		setResizable(false);
		pack();

	}

	public JPanel getPanelPlayer1() {
		return panelPlayer1;
	}

	public JPanel getPanelPlayer2() {
		return panelPlayer2;
	}

	public JPanel getPanelPlayer1Field() {
		return panelPlayer1Field;
	}

	public JPanel getPanelPlayer2Field() {
		return panelPlayer2Field;
	}

	public JPanel getPanelPlayer1Hand() {
		return panelPlayer1Hand;
	}

	public JPanel getPanelPlayer2Hand() {
		return panelPlayer2Hand;
	}

	public ButtonNextPhase getBtnPlayer1NextPhase() {
		return btnPlayer1NextPhase;
	}

	public ButtonNextPhase getBtnPlayer2NextPhase() {
		return btnPlayer2NextPhase;
	}

	public ButtonEndTurn getBtnPlayer1EndTurn() {
		return btnPlayer1EndTurn;
	}

	public ButtonEndTurn getBtnPlayer2EndTurn() {
		return btnPlayer2EndTurn;
	}

	public JLabel getLblPlayer1Name() {
		return lblPlayer1Name;
	}

	public JLabel getLblPlayer2Name() {
		return lblPlayer2Name;
	}

	public JLabel getLblPlayer1LifePoints() {
		return lblPlayer1LifePoints;
	}

	public JLabel getLblPlayer2LifePoints() {
		return lblPlayer2LifePoints;
	}

	public JLabel getLblPlayer1Phase() {
		return lblPlayer1Phase;
	}

	public JLabel getLblPlayer2Phase() {
		return lblPlayer2Phase;
	}

	public JLabel getLblPlayer1DeckCount() {
		return lblPlayer1DeckCount;
	}

	public JLabel getLblPlayer2DeckCount() {
		return lblPlayer2DeckCount;
	}

	public JLabel getLblPlayer1YourTurn() {
		return lblPlayer1YourTurn;
	}

	public JLabel getLblPlayer2YourTurn() {
		return lblPlayer2YourTurn;
	}

	public ArrayList<ButtonMonster> getArrayPlayer1Monsters() {
		return arrayPlayer1Monsters;
	}

	public ArrayList<ButtonSpell> getArrayPlayer1Spells() {
		return arrayPlayer1Spells;
	}

	public ArrayList<ButtonMonster> getArrayPlayer2Monsters() {
		return arrayPlayer2Monsters;
	}

	public ArrayList<ButtonSpell> getArrayPlayer2Spells() {
		return arrayPlayer2Spells;
	}

	public ButtonDeck getBtnPlayer1Deck() {
		return btnPlayer1Deck;
	}

	public ButtonDeck getBtnPlayer2Deck() {
		return btnPlayer2Deck;
	}

	public JButton getBtnPlayer2Hand() {
		return btnPlayer2Hand;
	}

	public JButton getBtnPlayer1Hand() {
		return btnPlayer1Hand;
	}

	/*
	 * Label with background class
	 */
	class JLabelWithIcon extends JLabel {

		public JLabelWithIcon(String path) {
			super(new ImageIcon(path));
		}

	}

	public JButton getBtnPlayer1Graveyard() {
		return btnPlayer1Graveyard;
	}

	public JButton getBtnPlayer2Graveyard() {
		return btnPlayer2Graveyard;
	}

	public JProgressBar getProgressBarPlayer2() {
		return progressBarPlayer2;
	}

	public JProgressBar getProgressBarPlayer1() {
		return progressBarPlayer1;
	}

}
