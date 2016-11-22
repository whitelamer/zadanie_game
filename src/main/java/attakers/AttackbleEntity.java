package attakers;

public interface AttackbleEntity {
    Damager getDps();
    void setDps(Damager dps);

    AttackbleEntity doAttack(AttackbleEntity target);
    double doDamage(double uron);
    double getHelth();
    void setHelth(double helth);

    boolean isAlive();
}
