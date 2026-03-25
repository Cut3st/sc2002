public abstract  class Enemy extends Combatant{
    protected EnemyAction action;

    public void TakeTurn(BattleInfo context){
        if(!isAlive())
            return;
        if (context.isStunned(this)){
            System.out.print("STUNNED: Turn skipped");
            return;
        }
        action.execute(this, context);
    }
}

