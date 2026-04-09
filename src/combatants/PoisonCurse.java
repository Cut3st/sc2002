package combatants;

import combat.BattleInfo;
import effects.PoisonEffect;

public class PoisonCurse implements EnemyAction {
    @Override
    public void execute(Enemy enemy, BattleInfo context) {
        Combatant player = context.getPlayer();
        context.applyStatusEffect(player, new PoisonEffect());
        System.out.println(enemy.getName() + " casts Poison Curse on " + player.getName() + "!");
    }
}
