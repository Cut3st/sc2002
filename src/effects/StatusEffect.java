package effects;
// only extend but not creating the object
public abstract class StatusEffect {

    protected final String name;
    protected final int duration;

    protected StatusEffect(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}