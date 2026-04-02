package combatants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import actions.Action;
import combat.BattleInfo;
import items.Item;
import ui.CLI;
public abstract class Player extends Combatant{
    protected SpecialSkill skill;

    public void TakeTurn(BattleInfo context) {
        if (!isAlive()) return;
        Action action = context.getPlayerAction(this);
        action.execute(this, context);
        // Only tick cooldown AFTER the action, and only if skill wasn't just activated
        // skillCooldown.triggerCooldown() sets it to MAX; reduceCooldown would immediately decrement.
        // tickCooldown() in skillCooldown handles this guard:
        skill.reduceCooldown();
    }
    public SpecialSkill getSkill() { return skill; }
    // Add these fields and methods to Player.java
    protected List<Item> items = new ArrayList<>();

    public void addItem(Item item) { items.add(item); }

    public boolean hasItems() {
        return items.stream().anyMatch(i -> !i.isUsed());
    }

    public String getItemsSummary() {
        if (items.isEmpty()) return "None";
        return items.stream()
            .map(i -> i.getName() + (i.isUsed() ? " (used)" : ""))
            .collect(java.util.stream.Collectors.joining(", "));
    }

    // M4 will call this from the Item action
    public Item selectItem(CLI cli) {
        return cli.selectItem(items.stream().filter(i -> !i.isUsed()).collect(Collectors.toList()));
}
}

