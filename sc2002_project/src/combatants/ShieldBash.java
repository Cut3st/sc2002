package combatants;
import combat.BattleInfo;
public class ShieldBash extends skillCooldown{ //warrior skill
    public void execute(Combatant user,BattleInfo context){
        if(!isAvailable()) //check if command use is available
            return;
        Combatant target=context.selectTarget(user); // choose target to attack

        int damage=Math.max(0,user.getAttack()-target.getDefense());//user attack - user defense as (Update HP (Existing HP-Damage+Defense)) or 0 incase def>atk
        target.receiveDamage(damage);

        context.applyStatusEffect(target, "STUNNED", 2);//name may change depend on what is named.

        triggerCooldown();
    }
}