package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import board.Board;
import board.player.Phase;
import board.player.Player;
import cards.Card;
import cards.Mode;
import cards.MonsterCard;
import cards.spells.ChangeOfHeart;
import cards.spells.MagePower;
import cards.spells.SpellCard;
import exceptions.DefenseMonsterAttackException;
import exceptions.MonsterMultipleAttackException;
import exceptions.MultipleMonsterAdditionException;
import exceptions.NoMonsterSpaceException;
import exceptions.NoSpellSpaceException;
import exceptions.UnexpectedFormatException;
import exceptions.WrongPhaseException;
import gui.ButtonDeck;
import gui.ButtonDuelMode;
import gui.ButtonEndTurn;
import gui.ButtonHand;
import gui.ButtonMonster;
import gui.ButtonNextPhase;
import gui.ButtonPlayAgain;
import gui.ButtonQuitGame;
import gui.ButtonSpell;
import gui.GUI;
import gui.PlayerHand;

public class Controller implements ActionListener, WindowListener {

	Board board;
	GUI gui;

	JButton firstClick;
	JButton secondClick;
	JButton thirdClick;
	JButton fourthClick;

	MonsterCard monster = null;
	SpellCard spell = null;
	ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>(2);

	public Controller(Board board, GUI gui) {

		this.board = board;
		this.gui = gui;
		addActionListenersToButtons();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof ButtonDuelMode) {

			Player p1 = null;
			Player p2 = null;

			try {
				if (!(gui.getStartWindow().getTxtFldPlayer1().getText().equals("")
						|| gui.getStartWindow().getTxtFldPlayer1().getText().equals(" ")))
					p1 = new Player(gui.getStartWindow().getTxtFldPlayer1().getText());
				else
					p1 = new Player("Yugi");

				if (!(gui.getStartWindow().getTxtFldPlayer2().getText().equals("")
						|| gui.getStartWindow().getTxtFldPlayer2().getText().equals(" ")))
					p2 = new Player(gui.getStartWindow().getTxtFldPlayer2().getText());
				else
					p2 = new Player("Kaiba");
			} catch (IOException | UnexpectedFormatException e1) {
				e1.printStackTrace();
			}

			board.startGame(p1, p2);
			gui.getStartWindow().setVisible(false);

			gui.getGameWindow().setVisible(true);

			gui.getGameWindow().getLblPlayer1Name().setText(p1.getName());
			gui.getGameWindow().getLblPlayer2Name().setText(p2.getName());

			if (board.getActivePlayer().getName() == p1.getName())
				disable2Enable1();

			else
				disable1Enable2();

		}

		if (e.getSource() instanceof ButtonQuitGame)
			System.exit(0);

		if (e.getSource() instanceof ButtonNextPhase) {

			if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText()) {

				if (board.getActivePlayer().getField().getPhase() != Phase.MAIN2) {
					board.getActivePlayer().endPhase();
					gui.getGameWindow().getLblPlayer1Phase()
							.setText(board.getActivePlayer().getField().getPhase() + "");
					gui.getGameWindow().getLblPlayer2Phase()
							.setText(board.getOpponentPlayer().getField().getPhase() + "");
				}

				else {
					board.getActivePlayer().endPhase();
					gui.getGameWindow().getLblPlayer2Phase()
							.setText(board.getActivePlayer().getField().getPhase() + "");
					gui.getGameWindow().getLblPlayer1Phase()
							.setText(board.getOpponentPlayer().getField().getPhase() + "");

					disable1Enable2();
				}

			}

			else {

				if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer2Name().getText()) {

					if (board.getActivePlayer().getField().getPhase() != Phase.MAIN2) {
						board.getActivePlayer().endPhase();
						gui.getGameWindow().getLblPlayer2Phase()
								.setText(board.getActivePlayer().getField().getPhase() + "");
						gui.getGameWindow().getLblPlayer1Phase()
								.setText(board.getOpponentPlayer().getField().getPhase() + "");
					}

					else {
						board.getActivePlayer().endPhase();
						gui.getGameWindow().getLblPlayer1Phase()
								.setText(board.getActivePlayer().getField().getPhase() + "");
						gui.getGameWindow().getLblPlayer2Phase()
								.setText(board.getOpponentPlayer().getField().getPhase() + "");

						disable2Enable1();
					}

				}

			}

			nullifyAttributes();

		}

		if (e.getSource() instanceof ButtonEndTurn) {

			if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText()) {

				board.getActivePlayer().endTurn();
				gui.getGameWindow().getLblPlayer2Phase().setText(board.getActivePlayer().getField().getPhase() + "");
				gui.getGameWindow().getLblPlayer1Phase().setText(board.getOpponentPlayer().getField().getPhase() + "");

				disable1Enable2();

			}

			else {

				if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer2Name().getText()) {

					board.getActivePlayer().endTurn();
					gui.getGameWindow().getLblPlayer1Phase()
							.setText(board.getActivePlayer().getField().getPhase() + "");
					gui.getGameWindow().getLblPlayer2Phase()
							.setText(board.getOpponentPlayer().getField().getPhase() + "");

					disable2Enable1();

				}

			}

			nullifyAttributes();

		}

		if (e.getActionCommand().equals("Hand")) {

			updateHand();

			nullifyAttributes();

			if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText())
				gui.getHandPlayer1().setVisible(true);

			else
				gui.getHandPlayer2().setVisible(true);

		}

		if (firstClick == null) {

			if (e.getSource() instanceof ButtonHand) {

				firstClick = (ButtonHand) e.getSource();

				if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText()) {

					int index = gui.getHandPlayer1().getArrayCards().indexOf(firstClick);
					Card card = board.getActivePlayer().getField().getHand().get(index);

					if (card instanceof MonsterCard) {
						monster = (MonsterCard) card;
						gui.getHandPlayer1().getBtnSetMonster().setEnabled(true);
						gui.getHandPlayer1().getBtnSummonMonster().setEnabled(true);
					}

					else {
						spell = (SpellCard) card;
						gui.getHandPlayer1().getBtnActivateSpell().setEnabled(true);
						gui.getHandPlayer1().getBtnSetSpell().setEnabled(true);
					}

				}

				else {

					int index = gui.getHandPlayer2().getArrayCards().indexOf(firstClick);
					Card card = board.getActivePlayer().getField().getHand().get(index);

					if (card instanceof MonsterCard) {
						monster = (MonsterCard) card;
						gui.getHandPlayer2().getBtnSetMonster().setEnabled(true);
						gui.getHandPlayer2().getBtnSummonMonster().setEnabled(true);
					}

					else {
						spell = (SpellCard) card;
						gui.getHandPlayer2().getBtnActivateSpell().setEnabled(true);
						gui.getHandPlayer2().getBtnSetSpell().setEnabled(true);
					}

				}

			}

			else {

				if (e.getSource() instanceof ButtonSpell) {

					firstClick = (ButtonSpell) e.getSource();
					int index = 0;

					if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText()) {

						index = gui.getGameWindow().getArrayPlayer1Spells().indexOf(firstClick);

						try {
							spell = board.getActivePlayer().getField().getSpellArea().get(index);
						} catch (ArrayIndexOutOfBoundsException c) {
							nullifyAttributes();
						}

						if (!(spell instanceof MagePower || spell instanceof ChangeOfHeart)) {

							try {
								board.getActivePlayer().activateSpell(spell, null);
							} catch (WrongPhaseException e1) {
								JOptionPane.showMessageDialog(null,
										"You can't activate this spell in the battle phase");
							} catch (NullPointerException e1) {
								nullifyAttributes();
							} finally {
								nullifyAttributes();
							}

						}

						else {

							if (spell instanceof MagePower) {

								if (board.getOpponentPlayer().getField().getMonstersArea().size() > 0
										|| board.getActivePlayer().getField().getMonstersArea().size() > 0)

									JOptionPane.showMessageDialog(null,
											"Choose the monster for the spell to be acted upon");

								else {

									JOptionPane.showMessageDialog(null, "No monsters available for spell activation");
									nullifyAttributes();

								}

							}

							else {

								if (spell instanceof ChangeOfHeart) {

									if (board.getOpponentPlayer().getField().getMonstersArea().size() > 0)

										JOptionPane.showMessageDialog(null,
												"Choose the monster for the spell to be acted upon");

									else {

										JOptionPane.showMessageDialog(null,
												"No monsters available for spell activation");
										nullifyAttributes();

									}

								}

							}

						}

					}

					else {

						index = gui.getGameWindow().getArrayPlayer2Spells().indexOf(firstClick);

						try {
							spell = board.getActivePlayer().getField().getSpellArea().get(index);
						} catch (ArrayIndexOutOfBoundsException c) {
							nullifyAttributes();
						}

						if (!(spell instanceof MagePower || spell instanceof ChangeOfHeart)) {

							try {
								board.getActivePlayer().activateSpell(spell, null);
							} catch (WrongPhaseException e1) {
								JOptionPane.showMessageDialog(null,
										"You can't activate this spell in the battle phase");
							} catch (NullPointerException e1) {
								nullifyAttributes();
							} finally {
								nullifyAttributes();
							}

						}

						else {

							if (spell instanceof MagePower) {

								if (board.getOpponentPlayer().getField().getMonstersArea().size() > 0
										|| board.getActivePlayer().getField().getMonstersArea().size() > 0)

									JOptionPane.showMessageDialog(null,
											"Choose the monster for the spell to be acted upon");

								else {

									JOptionPane.showMessageDialog(null, "No monsters available for spell activation");
									nullifyAttributes();

								}
							}

							else {

								if (spell instanceof ChangeOfHeart) {

									if (board.getOpponentPlayer().getField().getMonstersArea().size() > 0)

										JOptionPane.showMessageDialog(null,
												"Choose the monster for the spell to be acted upon");

									else {

										JOptionPane.showMessageDialog(null,
												"No monsters available for spell activation");
										nullifyAttributes();

									}

								}

							}

						}

					}

				}

				else {

					if (e.getSource() instanceof ButtonMonster) {

						firstClick = (ButtonMonster) e.getSource();
						int index = 0;

						if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText()) {

							index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(firstClick);

							try {
								monster = board.getActivePlayer().getField().getMonstersArea().get(index);
							} catch (ArrayIndexOutOfBoundsException c) {
								nullifyAttributes();
							}

							if (board.getActivePlayer().getField().getPhase() != Phase.BATTLE) {

								int dialogButton = JOptionPane.YES_NO_OPTION;
								int dialogResult = JOptionPane.showConfirmDialog(null, "Toggle mode?", "Toggle Mode",
										dialogButton);

								if (dialogResult == JOptionPane.YES_OPTION) {

									boolean opSuccessful = false;

									try {
										opSuccessful = board.getActivePlayer().switchMonsterMode(monster);
									} catch (NullPointerException c) {
										nullifyAttributes();
									}

									if (!opSuccessful)

										JOptionPane.showMessageDialog(null,
												"You can't change the mode of the monster again this turn");

									nullifyAttributes();

								}

							}

							else {

								if (board.getOpponentPlayer().getField().getMonstersArea().size() == 0) {

									try {
										board.getActivePlayer().declareAttack(monster);
									} catch (MonsterMultipleAttackException c) {
										JOptionPane.showMessageDialog(null, "You already attacked with this monster");
									} catch (DefenseMonsterAttackException c) {
										JOptionPane.showMessageDialog(null,
												"You cannot attack with a monster in defense mode");
									} catch (ArrayIndexOutOfBoundsException c) {
										nullifyAttributes();
									} finally {
										nullifyAttributes();
									}

								}

							}

						}

						else {

							index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(firstClick);

							try {
								monster = board.getActivePlayer().getField().getMonstersArea().get(index);
							} catch (ArrayIndexOutOfBoundsException c) {
								nullifyAttributes();
							}

							if (board.getActivePlayer().getField().getPhase() != Phase.BATTLE) {

								int dialogButton = JOptionPane.YES_NO_OPTION;
								int dialogResult = JOptionPane.showConfirmDialog(null, "Toggle mode?", "Toggle Mode",
										dialogButton);

								if (dialogResult == JOptionPane.YES_OPTION) {

									boolean opSuccessful = false;

									try {
										opSuccessful = board.getActivePlayer().switchMonsterMode(monster);
									} catch (NullPointerException c) {
										nullifyAttributes();
									}

									if (!opSuccessful)

										JOptionPane.showMessageDialog(null,
												"You can't change the mode of the monster again this turn");

									nullifyAttributes();

								} else
									nullifyAttributes();

							}

							else {

								if (board.getOpponentPlayer().getField().getMonstersArea().size() == 0) {

									try {
										board.getActivePlayer().declareAttack(monster);
									} catch (MonsterMultipleAttackException c) {
										JOptionPane.showMessageDialog(null, "You already attacked with this monster");
									} catch (DefenseMonsterAttackException c) {
										JOptionPane.showMessageDialog(null,
												"You cannot attack with a monster in defense mode");
									} catch (ArrayIndexOutOfBoundsException c) {
										nullifyAttributes();
									} finally {
										nullifyAttributes();
									}

								}

							}

						}

					}

				}

			}

		}

		else {

			if (secondClick == null) {

				if (e.getSource() instanceof ButtonHand) {

					firstClick = (ButtonHand) e.getSource();

					if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText()) {

						int index = gui.getHandPlayer1().getArrayCards().indexOf(firstClick);

						Card card = null;

						try {
							card = board.getActivePlayer().getField().getHand().get(index);
						} catch (ArrayIndexOutOfBoundsException c) {
							nullifyAttributes();
						}

						if (card instanceof MonsterCard) {
							monster = (MonsterCard) card;
							gui.getHandPlayer1().getBtnSetMonster().setEnabled(true);
							gui.getHandPlayer1().getBtnSummonMonster().setEnabled(true);
							gui.getHandPlayer1().getBtnActivateSpell().setEnabled(false);
							gui.getHandPlayer1().getBtnSetSpell().setEnabled(false);
						}

						else {
							spell = (SpellCard) card;
							gui.getHandPlayer1().getBtnActivateSpell().setEnabled(true);
							gui.getHandPlayer1().getBtnSetSpell().setEnabled(true);
							gui.getHandPlayer1().getBtnSetMonster().setEnabled(false);
							gui.getHandPlayer1().getBtnSummonMonster().setEnabled(false);
						}

					}

					else {

						int index = gui.getHandPlayer2().getArrayCards().indexOf(firstClick);

						Card card = null;

						try {
							card = board.getActivePlayer().getField().getHand().get(index);
						} catch (ArrayIndexOutOfBoundsException c) {
							nullifyAttributes();
						}

						if (card instanceof MonsterCard) {
							monster = (MonsterCard) card;
							gui.getHandPlayer2().getBtnSetMonster().setEnabled(true);
							gui.getHandPlayer2().getBtnSummonMonster().setEnabled(true);
							gui.getHandPlayer2().getBtnActivateSpell().setEnabled(false);
							gui.getHandPlayer2().getBtnSetSpell().setEnabled(false);
						}

						else {
							spell = (SpellCard) card;
							gui.getHandPlayer2().getBtnActivateSpell().setEnabled(true);
							gui.getHandPlayer2().getBtnSetSpell().setEnabled(true);
							gui.getHandPlayer2().getBtnSetMonster().setEnabled(false);
							gui.getHandPlayer2().getBtnSummonMonster().setEnabled(false);
						}

					}

				}

				else {

					secondClick = (JButton) e.getSource();

					if (secondClick.getActionCommand().equals("Summon Monster")) {

						if (monster.getLevel() <= 4) {

							try {
								board.getActivePlayer().summonMonster(monster);
							} catch (NoMonsterSpaceException c) {
								JOptionPane.showMessageDialog(null, "There is no space in the monsters area");
							} catch (WrongPhaseException c) {
								JOptionPane.showMessageDialog(null, "You cannot summon a monster in the battle phase");
							} catch (MultipleMonsterAdditionException c) {
								JOptionPane.showMessageDialog(null, "You already added a monster this turn");
							} finally {
								nullifyAttributes();
							}

						}

						else {

							if (monster.getLevel() == 5 || monster.getLevel() == 6) {

								if (board.getActivePlayer().getField().getMonstersArea().size() > 0)
									JOptionPane.showMessageDialog(null, "Choose 1 sacrifice");

								else {

									JOptionPane.showMessageDialog(null, "Not enough sacrifices in field");
									nullifyAttributes();

								}

							}

							else {

								if (monster.getLevel() == 7 || monster.getLevel() == 8) {

									if (board.getActivePlayer().getField().getMonstersArea().size() > 1)
										JOptionPane.showMessageDialog(null, "Choose 2 sacrifices");

									else {

										JOptionPane.showMessageDialog(null, "Not enough sacrifices in field");
										nullifyAttributes();

									}

								}

							}

						}

					}

					else {

						if (secondClick.getActionCommand().equals("Set Monster")) {

							if (monster.getLevel() <= 4) {

								try {
									board.getActivePlayer().setMonster(monster);
								} catch (NoMonsterSpaceException c) {
									JOptionPane.showMessageDialog(null, "There is no space in the monsters area");
								} catch (WrongPhaseException c) {
									JOptionPane.showMessageDialog(null, "You cannot set a monster in the battle phase");
								} catch (MultipleMonsterAdditionException c) {
									JOptionPane.showMessageDialog(null, "You already added a monster this turn");
								} finally {
									nullifyAttributes();
								}

							}

							else {

								if (monster.getLevel() == 5 || monster.getLevel() == 6) {

									if (board.getActivePlayer().getField().getMonstersArea().size() > 0)
										JOptionPane.showMessageDialog(null, "Choose 1 sacrifice");

									else {

										JOptionPane.showMessageDialog(null, "Not enough sacrifices in field");
										nullifyAttributes();

									}

								}

								else {

									if (monster.getLevel() == 7 || monster.getLevel() == 8)

										if (board.getActivePlayer().getField().getMonstersArea().size() > 1)
											JOptionPane.showMessageDialog(null, "Choose 2 sacrifices");

										else {

											JOptionPane.showMessageDialog(null, "Not enough sacrifices in field");
											nullifyAttributes();

										}
								}

							}

						}

						else {

							if (secondClick.getActionCommand().equals("Set Spell")) {

								try {
									board.getActivePlayer().setSpell(spell);
								} catch (NoSpellSpaceException c) {
									JOptionPane.showMessageDialog(null, "There is no space in the spells area");
								} catch (WrongPhaseException c) {
									JOptionPane.showMessageDialog(null,
											"You cannot add this spell in the battle phase");
								}

								finally {
									nullifyAttributes();
								}

							}

							else {

								if (secondClick.getActionCommand().equals("Activate Spell")) {

									if (!(spell instanceof ChangeOfHeart || spell instanceof MagePower)) {

										try {
											board.getActivePlayer().activateSpell(spell, null);
										} catch (WrongPhaseException c) {
											JOptionPane.showMessageDialog(null,
													"You cannot activate this spell in the battle phase");
										} finally {
											nullifyAttributes();
										}

									}

									else {

										if (spell instanceof MagePower) {

											if (board.getActivePlayer().getField().getMonstersArea().size() > 0 || board
													.getOpponentPlayer().getField().getMonstersArea().size() > 0)
												JOptionPane.showMessageDialog(null,
														"Choose the monster for the spell to be acted upon");

											else {

												JOptionPane.showMessageDialog(null, "No monsters available");
												nullifyAttributes();

											}
										}

										else {

											if (spell instanceof ChangeOfHeart) {

												if (board.getOpponentPlayer().getField().getMonstersArea().size() > 0)
													JOptionPane.showMessageDialog(null,
															"Choose the monster for the spell to be acted upon");

												else {

													JOptionPane.showMessageDialog(null, "No monsters available");
													nullifyAttributes();

												}

											}

										}

									}

								}

								else {

									if (firstClick instanceof ButtonSpell) {

										if (e.getSource() instanceof ButtonMonster) {

											secondClick = (ButtonMonster) e.getSource();
											int index = 0;

											if (spell instanceof ChangeOfHeart) {

												MonsterCard monsterSpelled = null;

												if (board.getActivePlayer().getName() == gui.getGameWindow()
														.getLblPlayer1Name().getText())
													index = gui.getGameWindow().getArrayPlayer2Monsters()
															.indexOf(secondClick);

												else
													index = gui.getGameWindow().getArrayPlayer1Monsters()
															.indexOf(secondClick);

												if (index == -1)
													secondClick = null;

												else {

													monsterSpelled = board.getOpponentPlayer().getField()
															.getMonstersArea().get(index);

													try {
														board.getActivePlayer().activateSpell(spell, monsterSpelled);
													} catch (WrongPhaseException c) {
														JOptionPane.showMessageDialog(null,
																"You cannot activate this spell in the battle phase");
													}

													finally {
														nullifyAttributes();
													}

												}

											}

											if (spell instanceof MagePower) {

												MonsterCard monsterSpelled = null;

												if (board.getActivePlayer().getName() == gui.getGameWindow()
														.getLblPlayer1Name().getText()) {

													index = gui.getGameWindow().getArrayPlayer1Monsters()
															.indexOf(secondClick);

													if (index != -1)
														monsterSpelled = board.getActivePlayer().getField()
																.getMonstersArea().get(index);

													else {

														index = gui.getGameWindow().getArrayPlayer2Monsters()
																.indexOf(secondClick);
														monsterSpelled = board.getOpponentPlayer().getField()
																.getMonstersArea().get(index);

													}

												}

												else {

													index = gui.getGameWindow().getArrayPlayer2Monsters()
															.indexOf(secondClick);

													if (index != -1)
														monsterSpelled = board.getActivePlayer().getField()
																.getMonstersArea().get(index);

													else {

														index = gui.getGameWindow().getArrayPlayer1Monsters()
																.indexOf(secondClick);
														monsterSpelled = board.getOpponentPlayer().getField()
																.getMonstersArea().get(index);

													}

												}

												board.getActivePlayer().activateSpell(spell, monsterSpelled);
												nullifyAttributes();

											}

										}

										else {

											JOptionPane.showMessageDialog(thirdClick, "You have to choose a monster");
											nullifyAttributes();

										}

									}

									else {

										if (firstClick instanceof ButtonMonster) {

											if (e.getSource() instanceof ButtonMonster) {

												secondClick = (ButtonMonster) e.getSource();
												int index = 0;

												if (board.getActivePlayer().getName() == gui.getGameWindow()
														.getLblPlayer1Name().getText())
													index = gui.getGameWindow().getArrayPlayer2Monsters()
															.indexOf(secondClick);

												else
													index = gui.getGameWindow().getArrayPlayer1Monsters()
															.indexOf(secondClick);

												if (index != -1) {

													MonsterCard monsterAtt = board.getOpponentPlayer().getField()
															.getMonstersArea().get(index);

													try {
														board.getActivePlayer().declareAttack(monster, monsterAtt);
													} catch (MonsterMultipleAttackException c) {
														JOptionPane.showMessageDialog(null,
																"You already attacked with this monster");
													} catch (DefenseMonsterAttackException c) {
														JOptionPane.showMessageDialog(null,
																"You cannot attack with a monster in defense mode");
													} finally {
														nullifyAttributes();
													}

												}

												nullifyAttributes();

											}

											else {

												if (e.getSource() instanceof ButtonSpell) {

													JOptionPane.showMessageDialog(null,
															"You cannot attack a spellcard");
													nullifyAttributes();

												}
											}

										}

									}

								}

							}

						}

					}

					gui.getHandPlayer1().setVisible(false);
					gui.getHandPlayer2().setVisible(false);

				}

			}

			else {

				if (secondClick.getActionCommand().equals("Summon Monster")) {

					if (monster.getLevel() == 5 || monster.getLevel() == 6) {

						gui.getHandPlayer1().setVisible(false);
						gui.getHandPlayer2().setVisible(false);

						if (thirdClick == null) {

							if (e.getSource() instanceof ButtonMonster) {

								thirdClick = (ButtonMonster) e.getSource();

								int index = 0;

								if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name()
										.getText())
									index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(thirdClick);
								else
									index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(thirdClick);

								MonsterCard sacrifice1 = null;

								try {
									sacrifice1 = board.getActivePlayer().getField().getMonstersArea().get(index);
								} catch (ArrayIndexOutOfBoundsException c) {
									nullifyAttributes();
								}

								sacrifices.add(sacrifice1);

								try {
									board.getActivePlayer().summonMonster(monster, sacrifices);
								} catch (NoMonsterSpaceException c) {
									JOptionPane.showMessageDialog(null, "There is no space in the monsters area");
								} catch (WrongPhaseException c) {
									JOptionPane.showMessageDialog(null, "You cannot summon in the battle phase");
								} catch (MultipleMonsterAdditionException c) {
									JOptionPane.showMessageDialog(null, "You already added a monster this turn");
								} catch (NullPointerException c) {
									nullifyAttributes();
								} finally {
									nullifyAttributes();
								}

							}

						}

					}

					else {

						if (monster.getLevel() == 7 || monster.getLevel() == 8) {

							gui.getHandPlayer1().setVisible(false);
							gui.getHandPlayer2().setVisible(false);

							if (thirdClick == null) {

								if (e.getSource() instanceof ButtonMonster) {

									thirdClick = (ButtonMonster) e.getSource();

									int index = 0;

									if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name()
											.getText())
										index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(thirdClick);
									else
										index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(thirdClick);

									MonsterCard sacrifice1 = null;

									try {
										sacrifice1 = board.getActivePlayer().getField().getMonstersArea().get(index);
									} catch (ArrayIndexOutOfBoundsException c) {
										nullifyAttributes();
									}

									sacrifices.add(sacrifice1);

								}

							}

							else {

								if (e.getSource() instanceof ButtonMonster) {

									fourthClick = (ButtonMonster) e.getSource();

									int index = 0;

									if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name()
											.getText())
										index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(fourthClick);
									else
										index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(fourthClick);

									MonsterCard sacrifice2 = null;

									try {
										sacrifice2 = board.getActivePlayer().getField().getMonstersArea().get(index);
									} catch (ArrayIndexOutOfBoundsException c) {
										nullifyAttributes();
									}

									sacrifices.add(sacrifice2);

									try {
										board.getActivePlayer().summonMonster(monster, sacrifices);
									} catch (NoMonsterSpaceException c) {
										JOptionPane.showMessageDialog(null, "There is no space in the monsters area");
									} catch (WrongPhaseException c) {
										JOptionPane.showMessageDialog(null, "You cannot summon in the battle phase");
									} catch (MultipleMonsterAdditionException c) {
										JOptionPane.showMessageDialog(null, "You already added a monster this turn");
									} catch (NullPointerException c) {
										nullifyAttributes();
									} finally {
										nullifyAttributes();
									}

								}

							}

						}

					}

				}

				else {

					if (secondClick.getActionCommand().equals("Set Monster")) {

						if (monster.getLevel() == 5 || monster.getLevel() == 6) {

							gui.getHandPlayer1().setVisible(false);
							gui.getHandPlayer2().setVisible(false);

							if (thirdClick == null) {

								if (e.getSource() instanceof ButtonMonster) {

									thirdClick = (ButtonMonster) e.getSource();

									int index = 0;

									if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name()
											.getText())
										index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(thirdClick);
									else
										index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(thirdClick);

									MonsterCard sacrifice1 = null;

									try {
										sacrifice1 = board.getActivePlayer().getField().getMonstersArea().get(index);
									} catch (ArrayIndexOutOfBoundsException c) {
										nullifyAttributes();
									}

									sacrifices.add(sacrifice1);

									try {
										board.getActivePlayer().setMonster(monster, sacrifices);
									} catch (NoMonsterSpaceException c) {
										JOptionPane.showMessageDialog(null, "There is no space in the monsters area");
									} catch (WrongPhaseException c) {
										JOptionPane.showMessageDialog(null, "You cannot set in the battle phase");
									} catch (MultipleMonsterAdditionException c) {
										JOptionPane.showMessageDialog(null, "You already added a monster this turn");
									} catch (NullPointerException c) {
										nullifyAttributes();
									} finally {
										nullifyAttributes();
									}

								}

							}

						}

						else {

							if (monster.getLevel() == 7 || monster.getLevel() == 8) {

								gui.getHandPlayer1().setVisible(false);
								gui.getHandPlayer2().setVisible(false);

								if (thirdClick == null) {

									if (e.getSource() instanceof ButtonMonster) {

										thirdClick = (ButtonMonster) e.getSource();

										int index = 0;

										if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name()
												.getText())
											index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(thirdClick);
										else
											index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(thirdClick);

										MonsterCard sacrifice1 = null;

										try {
											sacrifice1 = board.getActivePlayer().getField().getMonstersArea()
													.get(index);
										} catch (ArrayIndexOutOfBoundsException c) {
											nullifyAttributes();
										}

										sacrifices.add(sacrifice1);

									}

								}

								else {

									if (e.getSource() instanceof ButtonMonster) {

										fourthClick = (ButtonMonster) e.getSource();

										int index = 0;

										if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name()
												.getText())
											index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(fourthClick);
										else
											index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(fourthClick);

										MonsterCard sacrifice2 = null;

										try {
											sacrifice2 = board.getActivePlayer().getField().getMonstersArea()
													.get(index);
										} catch (ArrayIndexOutOfBoundsException c) {
											nullifyAttributes();
										}

										sacrifices.add(sacrifice2);

										try {
											board.getActivePlayer().setMonster(monster, sacrifices);
										} catch (NoMonsterSpaceException c) {
											JOptionPane.showMessageDialog(null,
													"There is no space in the monsters area");
										} catch (WrongPhaseException c) {
											JOptionPane.showMessageDialog(null, "You cannot set in the battle phase");
										} catch (MultipleMonsterAdditionException c) {
											JOptionPane.showMessageDialog(null,
													"You already added a monster this turn");
										} catch (NullPointerException c) {
											nullifyAttributes();
										} finally {
											nullifyAttributes();
										}

									}

								}

							}

						}

					}

					else {

						if (secondClick.getActionCommand().equals("Activate Spell")) {

							gui.getHandPlayer1().setVisible(false);
							gui.getHandPlayer2().setVisible(false);

							if (thirdClick == null) {

								if (e.getSource() instanceof ButtonMonster) {

									thirdClick = (ButtonMonster) e.getSource();
									int index = 0;

									if (spell instanceof ChangeOfHeart) {

										MonsterCard monsterSpelled = null;

										if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name()
												.getText())
											index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(thirdClick);

										else
											index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(thirdClick);

										if (index == -1)
											thirdClick = null;

										else {

											monsterSpelled = board.getOpponentPlayer().getField().getMonstersArea()
													.get(index);

											try {
												board.getActivePlayer().activateSpell(spell, monsterSpelled);
											} catch (WrongPhaseException c) {
												JOptionPane.showMessageDialog(null,
														"You cannot activate this spell in the battle phase");
											}

											finally {
												nullifyAttributes();
											}

										}

									}

									if (spell instanceof MagePower) {

										MonsterCard monsterSpelled = null;

										if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name()
												.getText()) {

											index = gui.getGameWindow().getArrayPlayer1Monsters().indexOf(thirdClick);

											if (index != -1)
												monsterSpelled = board.getActivePlayer().getField().getMonstersArea()
														.get(index);

											else {

												index = gui.getGameWindow().getArrayPlayer2Monsters()
														.indexOf(thirdClick);
												monsterSpelled = board.getOpponentPlayer().getField().getMonstersArea()
														.get(index);

											}

										}

										else {

											index = gui.getGameWindow().getArrayPlayer2Monsters().indexOf(thirdClick);

											if (index != -1)
												monsterSpelled = board.getActivePlayer().getField().getMonstersArea()
														.get(index);

											else {

												index = gui.getGameWindow().getArrayPlayer1Monsters()
														.indexOf(thirdClick);
												monsterSpelled = board.getOpponentPlayer().getField().getMonstersArea()
														.get(index);

											}

										}

										board.getActivePlayer().activateSpell(spell, monsterSpelled);
										nullifyAttributes();
									}

								}

								else {

									JOptionPane.showMessageDialog(thirdClick, "You have to choose a monster");
									nullifyAttributes();

								}

							}

						}

					}

				}

			}

		}

		if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText()) {

			gui.getGameWindow().getLblPlayer1LifePoints().setText("" + (board.getActivePlayer().getLifePoints()));
			gui.getGameWindow().getLblPlayer2LifePoints().setText("" + (board.getOpponentPlayer().getLifePoints()));
			gui.getGameWindow().getLblPlayer1DeckCount()
					.setText("" + board.getActivePlayer().getField().getDeck().getDeck().size());
			gui.getGameWindow().getLblPlayer2DeckCount()
					.setText("" + board.getOpponentPlayer().getField().getDeck().getDeck().size());

			gui.getGameWindow().getProgressBarPlayer2()
					.setValue(Integer.parseInt(gui.getGameWindow().getLblPlayer2LifePoints().getText()));
			gui.getGameWindow().getProgressBarPlayer1()
					.setValue(Integer.parseInt(gui.getGameWindow().getLblPlayer1LifePoints().getText()));

			if (board.getActivePlayer().getField().getGraveyard().size() == 0)
				gui.getGameWindow().getBtnPlayer1Graveyard().setIcon(new ImageIcon("src/resources/CardBack.png"));

			else {

				Card card = board.getActivePlayer().getField().getGraveyard()
						.get(board.getActivePlayer().getField().getGraveyard().size() - 1);
				gui.getGameWindow().getBtnPlayer1Graveyard()
						.setIcon(new ImageIcon("src/resources/" + card.getName() + ".png"));

				String text = "";

				if (card instanceof MonsterCard)
					text = ((MonsterCard) card).toString();
				else
					text = ((SpellCard) card).toString();

				gui.getGameWindow().getBtnPlayer1Graveyard()
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

			if (board.getOpponentPlayer().getField().getGraveyard().size() == 0)
				gui.getGameWindow().getBtnPlayer2Graveyard().setIcon(new ImageIcon("src/resources/CardBack.png"));

			else {

				Card card = board.getOpponentPlayer().getField().getGraveyard()
						.get(board.getOpponentPlayer().getField().getGraveyard().size() - 1);
				gui.getGameWindow().getBtnPlayer2Graveyard()
						.setIcon(new ImageIcon("src/resources/" + card.getName() + ".png"));

				String text = "";

				if (card instanceof MonsterCard)
					text = ((MonsterCard) card).toString();
				else
					text = ((SpellCard) card).toString();

				gui.getGameWindow().getBtnPlayer2Graveyard()
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

			for (int i = 0; i < 5; i++) {
				gui.getGameWindow().getArrayPlayer1Monsters().get(i).setIcon(null);
				gui.getGameWindow().getArrayPlayer1Monsters().get(i).setEnabled(false);
			}

			for (int i = 0; i < board.getActivePlayer().getField().getMonstersArea().size(); i++) {

				String cardName = board.getActivePlayer().getField().getMonstersArea().get(i).getName();
				MonsterCard card = board.getActivePlayer().getField().getMonstersArea().get(i);

				String text = "";

				if (card.getMode() == Mode.DEFENSE)
					gui.getGameWindow().getArrayPlayer1Monsters().get(i)
							.setIcon(new ImageIcon("src/resources/CardBack.png"));

				else
					gui.getGameWindow().getArrayPlayer1Monsters().get(i)
							.setIcon(new ImageIcon("src/resources/" + cardName + ".png"));

				gui.getGameWindow().getArrayPlayer1Monsters().get(i).setEnabled(true);
				text = card.toString2();
				gui.getGameWindow().getArrayPlayer1Monsters().get(i)
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

			for (int i = 0; i < 5; i++) {
				gui.getGameWindow().getArrayPlayer2Monsters().get(i).setIcon(null);
				gui.getGameWindow().getArrayPlayer2Monsters().get(i).setEnabled(false);
			}

			for (int i = 0; i < board.getOpponentPlayer().getField().getMonstersArea().size(); i++) {

				String cardName = board.getOpponentPlayer().getField().getMonstersArea().get(i).getName();
				MonsterCard card = board.getOpponentPlayer().getField().getMonstersArea().get(i);
				String text = "";

				if (card.getMode() == Mode.DEFENSE) {

					gui.getGameWindow().getArrayPlayer2Monsters().get(i)
							.setIcon(new ImageIcon("src/resources/CardBack.png"));
					gui.getGameWindow().getArrayPlayer2Monsters().get(i).setEnabled(true);
					gui.getGameWindow().getArrayPlayer2Monsters().get(i).setToolTipText("" + card.getMode());

				}

				else {

					gui.getGameWindow().getArrayPlayer2Monsters().get(i)
							.setIcon(new ImageIcon("src/resources/" + cardName + ".png"));
					gui.getGameWindow().getArrayPlayer2Monsters().get(i).setEnabled(true);
					text = card.toString2();
					gui.getGameWindow().getArrayPlayer2Monsters().get(i)
							.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

				}

			}

			for (int i = 0; i < 5; i++) {
				gui.getGameWindow().getArrayPlayer1Spells().get(i).setIcon(null);
				gui.getGameWindow().getArrayPlayer1Spells().get(i).setEnabled(false);
			}

			for (int i = 0; i < board.getActivePlayer().getField().getSpellArea().size(); i++) {

				gui.getGameWindow().getArrayPlayer1Spells().get(i).setIcon(new ImageIcon("src/resources/CardBack.png"));
				gui.getGameWindow().getArrayPlayer1Spells().get(i).setEnabled(true);

				String text = "";
				SpellCard card = board.getActivePlayer().getField().getSpellArea().get(i);

				text = card.toString();
				gui.getGameWindow().getArrayPlayer1Spells().get(i)
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

			for (int i = 0; i < 5; i++) {
				gui.getGameWindow().getArrayPlayer2Spells().get(i).setIcon(null);
				gui.getGameWindow().getArrayPlayer2Spells().get(i).setEnabled(false);
			}

			for (int i = 0; i < board.getOpponentPlayer().getField().getSpellArea().size(); i++) {

				gui.getGameWindow().getArrayPlayer2Spells().get(i).setIcon(new ImageIcon("src/resources/CardBack.png"));
				gui.getGameWindow().getArrayPlayer2Spells().get(i).setEnabled(true);

				String text = "";

				gui.getGameWindow().getArrayPlayer2Spells().get(i)
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

		}

		else {

			gui.getGameWindow().getLblPlayer2LifePoints().setText("" + (board.getActivePlayer().getLifePoints()));
			gui.getGameWindow().getLblPlayer1LifePoints().setText("" + (board.getOpponentPlayer().getLifePoints()));
			gui.getGameWindow().getLblPlayer2DeckCount()
					.setText("" + board.getActivePlayer().getField().getDeck().getDeck().size());
			gui.getGameWindow().getLblPlayer1DeckCount()
					.setText("" + board.getOpponentPlayer().getField().getDeck().getDeck().size());

			gui.getGameWindow().getProgressBarPlayer2()
					.setValue(Integer.parseInt(gui.getGameWindow().getLblPlayer2LifePoints().getText()));
			gui.getGameWindow().getProgressBarPlayer1()
					.setValue(Integer.parseInt(gui.getGameWindow().getLblPlayer1LifePoints().getText()));

			if (board.getActivePlayer().getField().getGraveyard().size() == 0)
				gui.getGameWindow().getBtnPlayer2Graveyard().setIcon(new ImageIcon("src/resources/CardBack.png"));

			else {

				Card card = board.getActivePlayer().getField().getGraveyard()
						.get(board.getActivePlayer().getField().getGraveyard().size() - 1);
				gui.getGameWindow().getBtnPlayer2Graveyard()
						.setIcon(new ImageIcon("src/resources/" + card.getName() + ".png"));

				String text = "";
				if (card instanceof MonsterCard)
					text = ((MonsterCard) card).toString();
				else
					text = ((SpellCard) card).toString();

				gui.getGameWindow().getBtnPlayer2Graveyard()
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

			if (board.getOpponentPlayer().getField().getGraveyard().size() == 0)
				gui.getGameWindow().getBtnPlayer1Graveyard().setIcon(new ImageIcon("src/resources/CardBack.png"));

			else {

				Card card = board.getOpponentPlayer().getField().getGraveyard()
						.get(board.getOpponentPlayer().getField().getGraveyard().size() - 1);
				gui.getGameWindow().getBtnPlayer1Graveyard()
						.setIcon(new ImageIcon("src/resources/" + card.getName() + ".png"));

				String text = "";

				if (card instanceof MonsterCard)
					text = ((MonsterCard) card).toString();
				else
					text = ((SpellCard) card).toString();

				gui.getGameWindow().getBtnPlayer1Graveyard()
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

			for (int i = 0; i < 5; i++) {
				gui.getGameWindow().getArrayPlayer2Monsters().get(i).setIcon(null);
				gui.getGameWindow().getArrayPlayer2Monsters().get(i).setEnabled(false);
			}

			for (int i = 0; i < board.getActivePlayer().getField().getMonstersArea().size() && i < 5; i++) {

				String cardName = board.getActivePlayer().getField().getMonstersArea().get(i).getName();
				String text = "";
				MonsterCard card = board.getActivePlayer().getField().getMonstersArea().get(i);

				if (card.getMode() == Mode.DEFENSE)
					gui.getGameWindow().getArrayPlayer2Monsters().get(i)
							.setIcon(new ImageIcon("src/resources/CardBack.png"));

				else
					gui.getGameWindow().getArrayPlayer2Monsters().get(i)
							.setIcon(new ImageIcon("src/resources/" + cardName + ".png"));

				gui.getGameWindow().getArrayPlayer2Monsters().get(i).setEnabled(true);
				text = card.toString2();
				gui.getGameWindow().getArrayPlayer2Monsters().get(i)
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

			for (int i = 0; i < 5; i++) {
				gui.getGameWindow().getArrayPlayer1Monsters().get(i).setIcon(null);
				gui.getGameWindow().getArrayPlayer1Monsters().get(i).setEnabled(false);
			}

			for (int i = 0; i < board.getOpponentPlayer().getField().getMonstersArea().size() && i < 5; i++) {

				String cardName = board.getOpponentPlayer().getField().getMonstersArea().get(i).getName();
				String text = "";
				MonsterCard card = board.getOpponentPlayer().getField().getMonstersArea().get(i);

				if (card.getMode() == Mode.DEFENSE) {
					gui.getGameWindow().getArrayPlayer1Monsters().get(i)
							.setIcon(new ImageIcon("src/resources/CardBack.png"));
					gui.getGameWindow().getArrayPlayer1Monsters().get(i).setEnabled(true);
					gui.getGameWindow().getArrayPlayer1Monsters().get(i).setToolTipText("" + card.getMode());
				}

				else {
					gui.getGameWindow().getArrayPlayer1Monsters().get(i)
							.setIcon(new ImageIcon("src/resources/" + cardName + ".png"));
					gui.getGameWindow().getArrayPlayer1Monsters().get(i).setEnabled(true);
					text = card.toString2();
					gui.getGameWindow().getArrayPlayer1Monsters().get(i)
							.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");
				}

			}

			for (int i = 0; i < 5; i++) {
				gui.getGameWindow().getArrayPlayer2Spells().get(i).setIcon(null);
				gui.getGameWindow().getArrayPlayer2Spells().get(i).setEnabled(false);
			}

			for (int i = 0; i < board.getActivePlayer().getField().getSpellArea().size() && i < 5; i++) {

				gui.getGameWindow().getArrayPlayer2Spells().get(i).setIcon(new ImageIcon("src/resources/CardBack.png"));
				gui.getGameWindow().getArrayPlayer2Spells().get(i).setEnabled(true);
				String text = "";
				SpellCard card = board.getActivePlayer().getField().getSpellArea().get(i);

				text = card.toString();
				gui.getGameWindow().getArrayPlayer2Spells().get(i)
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

			for (int i = 0; i < 5; i++) {
				gui.getGameWindow().getArrayPlayer1Spells().get(i).setIcon(null);
				gui.getGameWindow().getArrayPlayer1Spells().get(i).setEnabled(false);
			}

			for (int i = 0; i < board.getOpponentPlayer().getField().getSpellArea().size() && i < 5; i++) {

				gui.getGameWindow().getArrayPlayer1Spells().get(i).setIcon(new ImageIcon("src/resources/CardBack.png"));
				gui.getGameWindow().getArrayPlayer1Spells().get(i).setEnabled(true);
				String text = "";

				gui.getGameWindow().getArrayPlayer1Spells().get(i)
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

		}

		if (board.getWinner() != null) {

			String winner = board.getWinner().getName();
			gui.getGameWindow().setVisible(false);
			gui.getEndWindow().setTitle(winner + " wins!");
			gui.getEndWindow().setVisible(true);

		}

		if (e.getSource() instanceof ButtonDeck) {

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "Do you wish to surrender?", "Surrender",
					dialogButton);

			if (dialogResult == JOptionPane.YES_OPTION) {

				gui.getGameWindow().setVisible(false);
				JButton tempBtn = (JButton) e.getSource();

				if (tempBtn.getToolTipText().equals("Player 1 Deck"))
					gui.getEndWindow().setTitle(gui.getGameWindow().getLblPlayer2Name().getText() + " wins!");
				else
					gui.getEndWindow().setTitle(gui.getGameWindow().getLblPlayer1Name().getText() + " wins!");

				gui.getEndWindow().setVisible(true);

			}

		}

		if (e.getSource() instanceof ButtonPlayAgain) {
			gui.getEndWindow().setVisible(false);
			new Main();

		}

	}

	public void updateHand() {

		if (board.getActivePlayer().getName() == gui.getGameWindow().getLblPlayer1Name().getText()) {

			for (int i = 0; i < 20; i++)
				gui.getHandPlayer1().getArrayCards().get(i).setIcon(null);

			gui.getHandPlayer1().getBtnActivateSpell().setEnabled(false);
			gui.getHandPlayer1().getBtnSetMonster().setEnabled(false);
			gui.getHandPlayer1().getBtnSetSpell().setEnabled(false);
			gui.getHandPlayer1().getBtnSummonMonster().setEnabled(false);

			for (int i = 0; i < board.getActivePlayer().getField().getHand().size(); i++) {

				String cardName = board.getActivePlayer().getField().getHand().get(i).getName();
				gui.getHandPlayer1().getArrayCards().get(i)
						.setIcon(new ImageIcon("src/resources/" + cardName + ".png"));
				gui.getHandPlayer1().getArrayCards().get(i).setEnabled(true);

				String text = "";
				Card card = board.getActivePlayer().getField().getHand().get(i);

				if (card instanceof MonsterCard)
					text = ((MonsterCard) card).toString();

				else
					text = ((SpellCard) card).toString();

				gui.getHandPlayer1().getArrayCards().get(i)
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

		}

		else {

			for (int i = 0; i < 20; i++)
				gui.getHandPlayer2().getArrayCards().get(i).setIcon(null);

			gui.getHandPlayer2().getBtnActivateSpell().setEnabled(false);
			gui.getHandPlayer2().getBtnSetMonster().setEnabled(false);
			gui.getHandPlayer2().getBtnSetSpell().setEnabled(false);
			gui.getHandPlayer2().getBtnSummonMonster().setEnabled(false);

			for (int i = 0; i < board.getActivePlayer().getField().getHand().size(); i++) {

				String cardName = board.getActivePlayer().getField().getHand().get(i).getName();
				gui.getHandPlayer2().getArrayCards().get(i)
						.setIcon(new ImageIcon("src/resources/" + cardName + ".png"));
				gui.getHandPlayer2().getArrayCards().get(i).setEnabled(true);

				String text = "";
				Card card = board.getActivePlayer().getField().getHand().get(i);

				if (card instanceof MonsterCard)
					text = ((MonsterCard) card).toString();
				else
					text = ((SpellCard) card).toString();

				gui.getHandPlayer2().getArrayCards().get(i)
						.setToolTipText("<html><p width=\"250\">" + text + "</p></html>");

			}

		}

	}

	public void disable1Enable2() {

		gui.getGameWindow().getLblPlayer1YourTurn().setVisible(false);
		gui.getGameWindow().getLblPlayer2YourTurn().setVisible(true);

		gui.getGameWindow().getBtnPlayer1Hand().setEnabled(false);
		gui.getGameWindow().getBtnPlayer2Hand().setEnabled(true);

		gui.getGameWindow().getBtnPlayer1NextPhase().setEnabled(false);
		gui.getGameWindow().getBtnPlayer1EndTurn().setEnabled(false);
		gui.getGameWindow().getBtnPlayer2NextPhase().setEnabled(true);
		gui.getGameWindow().getBtnPlayer2EndTurn().setEnabled(true);

	}

	public void disable2Enable1() {

		gui.getGameWindow().getLblPlayer2YourTurn().setVisible(false);
		gui.getGameWindow().getLblPlayer1YourTurn().setVisible(true);

		gui.getGameWindow().getBtnPlayer2Hand().setEnabled(false);
		gui.getGameWindow().getBtnPlayer1Hand().setEnabled(true);

		gui.getGameWindow().getBtnPlayer1NextPhase().setEnabled(true);
		gui.getGameWindow().getBtnPlayer1EndTurn().setEnabled(true);
		gui.getGameWindow().getBtnPlayer2NextPhase().setEnabled(false);
		gui.getGameWindow().getBtnPlayer2EndTurn().setEnabled(false);

	}

	public void nullifyAttributes() {

		firstClick = null;
		secondClick = null;
		thirdClick = null;
		fourthClick = null;

		monster = null;
		spell = null;
		sacrifices = new ArrayList<MonsterCard>(2);

	}

	public void addActionListenersToButtons() {

		gui.getStartWindow().getBtnDuelMode().addActionListener(this);
		gui.getStartWindow().getBtnQuitGame().addActionListener(this);

		gui.getGameWindow().getBtnPlayer1EndTurn().addActionListener(this);
		gui.getGameWindow().getBtnPlayer2EndTurn().addActionListener(this);
		gui.getGameWindow().getBtnPlayer1NextPhase().addActionListener(this);
		gui.getGameWindow().getBtnPlayer2NextPhase().addActionListener(this);

		ArrayList<ButtonMonster> monsterButtons1 = gui.getGameWindow().getArrayPlayer1Monsters();
		for (ButtonMonster monsterbutton : monsterButtons1)
			monsterbutton.addActionListener(this);

		ArrayList<ButtonMonster> monsterButtons2 = gui.getGameWindow().getArrayPlayer2Monsters();
		for (ButtonMonster monsterbutton : monsterButtons2)
			monsterbutton.addActionListener(this);

		ArrayList<ButtonSpell> spellButtons1 = gui.getGameWindow().getArrayPlayer1Spells();
		for (ButtonSpell spellbutton : spellButtons1)
			spellbutton.addActionListener(this);

		ArrayList<ButtonSpell> spellButtons2 = gui.getGameWindow().getArrayPlayer2Spells();
		for (ButtonSpell spellbutton : spellButtons2)
			spellbutton.addActionListener(this);

		gui.getGameWindow().getBtnPlayer1Deck().addActionListener(this);
		gui.getGameWindow().getBtnPlayer2Deck().addActionListener(this);

		gui.getGameWindow().getBtnPlayer1Hand().addActionListener(this);
		gui.getGameWindow().getBtnPlayer2Hand().addActionListener(this);

		gui.getHandPlayer1().getBtnActivateSpell().addActionListener(this);
		gui.getHandPlayer1().getBtnSetMonster().addActionListener(this);
		gui.getHandPlayer1().getBtnSetSpell().addActionListener(this);
		gui.getHandPlayer1().getBtnSummonMonster().addActionListener(this);

		gui.getHandPlayer2().getBtnActivateSpell().addActionListener(this);
		gui.getHandPlayer2().getBtnSetMonster().addActionListener(this);
		gui.getHandPlayer2().getBtnSetSpell().addActionListener(this);
		gui.getHandPlayer2().getBtnSummonMonster().addActionListener(this);

		ArrayList<ButtonHand> handButtons1 = gui.getHandPlayer1().getArrayCards();
		for (ButtonHand handbutton : handButtons1)
			handbutton.addActionListener(this);

		ArrayList<ButtonHand> handButtons2 = gui.getHandPlayer2().getArrayCards();
		for (ButtonHand handbutton : handButtons2)
			handbutton.addActionListener(this);

		gui.getEndWindow().getBtnPlayAgain().addActionListener(this);
		gui.getEndWindow().getBtnQuitGame().addActionListener(this);

		gui.getHandPlayer1().addWindowListener(this);
		gui.getHandPlayer2().addWindowListener(this);

	}

	@Override
	public void windowClosed(WindowEvent e) {

		if (e.getSource() instanceof PlayerHand)
			nullifyAttributes();

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
