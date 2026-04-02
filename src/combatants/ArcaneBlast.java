package combatants;
import combat.BattleInfo;
import effects.ArcaneBuff;
public class ArcaneBlast extends skillCooldown {
    public void execute(Combatant user, BattleInfo context) {
        if (!isAvailable()) {
            System.out.println("Arcane Blast is on cooldown!");
            return;
        }
        int kills = 0;
        System.out.println(user.getName() + " unleashes Arcane Blast on all enemies!");
        for (Combatant enemy : context.getEnemies()) {
            int damage = Math.max(0, user.getAttack() - enemy.getDefense());
            int oldHp = enemy.getHp();
            enemy.receiveDamage(damage);
            System.out.println("  " + enemy.getName() + " HP: " + oldHp + " -> " + enemy.getHp()
                    + " (dmg: " + damage + ")");
            if (!enemy.isAlive()) kills++;
        }
        if (kills > 0) {
            context.applyStatusEffect(user, new ArcaneBuff(kills));
        }
        triggerCooldown();
    }
}