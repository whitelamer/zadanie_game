package attakers;

public class Damager {
    public double getMin_damage() {
        return min_damage;
    }

    public double getMax_damage() {
        return max_damage;
    }

    private double min_damage;
    private double max_damage;
    public Damager(double min,double max){
        min_damage=Math.min(min,max);
        max_damage=Math.max(min,max);
    }
    public double damage(){
        return Math.random()*(max_damage-min_damage)+min_damage;
    }

    @Override
    public String toString() {
        return "{" + (int)min_damage + "-" + (int)max_damage + "}";
    }

    public void addItem(Damager damager) {
        min_damage+=damager.getMin_damage();
        max_damage+=damager.getMax_damage();
    }
}
