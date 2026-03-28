package combatants;
import combat.BattleInfo;
public class BasicEnemyAttack implements EnemyAction{
    public void execute(Enemy enemy, BattleInfo context){
        Combatant player=context.getPlayer();//name may change depends on what its named

        int damage=Math.max(0,enemy.getAttack()- player.getDefense());//user attack - user defense as (Update HP (Existing HP-Damage+Defense)) or 0 incase def>atk
        player.receiveDamage(damage);
    }
}
