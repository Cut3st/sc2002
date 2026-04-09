package combatants;

import combat.BattleInfo;

public class SavageBite implements EnemyAction {
    private static final int BONUS_DAMAGE = 15;

    @Override
    public void execute(Enemy enemy, BattleInfo context) {
        Combatant player = context.getPlayer();

        if (context.isSmokeBombed(player)) {
            System.out.println(enemy.getName() + " lunges with Savage Bite, but the smoke bomb absorbs the hit! (0 damage)");
            return;
        }

        int damage = Math.max(0, enemy.getAttack() + BONUS_DAMAGE - player.getDefense());
        int oldHp = player.getHp();
        player.receiveDamage(damage);

        System.out.println(enemy.getName() + " uses Savage Bite on " + player.getName()
                + " for " + damage + " damage. HP: " + oldHp + " -> " + player.getHp());
    }
}
