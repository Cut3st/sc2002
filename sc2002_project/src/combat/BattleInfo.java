package combat;
import actions.Action;
import combatants.*;
import java.util.*;

public class BattleInfo {
    private Combatant player;
    private List<Combatant> enemies;
    private int currentRound;
    private Map<Combatant, Map<String, Integer>> statusEffects;

    public BattleInfo(Combatant player, List<Combatant> enemies, int currentRound) {
        this.player = player;
        this.enemies = enemies;
        this.currentRound = currentRound;
        this.statusEffects = new HashMap<>();
    }

    // BasicEnemyAttack uses this
    public Combatant getPlayer() { return player; }

    // ArcaneBlast uses this
    public List<Combatant> getEnemies() { return enemies; }

    // Enemy.java uses this
    public boolean isStunned(Combatant c) {
        return statusEffects.containsKey(c)
            && statusEffects.get(c).getOrDefault("STUNNED", 0) > 0;
    }

    // ShieldBash uses this
    public void applyStatusEffect(Combatant target, String effect, int duration) {
        statusEffects.computeIfAbsent(target, k -> new HashMap<>()).put(effect, duration);
        System.out.println(target.getName() + " is " + effect + " for " + duration + " turns!");
    }

    // ShieldBash uses this — CLI member will flesh this out
    public Combatant selectTarget(Combatant user) {
        List<Combatant> alive = new ArrayList<>();
        for (Combatant e : enemies) {
            if (e.isAlive()) alive.add(e);
        }
        System.out.println("Select a target:");
        for (int i = 0; i < alive.size(); i++) {
            System.out.println((i + 1) + ". " + alive.get(i).getName()
                + " HP: " + alive.get(i).getHp());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt() - 1;
        return alive.get(choice);
    }

    // Player.java uses this — CLI member will flesh this out
    public Action getPlayerAction(Player player) {
        return null; // placeholder for CLI member
    }

    // call this at end of every turn in BattleEngine
    public void tickEffects() {
        for (Map<String, Integer> effects : statusEffects.values()) {
            effects.replaceAll((effect, turns) -> turns - 1);
            effects.entrySet().removeIf(e -> e.getValue() <= 0);
        }
    }

    public int getCurrentRound() { return currentRound; }
}