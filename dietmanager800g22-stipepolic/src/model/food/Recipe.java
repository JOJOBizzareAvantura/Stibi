package model.food;

import java.util.Map;

public class Recipe implements Food {
    private String name;
    private Map<Food, Double> ingredients;

    public Recipe(String name, Map<Food, Double> ingredients) {
        this.setName(name);
        this.setIngredients(ingredients);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(Map<Food, Double> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Food ingredient, Double servings) {
        this.ingredients.put(ingredient, servings);
    }

    public void addIngredients(Map<Food, Double> ingredients) {
        this.ingredients.putAll(ingredients);
    }

    public String getName() {
        return name;
    }

    public Map<Food, Double> getIngredients() {
        return this.ingredients;
    }

    public double getCalories() {
        double calories = 0;
        for (Map.Entry<Food, Double> ingredient : ingredients.entrySet()) {
            calories += ingredient.getKey().getCalories() * ingredient.getValue();
        }
        return calories;
    }

    public double getFat() {
        double fat = 0;
        for (Map.Entry<Food, Double> ingredient : ingredients.entrySet()) {
            fat += ingredient.getKey().getFat() * ingredient.getValue();
        }
        return fat;
    }

    public double getCarbs() {
        double carbs = 0;
        for (Map.Entry<Food, Double> ingredient : ingredients.entrySet()) {
            carbs += ingredient.getKey().getCarbs() * ingredient.getValue();
        }
        return carbs;
    }

    public double getProtein() {
        double protein = 0;
        for (Map.Entry<Food, Double> ingredient : ingredients.entrySet()) {
            protein += ingredient.getKey().getProtein() * ingredient.getValue();
        }
        return protein;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("r,");
        sb.append(this.name);
        for (Map.Entry<Food, Double> ingredient : ingredients.entrySet()) {
            sb.append("," + ingredient.getKey().getName() + ",");
            sb.append(ingredient.getValue());
        }
        return new String(sb);
    }

}
