package combatants;
import combat.BattleInfo;
public class ArcaneBlast extends skillCooldown{ //wizard skill
    public void execute(Combatant user,BattleInfo context) {
        if (!isAvailable()) //check if command use is available
            return;
        int kills = 0;

        for (Combatant enemy : context.getEnemies()) { //for each enemy in context.getEnemies()
            int damage =Math.max(0,user.getAttack() - enemy.getDefense());//user attack - user defense as (Update HP (Existing HP-Damage+Defense)) or 0 incase def>atk
            enemy.receiveDamage(damage);

            if (!enemy.isAlive())
                kills++;
        }

        user.increaseAttack(kills * 10); //may need to change name depends on what is named

        triggerCooldown();
    }
}