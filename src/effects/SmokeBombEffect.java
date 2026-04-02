package effects;

import combatants.Combatant;

public class SmokeBombEffect extends StatusEffect {
    public SmokeBombEffect() { super("SMOKE_BOMB", 2); }

    @Override
    public void onApply(Combatant target) {
        // Marker effect — damage suppression is checked in BattleInfo.isSmokeBombed()
        System.out.println(target.getName() + " is shrouded in smoke! Enemy attacks deal 0 damage for 2 turns.");
    }

    @Override
    public void onExpire(Combatant target) {
        System.out.println("Smoke clears around " + target.getName() + ".");
    }
}
