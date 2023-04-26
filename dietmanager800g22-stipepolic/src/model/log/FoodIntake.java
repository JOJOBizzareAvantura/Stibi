package model.log;

import java.time.LocalDate;

import model.food.Food;

/*
 * This class represents a food intake entry in the log.
 */
public class FoodIntake implements LogEntry {
    private Food food;
    private double servings;
    private LocalDate date;

    public FoodIntake(LocalDate date, Food food, double servings) {
        this.date = date;
        this.food = food;
        this.servings = servings;
    }

    public Food getFood() {
        return food;
    }

    public double getServings() {
        return servings;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.date.getYear() + ",");
        sb.append(this.date.getMonthValue() + ",");
        sb.append(this.date.getDayOfMonth());
        sb.append(",f,");
        sb.append(this.food.getName() + ",");
        sb.append(this.servings);
        return new String(sb);
    }
}
