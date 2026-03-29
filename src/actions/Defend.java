package actions;
import combat.BattleInfo;//import combatants
import combatants.Combatant;//import battle information+combatants is an object

public class Defend implements Action {

    @Override
    public void execute(Combatant user, BattleInfo context) {
        if (user == null || !user.isAlive()) return;// condition: if user is null or dead

        context.applyStatusEffect(user, "DEFEND", 2);//realized in Battleinfo

        System.out.println(user.getName() +
                " uses Defend. Defense +10 for current turn and next turn.");
    }

    @Override
    public String getName() {
        return "Defend";
    }
}