package combatants;

import combat.BattleInfo;

public class Shaman extends Enemy {
    public Shaman(String name) {
        this.name = name;
        this.hp = 45;
        this.maxHp = 45;
        this.attack = 30;
        this.defense = 10;
        this.speed = 20;
        this.action = new BasicEnemyAttack();
    }

    public Shaman() {
        this("Shaman");
    }

    @Override
    protected EnemyAction chooseAction(BattleInfo context) {
        if (!context.isPoisoned(context.getPlayer())) {
            return new PoisonCurse();
        }
        return super.chooseAction(context);
    }
}
