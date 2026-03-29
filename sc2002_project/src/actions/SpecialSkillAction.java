package actions;
import combat.BattleInfo;
import combatants.Combatant;//import combatants
import combatants.Player;//import battle information+combatants is an object

public class SpecialSkillAction implements Action {

    @Override
    public void execute(Combatant user, BattleInfo context) {
        if (user == null || !user.isAlive()) return;

        if (!(user instanceof Player)) {
            System.out.println("Only players can use Special Skill.");
            return;
        }

        Player player = (Player) user;

        if (player.getSkill() == null) {
            System.out.println("No special skill available.");
            return;
        }

        player.getSkill().execute(player, context);
    }

    @Override
    public String getName() {
        return "SpecialSkill";
    }
}