package combatants;
import combat.BattleInfo;
public abstract  class Enemy extends Combatant{
    protected EnemyAction action;

    public void TakeTurn(BattleInfo context) {
        if (!isAlive()) return;
        if (context.isStunned(this)) {
            System.out.println(getName() + " is stunned and cannot act!");
            return;
        }
        action.execute(this, context);
    }
}

