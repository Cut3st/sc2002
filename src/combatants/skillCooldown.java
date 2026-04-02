package combatants;
public abstract class skillCooldown implements SpecialSkill{
    protected int cooldown=0;
    protected static final int MAX_COOLDOWN=3;

    public boolean isAvailable(){
        return cooldown==0; // if cooldown == 0 will return true, else false
    }

    protected void triggerCooldown(){
        cooldown=MAX_COOLDOWN;
    }
    public void tickCooldown() {
        if (cooldown == MAX_COOLDOWN) return; // just used this turn, don't decrement yet
        if (cooldown > 0) cooldown--;
    }
    public void reduceCooldown(){
        if(cooldown>0)
            cooldown--;
    }
}



