package cards;

public class MonsterCard extends Card {
	
	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;
	
	private boolean hasAttacked = false;
	private boolean modeSwitched = false;

	public MonsterCard(String name, String description, int level, int attack, int defense) {
		super(name, description);
		this.level = level;
		attackPoints = attack;
		defensePoints = defense;
		mode = Mode.DEFENSE;
	}

	public int getLevel() {
		return level;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public Mode getMode() {
		return mode;
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public boolean hasAttacked() {
		return hasAttacked;
	}

	public void setHasAttacked(boolean hasAttacked) {
		this.hasAttacked = hasAttacked;
	}
	
	public String toString(){
		return super.toString() + ", ATK: " + attackPoints + ", DEF: " + defensePoints + ", LVL: " + level + ", " + getDescription() + "\n"; 
	}
	
	public String toString2(){
		return super.toString() + ", ATK: " + attackPoints + ", DEF: " + defensePoints + ", LVL: " + level + ", " + mode + ", " + getDescription() + "\n"; 
	}

	@Override
	public void action(MonsterCard monster) {
		
		int attackingPoints = this.getAttackPoints();
		int attackedPoints;
		int deductedPoints;
		if(monster.getMode() == Mode.ATTACK){
			attackedPoints = monster.getAttackPoints();
			 deductedPoints = Math.abs(attackingPoints-attackedPoints);
		 if(attackingPoints > attackedPoints){
			
			 getBoard().getOpponentPlayer().setLifePoints(getBoard().getOpponentPlayer().getLifePoints() - (deductedPoints));
			 getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
		 }
		 
		 else{
			 if(attackingPoints == attackedPoints){
				 getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
				 getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			 }
			 else{
				 getBoard().getActivePlayer().setLifePoints(getBoard().getActivePlayer().getLifePoints() - (deductedPoints));
				 getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
			 }
		 }
		 
		}
		
		else{
			
			attackedPoints = monster.getDefensePoints();
			 deductedPoints=Math.abs(attackingPoints-attackedPoints);
			 
			if(attackingPoints > attackedPoints)
				getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			 
			 else{
				 if(attackingPoints < attackedPoints)
					 getBoard().getActivePlayer().setLifePoints(getBoard().getActivePlayer().getLifePoints() - (deductedPoints));
									 
			 }
			
		}
		
		Card.getBoard().assignWinner();
		 
	}
	
	public void action(){
		
		if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size() == 0){
			
			getBoard().getOpponentPlayer().setLifePoints(getBoard().getOpponentPlayer().getLifePoints() - this.getAttackPoints());
			Card.getBoard().assignWinner();
			
		}
		
	}

	public boolean isModeSwitched() {
		return modeSwitched;
	}

	public void setModeSwitched(boolean modeSwitched) {
		this.modeSwitched = modeSwitched;
	}
	
	
	
}