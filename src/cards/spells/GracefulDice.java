package cards.spells;

import cards.MonsterCard;

public class GracefulDice extends SpellCard {

	public GracefulDice(String name, String description) {
		super(name, description);
	}
	
	public void action(MonsterCard monster){
		
		int factorOfIncrease = (int)Math.ceil(Math.random()*10);
		int numberOfMonstersControlled = getBoard().getActivePlayer().getField().getMonstersArea().size();
		
		for (int i = 0; i <numberOfMonstersControlled ; i++) {
			
			MonsterCard monsterC = getBoard().getActivePlayer().getField().getMonstersArea().get(i);
			monsterC.setAttackPoints(monsterC.getAttackPoints()+(100*factorOfIncrease));
			monsterC.setDefensePoints(monsterC.getDefensePoints()+(100*factorOfIncrease));
			
		}
	}

}
