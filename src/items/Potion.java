package items;

import combat.BattleInfo;
import combatants.Player;

public class Potion implements Item 
{
    private static final int HEAL_AMOUNT = 100;

    @Override
    public String getName()
    {
        return "Potion";
    }

    @Override
    public void use(Player user, BattleInfo context)
    {
        int oldHP = user.getHp();
        int healed = user.heal(HEAL_AMOUNT);

        System.out.println(user.getName() + " uses Potion. HP: " + oldHP + " -> " + user.getHp() + " (+" + healed + ")");
    }
}
