public abstract class Player extends Combatant{
    protected SpecialSkill skill;

    public void TakeTurn(BattleInfo context) {
        if (!isAlive())
            return;
        Action action = context.getPlayerAction(this); //get action executed(may need to change name depends on what function name used)
        action.execute(this, context);

        skill.reduceCooldown();//if player use skills, cooldown need to be reduced
    }
}

