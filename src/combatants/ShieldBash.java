package combatants;
import combat.BattleInfo;
import effects.Stun;
public class ShieldBash extends skillCooldown{ //warrior skill
    public void execute(Combatant user, BattleInfo context) {
        if (!isAvailable()) {
            System.out.println("Shield Bash is on cooldown!");
            return;
        }
        Combatant target = context.selectTarget(user);
        int damage = Math.max(0, user.getAttack() - target.getDefense());
        int oldHp = target.getHp();
        target.receiveDamage(damage);
        System.out.println(user.getName() + " uses Shield Bash on " + target.getName()
                + " for " + damage + " damage. HP: " + oldHp + " -> " + target.getHp());
        context.applyStatusEffect(target, new Stun());
        triggerCooldown();
    }
}