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
        if (user == null || !user.isAlive()) return;

<<<<<<< HEAD
        // Use a class instead of "DEFEND", 2
        context.applyStatusEffect(user, new effects.DefendEffect()); 
=======
        context.applyStatusEffect(user, new DefendEffect());//realized in Battleinfo
>>>>>>> 954a207c25086b72bb62a4625756fc54b22434d4

        System.out.println(user.getName() + " uses Defend. Defense +10 for current and next turn.");
    }

    @Override
    public String getName() {
        return "Defend";
    }
}