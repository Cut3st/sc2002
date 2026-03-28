package combatants;
public class Wolf extends Enemy{
    public Wolf(){
        this.name = "Wolf";
        this.hp = 40;
        this.maxHp = 40;
        this.attack=45;
        this.defense=5;
        this.speed=35;
        this.action=new BasicEnemyAttack();
    }
}
