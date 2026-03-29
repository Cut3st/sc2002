package actions;
import combat.BattleInfo;// import combatants
import combatants.Combatant;//import battle information+combatants is an object

public class BasicAttack implements Action {
// must realize function execute() and getname()
    @Override
    public void execute(Combatant user, BattleInfo context) {
        if (user == null || !user.isAlive()) return;// condition: if user is null or dead

        Combatant target = context.selectTarget(user);//let the user choose the target
        if (target == null || !target.isAlive()) return;// condition: if target is null or dead

        int damage = Math.max(0, user.getAttack() - target.getDefense());
        int oldHp = target.getHp();//record current HP
        target.receiveDamage(damage);// transfer the damage to the target

        System.out.println(user.getName() + " uses BasicAttack on " + target.getName()
                + " for " + damage + " damage. "
                + target.getName() + " HP: " + oldHp + " -> " + target.getHp());
    }

    @Override
    public String getName() {
        return "BasicAttack";
    }
}