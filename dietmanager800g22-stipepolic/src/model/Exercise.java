package model;

public class Exercise {
    String name;
    double caloriesBurned = 0;

    public Exercise(String name, double caloriesBurned) {
        this.name = name;
        this.caloriesBurned = caloriesBurned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public String toString() {
        return "e," + this.name + "," + this.caloriesBurned;
    }
}
