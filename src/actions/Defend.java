package actions;

import combat.BattleInfo;//import combatants
import combatants.Combatant;//import battle information+combatants is an object
import effects.DefendEffect;

public class Defend implements Action {

    @Override
    public void execute(Combatant user, BattleInfo context) {
        if (user == null || !user.isAlive()) return;

        context.applyStatusEffect(user, new DefendEffect());

        System.out.println(user.getName() + " uses Defend. Defense +10 for current and next turn.");
    }

    @Override
    public String getName() {
        return "Defend";
    }
}