package effects;

import combatants.Combatant;
public class Stun extends StatusEffect {
    public Stun() { super("STUNNED", 2); }

    @Override
    public void onApply(Combatant target) {
        // Stun has no stat change — the stun check in Enemy.TakeTurn handles skipping
    }

    @Override
    public void onExpire(Combatant target) {
        // Nothing to clean up
    }
}
