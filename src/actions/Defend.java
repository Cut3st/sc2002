package actions;

import combat.BattleInfo;//import combatants
import combatants.Combatant;//import battle information+combatants is an object
<<<<<<< HEAD
import effects.DefendEffect; 
=======
import effects.DefendEffect;
>>>>>>> f7b4b4187540c0b7be8d594121c2f8d16e22cd2c

public class Defend implements Action {

    @Override
    public void execute(Combatant user, BattleInfo context) {
        if (user == null || !user.isAlive()) return;// condition: if user is null or dead

        context.applyStatusEffect(user, new DefendEffect());//realized in Battleinfo

        System.out.println(user.getName() +
                " uses Defend. Defense +10 for current turn and next turn.");
    }

    @Override
    public String getName() {
        return "Defend";
    }
}