package actions;

import combat.BattleInfo;
import combatants.Combatant;
import combatants.Player;

public class ItemAction implements Action
{
    private final int itemIndex;
    
    public ItemAction(int itemIndex)
    {
        this.itemIndex = itemIndex;
    }


    @Override
    public void execute(Combatant user, BattleInfo context)
    {
        if (!(user instanceof Player player))
        {
            System.out.println("Only players can use items.");
            return;
        }
        player.useItem(itemIndex, context);
    }

    @Override
    public String getName()
    {
        return "Item";
    }
}
