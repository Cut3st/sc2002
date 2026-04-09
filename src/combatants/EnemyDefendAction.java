package combatants;

import combat.BattleInfo;
import effects.DefendEffect;

public class EnemyDefendAction implements EnemyAction {
    @Override
    public void execute(Enemy enemy, BattleInfo context) {
        if (context.isDefending(enemy)) {
            return;
        }

        context.applyStatusEffect(enemy, new DefendEffect());
        System.out.println(enemy.getName() + " braces for impact and raises its defense.");
    }
}
