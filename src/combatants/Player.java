package combatants;
import actions.Action;
import combat.BattleInfo;
public abstract class Player extends Combatant{
    protected SpecialSkill skill;

    public void TakeTurn(BattleInfo context) {
        if (!isAlive()) return;
        Action action = context.getPlayerAction(this);
        action.execute(this, context);
        // Only tick cooldown AFTER the action, and only if skill wasn't just activated
        // skillCooldown.triggerCooldown() sets it to MAX; reduceCooldown would immediately decrement.
        // tickCooldown() in skillCooldown handles this guard:
        skill.tickCooldown();
    }
    public SpecialSkill getSkill() { return skill; }
    public boolean hasItems() { return false; }
    public String getItemsSummary() { return "No items (coming soon)"; }
}

