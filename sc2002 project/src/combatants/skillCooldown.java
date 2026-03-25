public abstract class skillCooldown implements SpecialSkill{
    protected int cooldown=0;
    protected static final int MAX_COOLDOWN=3;

    public boolean isAvailable(){
        return cooldown==0; // if cooldown == 0 will return true, else false
    }

    protected void triggerCooldown(){
        cooldown=MAX_COOLDOWN;
    }

    public void reduceCooldown(){
        if(cooldown>0)
            cooldown--;
    }
}

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
