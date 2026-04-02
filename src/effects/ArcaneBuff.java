package effects;

import combatants.Combatant;

public class ArcaneBuff extends StatusEffect {
    private final int attackBonus;

    public ArcaneBuff(int kills) {
        super("ARCANE_BUFF", -1); // -1 = permanent until end of level
        this.attackBonus = kills * 10;
    }

    @Override
    public void onApply(Combatant target) {
        target.increaseAttack(attackBonus);
        System.out.println(target.getName() + " gains +" + attackBonus + " ATK from Arcane Buff!");
    }

    @Override
    public void onExpire(Combatant target) {
        // Permanent — never expires mid-level, so nothing here
    }
}