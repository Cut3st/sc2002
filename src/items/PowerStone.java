package items;

import combat.BattleInfo;
import combatants.Player;

public class PowerStone implements Item
{
    @Override
    public String getName()
    {
        return "Power Stone";
    }    
    
    @Override
    public void use(Player user, BattleInfo context)
    {
        System.out.println(user.getName() + " uses Power Stone!");
        user.getSkill().executeFromPowerStone(user, context);
    }
}
