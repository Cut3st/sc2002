package combat;

import actions.Action;
import combatants.*;
import effects.StatusEffect;
import ui.CLI;
import java.util.*;

public class BattleInfo {
    private Combatant player;
    private List<Combatant> enemies;
    private int currentRound;
    private Map<Combatant, List<StatusEffect>> statusEffects;
    private CLI cli;

    public BattleInfo(Combatant player, List<Combatant> enemies, int currentRound, CLI cli) {
        this.player = player;
        this.enemies = enemies;
        this.currentRound = currentRound;
        this.statusEffects = new HashMap<>();
        this.cli = cli;
    }

    public Combatant getPlayer() { return player; }
    public List<Combatant> getEnemies() { return enemies; }
    public int getCurrentRound() { return currentRound; }

    public void applyStatusEffect(Combatant target, StatusEffect effect) {
        statusEffects.computeIfAbsent(target, k -> new ArrayList<>()).add(effect);
        effect.onApply(target);
    }

    public boolean isStunned(Combatant c) {
        return getEffects(c).stream().anyMatch(e -> e.getName().equals("STUNNED"));
    }

    public boolean isDefending(Combatant c) {
        return getEffects(c).stream().anyMatch(e -> e.getName().equals("DEFEND"));
    }

    public boolean isSmokeBombed(Combatant c) {
        return getEffects(c).stream().anyMatch(e -> e.getName().equals("SMOKE_BOMB"));
    }

    // Called once per combatant's turn end to tick their effects down
    public void tickEffectsFor(Combatant c) {
        List<StatusEffect> effects = getEffects(c);
        Iterator<StatusEffect> it = effects.iterator();
        while (it.hasNext()) {
            StatusEffect e = it.next();
            if (e.getDuration() == -1) continue; // permanent
            e.tick();
            if (e.isExpired()) {
                e.onExpire(c);
                it.remove();
            }
        }
    }

    private List<StatusEffect> getEffects(Combatant c) {
        return statusEffects.getOrDefault(c, Collections.emptyList());
    }

    public Combatant selectTarget(Combatant user) {
        List<Combatant> alive = new ArrayList<>();
        for (Combatant e : enemies) {
            if (e.isAlive()) alive.add(e);
        }
        return cli.selectTarget(alive);
    }

    public Action getPlayerAction(Player player) {
        return cli.getPlayerAction(player);
    }

    public String getStatusSummary(Combatant c) {
        List<StatusEffect> effects = getEffects(c);
        if (effects.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (StatusEffect e : effects) sb.append("[").append(e.getName()).append("] ");
        return sb.toString().trim();
    }
}