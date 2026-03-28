package combat;
import combatants.Combatant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BattleEngine {
    private Combatant player;
    private List<Combatant> enemies;
    private List<Combatant> backupSpawn;
    private boolean backupSpawned = false;
    private TurnOrderStrategy turnOrderStrategy;
    private int roundCount = 0;

    public BattleEngine(Combatant player, List<Combatant> enemies,
                        List<Combatant> backupSpawn, TurnOrderStrategy strategy) {
        this.player = player;
        this.enemies = new ArrayList<>(enemies);
        this.backupSpawn = (backupSpawn != null) ? backupSpawn : new ArrayList<>();
        this.turnOrderStrategy = strategy;
    }

    public void runBattle() {
        while (!isBattleOver()) {
            roundCount++;
            System.out.println("\n========== ROUND " + roundCount + " ==========");

            List<Combatant> allCombatants = getAllCombatants();
            List<Combatant> turnOrder = turnOrderStrategy.getTurnOrder(allCombatants);

            for (Combatant c : turnOrder) {
                if (!c.isAlive()) continue;
                if (isBattleOver()) break;

                BattleInfo context = new BattleInfo(
                    player,
                    enemies.stream().filter(Combatant::isAlive).collect(Collectors.toList()),
                    roundCount
                );

                c.TakeTurn(context);
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
                System.out.println("\n⚠ BACKUP SPAWN TRIGGERED!");
                enemies.addAll(backupSpawn);
                backupSpawned = true;
                for (Combatant c : backupSpawn) {
                    System.out.println(c.getName() + " has entered the battle!");
                }
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
            System.out.println("\n🏆 VICTORY!");
            System.out.println("Congratulations, you have defeated all your enemies.");
            System.out.println("Remaining HP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println("Total Rounds: " + roundCount);
        } else {
            System.out.println("\n💀 DEFEATED. Don't give up, try again!");
            long remaining = enemies.stream().filter(Combatant::isAlive).count();
            System.out.println("Enemies Remaining: " + remaining);
            System.out.println("Total Rounds Survived: " + roundCount);
        }
    }
}