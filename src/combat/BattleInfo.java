package combat;

import actions.Action;
import combatants.*;
import ui.CLI;
import java.util.*;

public class BattleInfo {
    private Combatant player;
    private List<Combatant> enemies;
    private int currentRound;
    private Map<Combatant, Map<String, Integer>> statusEffects;
    private CLI cli; // injected so CLI handles all I/O

    public BattleInfo(Combatant player, List<Combatant> enemies, int currentRound, CLI cli) {
        this.player = player;
        this.enemies = enemies;
        this.currentRound = currentRound;
        this.statusEffects = new HashMap<>();
        this.cli = cli;
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

    // ShieldBash + Defend uses this
    public void applyStatusEffect(Combatant target, String effect, int duration) {
        statusEffects.computeIfAbsent(target, k -> new HashMap<>()).put(effect, duration);
        System.out.println("  " + target.getName() + " is " + effect + " for " + duration + " turns!");
    }

    /**
     * Fleshed out — delegates to CLI for display and input.
     * Called by BasicAttack and ShieldBash.
     */
    public Combatant selectTarget(Combatant user) {
        List<Combatant> alive = new ArrayList<>();
        for (Combatant e : enemies) {
            if (e.isAlive()) alive.add(e);
        }
        return cli.selectTarget(alive);
    }

    /**
     * Fleshed out — delegates to CLI for action menu display and input.
     * Called by Player.TakeTurn().
     */
    public Action getPlayerAction(Player player) {
        return cli.getPlayerAction(player);
    }

    // Called at end of every turn in BattleEngine
    public void tickEffects() {
        for (Map<String, Integer> effects : statusEffects.values()) {
            effects.replaceAll((effect, turns) -> turns - 1);
            effects.entrySet().removeIf(e -> e.getValue() <= 0);
        }
    }

    public int getCurrentRound() { return currentRound; }

    // Returns summary of active effects on a combatant (for display)
    public String getStatusSummary(Combatant c) {
        if (!statusEffects.containsKey(c) || statusEffects.get(c).isEmpty()) return "";
        return statusEffects.get(c).toString();
    }
}
