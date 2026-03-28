package effects;

public class ArcaneBuff extends StatusEffect {

    private static final int BONUS_PER_KILL = 10;

    public ArcaneBuff() {
        super("ARCANE_BUFF", -1); // permanent duration
    }

    public static void apply(Combatant user, int kills) {
        if (kills <= 0) return;

        int bonus = kills * BONUS_PER_KILL;
        user.increaseAttack(bonus);

        System.out.println(user.getName() +
                " gains +" + bonus + " attack from Arcane Buff.");
    }
}