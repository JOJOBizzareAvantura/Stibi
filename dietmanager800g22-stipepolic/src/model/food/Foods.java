package model.food;

import java.util.List;

import model.database.FileHandler;

public class Foods {
    List<Food> foodList;

    public Foods() {
        this.foodList = FileHandler.readFood();
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public void addFood(Food food) {
        this.foodList.add(food);
    }

    public void updateFoodList(List<Food> foodList) {
        this.foodList.addAll(foodList);
    }

    public void saveFoodList() {
        FileHandler.writeFood(foodList);
    }

    public List<Food> getFoodList() {
        return this.foodList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Food food : this.foodList) {
            sb.append(food.toString() + "\n");
        }
        return new String(sb);
    }
}
