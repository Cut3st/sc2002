package items;

import combat.BattleInfo;
import combatants.Player;
import effects.SmokeBombEffect;

public class SmokeBomb implements Item
{
    @Override
    public String getName()
    {
        return "Smoke Bomb";
    }

    @Override
    public void use(Player user, BattleInfo context)
    {
        context.applyStatusEffect(user, new SmokeBombEffect());
        System.out.println(user.getName() + " uses Smoke Bomb!");
    }
}
