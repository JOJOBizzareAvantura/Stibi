package model.food;

public class BasicFood implements Food {
    private String name;
    private double calories;
    private double fat;
    private double carbs;
    private double protein;

    public BasicFood(String name, double calories, double fat, double carbs, double protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public String getName() {
        return this.name;
    }

    public double getCalories() {
        return this.calories;
    }

    public double getFat() {
        return this.fat;
    }

    public double getCarbs() {
        return this.carbs;
    }

    public double getProtein() {
        return this.protein;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public String toString() {
        return "b," + this.name + "," + this.calories + "," + this.fat + "," + this.carbs + "," + this.protein;
    }
}
