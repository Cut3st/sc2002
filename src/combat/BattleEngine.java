package combat;
import combatants.Combatant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ui.CLI;

public class BattleEngine {
    private Combatant player;
    private List<Combatant> enemies;
    private List<Combatant> backupSpawn;
    private boolean backupSpawned = false;
    private TurnOrderStrategy turnOrderStrategy;
    private int roundCount = 0;
private CLI cli;
public BattleEngine(Combatant player, List<Combatant> enemies, List<Combatant> backupSpawn, TurnOrderStrategy strategy, CLI cli) {
    this.player = player;
    this.enemies = new ArrayList<>(enemies);
    this.backupSpawn = (backupSpawn != null) ? backupSpawn : new ArrayList<>();
    this.turnOrderStrategy = strategy;
    this.cli = cli;
}

    public void runBattle() {
        while (!isBattleOver()) {
            roundCount++;
            cli.showBattleStatus(player, enemies, roundCount); // move display to top of round

            List<Combatant> allCombatants = getAllCombatants();
            List<Combatant> turnOrder = turnOrderStrategy.getTurnOrder(allCombatants);

            BattleInfo context = new BattleInfo(
                player,
                enemies.stream().filter(Combatant::isAlive).collect(Collectors.toList()),
                roundCount,
                cli
            );

            for (Combatant c : turnOrder) {
                if (!c.isAlive()) continue;
                if (isBattleOver()) break;
                c.TakeTurn(context);
                context.tickEffectsFor(c); // tick only this combatant's effects after their turn
            }

            checkBackupSpawn();
            displayRoundSummary();
        }
        displayResult();
    }
    private void checkBackupSpawn() {
        if (!backupSpawned && !backupSpawn.isEmpty()) {
            boolean allInitialDead = enemies.stream().noneMatch(Combatant::isAlive);
            if (allInitialDead) {
                enemies.addAll(backupSpawn);
                backupSpawned = true;
                cli.showBackupSpawn(backupSpawn); // already implemented in CLI
            }
        }
    }

    private boolean isBattleOver() {
        boolean playerDead = !player.isAlive();
        boolean allEnemiesDead = enemies.stream().noneMatch(Combatant::isAlive)
                && (backupSpawned || backupSpawn.isEmpty());
        return playerDead || allEnemiesDead;
    }

    private List<Combatant> getAllCombatants() {
        List<Combatant> all = new ArrayList<>();
        all.add(player);
        all.addAll(enemies);
        return all;
    }

    private void displayRoundSummary() {
        System.out.println("\n--- End of Round " + roundCount + " ---");
        System.out.println(player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp());
        for (Combatant e : enemies) {
            String status = e.isAlive() ? "HP: " + e.getHp() : "ELIMINATED";
            System.out.println(e.getName() + " " + status);
        }
    }

    private void displayResult() {
        if (player.isAlive()) {
            long remaining = enemies.stream().filter(Combatant::isAlive).count();
            cli.showVictoryScreen(player.getHp(), roundCount); // use CLI screen
        } else {
            long remaining = enemies.stream().filter(Combatant::isAlive).count();
            cli.showDefeatScreen((int) remaining, roundCount); // use CLI screen
        }
    }
}