package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class PlayerHand extends JFrame {

  private JButton btnSummonMonster;
  private JButton btnSetMonster;
  private JButton btnActivateSpell;
  private JButton btnSetSpell;
  private JPanel panelCards;
  private ArrayList<ButtonHand> arrayCards = new ArrayList<ButtonHand>(20);

  public PlayerHand() {
    setBounds(100, 100, 1469, 417);
    setLocation(217, 300);
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(null);

    panelCards = new JPanel();
    panelCards.setBounds(12, 13, 1147, 344);
    getContentPane().add(panelCards);
    panelCards.setLayout(new GridLayout(2, 10));

    JPanel panelMonsterControl = new JPanel();
    panelMonsterControl.setBorder(
      new EtchedBorder(EtchedBorder.LOWERED, null, null)
    );
    panelMonsterControl.setBounds(1171, 13, 268, 164);
    getContentPane().add(panelMonsterControl);
    panelMonsterControl.setLayout(null);

    btnSummonMonster = new JButton("Summon Monster");
    btnSummonMonster.setContentAreaFilled(false);
    btnSummonMonster.setFont(new Font("Agency FB", Font.BOLD, 12));
    btnSummonMonster.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnSummonMonster.setBounds(54, 35, 159, 44);
    panelMonsterControl.add(btnSummonMonster);

    btnSetMonster = new JButton("Set Monster");
    btnSetMonster.setContentAreaFilled(false);
    btnSetMonster.setFont(new Font("Agency FB", Font.BOLD, 12));
    btnSetMonster.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnSetMonster.setBounds(54, 92, 159, 44);
    panelMonsterControl.add(btnSetMonster);

    JPanel panelSpellControl = new JPanel();
    panelSpellControl.setLayout(null);
    panelSpellControl.setBorder(
      new EtchedBorder(EtchedBorder.LOWERED, null, null)
    );
    panelSpellControl.setBounds(1171, 193, 268, 164);
    getContentPane().add(panelSpellControl);

    btnActivateSpell = new JButton("Activate Spell");
    btnActivateSpell.setContentAreaFilled(false);
    btnActivateSpell.setFont(new Font("Agency FB", Font.BOLD, 12));
    btnActivateSpell.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnActivateSpell.setBounds(54, 35, 159, 44);
    panelSpellControl.add(btnActivateSpell);

    btnSetSpell = new JButton("Set Spell");
    btnSetSpell.setFont(new Font("Agency FB", Font.BOLD, 12));
    btnSetSpell.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnSetSpell.setContentAreaFilled(false);
    btnSetSpell.setBounds(54, 92, 159, 44);
    panelSpellControl.add(btnSetSpell);

    for (int i = 0; i < 20; i++) {
      ButtonHand card = new ButtonHand();
      card.setOpaque(false);
      card.setContentAreaFilled(false);
      arrayCards.add(card);
      panelCards.add(card);
      card.setEnabled(false);
    }
  }

  public JPanel getPanelCards() {
    return panelCards;
  }

  public ArrayList<ButtonHand> getArrayCards() {
    return arrayCards;
  }

  public JButton getBtnSummonMonster() {
    return btnSummonMonster;
  }

  public JButton getBtnSetMonster() {
    return btnSetMonster;
  }

  public JButton getBtnActivateSpell() {
    return btnActivateSpell;
  }

  public JButton getBtnSetSpell() {
    return btnSetSpell;
  }
}
