package items;

import combat.BattleInfo;
import combatants.Player;

public class Antidote implements Item {
    @Override
    public String getName() {
        return "Antidote";
    }

    @Override
    public void use(Player user, BattleInfo context) {
        if (context.removeEffectsByName(user, "POISON")) {
            System.out.println(user.getName() + " uses Antidote and cures the poison.");
        } else {
            System.out.println(user.getName() + " uses Antidote, but there was no poison to cure.");
        }
    }
}
