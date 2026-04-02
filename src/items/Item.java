package items;

import combatants.Player;
import combat.BattleInfo;

public interface Item {
    void use(Player player, BattleInfo context);
    String getName();
    boolean isUsed();
}