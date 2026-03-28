package combatants;
public class Goblin extends Enemy{
    public Goblin(){
        this.name = "Goblin";
        this.hp = 55;
        this.maxHp = 55;
        this.attack=35;
        this.defense=15;
        this.speed=25;
        this.action=new BasicEnemyAttack();
    }
}
