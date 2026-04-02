// combat/GameController.java
package combat;

import combatants.*;
import ui.CLI;
import java.util.*;

public class GameController {
    private CLI cli;

    public GameController(CLI cli) {
        this.cli = cli;
    }

    public void run() {
        cli.showTitleScreen();

        boolean playing = true;
        while (playing) {

            // 1. Pick character
            int playerChoice = cli.showPlayerSelection();
            Combatant player = playerChoice == 1 ? new Warrior() : new Wizard();

            // 2. Pick items (add when Member 4 is done)

            // 3. Pick difficulty
            int difficulty = cli.showDifficultySelection();
            List<Combatant> enemies = buildEnemies(difficulty);
            List<Combatant> backup = buildBackup(difficulty);

            // 4. Run battle
            BattleEngine engine = new BattleEngine(
                player, enemies, backup, new SpeedBasedTurnOrder(), cli
            );
            engine.runBattle();

            // 5. Post game menu
            int choice = cli.showPostGameMenu();
            switch (choice) {
                case 1 -> { /* replay — loop continues with same difficulty, add later */ }
                case 2 -> { /* new game — loop continues back to title */ }
                case 3 -> playing = false;
            }
        }

        cli.close();
    }

    private List<Combatant> buildEnemies(int difficulty) {
        List<Combatant> enemies = new ArrayList<>();
        switch (difficulty) {
            case 1 -> {
                enemies.add(new Goblin("Goblin A"));
                enemies.add(new Goblin("Goblin B"));
                enemies.add(new Goblin("Goblin C"));
            }
            case 2 -> {
                enemies.add(new Goblin("Goblin A"));
                enemies.add(new Wolf("Wolf A"));
            }
            case 3 -> {
                enemies.add(new Goblin("Goblin A"));
                enemies.add(new Goblin("Goblin B"));
            }
        }
        return enemies;
    }

    private List<Combatant> buildBackup(int difficulty) {
        List<Combatant> backup = new ArrayList<>();
        switch (difficulty) {
            case 2 -> {
                backup.add(new Wolf("Wolf A"));
                backup.add(new Wolf("Wolf B"));
            }
            case 3 -> {
                backup.add(new Goblin("Goblin C"));
                backup.add(new Wolf("Wolf A"));
                backup.add(new Wolf("Wolf B"));
            }
        }
        return backup;
    }
}