package effects;

import combatants.Combatant;

// only extend but not creating the object
public abstract class StatusEffect {

    protected final String name;
    protected int duration;

    protected StatusEffect(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() { return name; }
    public int getDuration() { return duration; }
    public boolean isExpired() { return duration <= 0; }
    public void tick() { if (duration > 0) duration--; }
    public void onTick(Combatant target) {}

    // Subclasses define what happens when applied and when removed
    public abstract void onApply(Combatant target);
    public abstract void onExpire(Combatant target);
}
