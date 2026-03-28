package actions;
import combatants.Combatant;
import combat.BattleInfo;

public interface Action {
    void execute(Combatant user, BattleInfo context);
    String getName();
}