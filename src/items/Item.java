package items;

import combatants.Player;
import combat.BattleInfo;

public interface Item {
    String getName();
    void use(Player player, BattleInfo context);
}