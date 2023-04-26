package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.food.BasicFood;
import model.food.Food;
import model.food.Foods;
import model.food.Recipe;
import model.log.CalorieLimit;
import model.log.FoodIntake;
import model.log.LogEntry;
import model.log.Logs;
import model.log.Weight;
import model.Exercise;
import model.ExerciseLogEntry;
import model.Exercises;
import model.database.*;

public class Controller {
    public Controller() {

    }

    public Foods readFoods() {

        Foods foods = new Foods();

        foods.updateFoodList(FileHandler.readFood());
        return foods;
    }

    public String readFoodAsString() {
        String foodNames = "";

        List<Food> foodsList = FileHandler.readFood();

        for (Food food : foodsList) {
            foodNames += food + "\n";
        }

        return foodNames;
    }

    public List<String> readFoodNamesAsList() {
        List<String> lFoodNames = new ArrayList();

        List<Food> foodsList = FileHandler.readFood();

        for (Food food : foodsList) {
            lFoodNames.add(food.getName());
        }

        return lFoodNames;
    }

    public List<String> readBasicFoodNamesAsList() {
        List<String> lFoodNames = new ArrayList();

        List<Food> foodsList = FileHandler.readFood();

        for (Food food : foodsList) {
            if (food instanceof BasicFood) {
                lFoodNames.add(food.getName());
            }
        }

        return lFoodNames;
    }

    public void writeFoods(Foods foods) {
        FileHandler.writeFood(foods.getFoodList());
    }

    public void writeLogs(Logs logs) {
        FileHandler.writeLog(logs.getLogList());
    }

    public void writeBasicFood(Foods foods, String name, double calories, double fat, double carbs, double protein) {
        Food food = new BasicFood(name, calories, fat, carbs, protein);
        foods.addFood(food);
    }

    public void writeRecipe(Foods foods, String name, String ingredientsText) {
        Map<Food, Double> ingredients = new HashMap<>();

        String[] ingerdientsArray = ingredientsText.split("\n");
        for (String ingerdient : ingerdientsArray) {
            String[] ingerdientRowValues = ingerdient.split(",");

            String ingredientName = ingerdientRowValues[0];
            Double ingredientQuantity = Double.parseDouble(ingerdientRowValues[1]);

            BasicFood newBasicFood = new BasicFood(ingredientName, 0, 0, 0, 0);

            ingredients.put(newBasicFood, ingredientQuantity);
        }

        Recipe recipe = new Recipe(name, ingredients);

        foods.addFood(recipe);
    }

    public void writeFoodIntakeLog(Logs logs, LocalDate date, Food food, double servings) {
        FoodIntake fi = new FoodIntake(date, food, servings);
        logs.addLogEntry(fi);
    }

    public void writeWeightLog(Logs logs, LocalDate date, double weight) {
        Weight we = new Weight(date, weight);
        logs.addLogEntry(we);
    }

    public void writeCaloriesLimitLog(Logs logs, LocalDate date, double limit) {
        CalorieLimit cl = new CalorieLimit(date, limit);
        logs.addLogEntry(cl);
    }

    public Logs readLogsForDate(LocalDate date) {
        Logs logs = new Logs();
        logs.updateLogList(FileHandler.readLog(date));
        return logs;
    }

    public double getCaloriesTakenForDate(LocalDate date) {
        double result = 0;

        List<LogEntry> logEntries = FileHandler.readLog(date);
        for (LogEntry le : logEntries) {
            if (le instanceof FoodIntake) {
                FoodIntake foodIntake = ((FoodIntake) le);
                result += foodIntake.getFood().getCalories() * foodIntake.getServings();
            }
        }

        return result;
    }

    public double getWeightForDate(LocalDate date) {
        double result = 0;

        List<LogEntry> logEntries = FileHandler.readLog(date);
        for (LogEntry le : logEntries) {
            if (le instanceof Weight) {
                Weight weight = ((Weight) le);
                result += weight.getWeight();
            }
        }

        return result;
    }

    public double getCalorieLimitForDate(LocalDate date) {
        double result = 0;

        List<LogEntry> logEntries = FileHandler.readLog(date);
        for (LogEntry le : logEntries) {
            if (le instanceof CalorieLimit) {
                CalorieLimit calorieLimit = ((CalorieLimit) le);
                result += calorieLimit.getCalorieLimit();
            }
        }

        return result;
    }

    public double getCaloriesBurnedForDate(LocalDate date, double weight) {
        double result = 0;

        List<LogEntry> logEntries = FileHandler.readLog(date);
        for (LogEntry le : logEntries) {
            if (le instanceof ExerciseLogEntry) {
                ExerciseLogEntry exerciseLogEntry = ((ExerciseLogEntry) le);
                result += (exerciseLogEntry.getExercise().getCaloriesBurned() * weight)
                        / (60 / exerciseLogEntry.getDuration());
            }
        }

        return result;
    }

    public Logs readExercisesForDate(LocalDate date) {
        Logs logs = new Logs();
        logs.updateLogList(FileHandler.readLog(date));
        return logs;
    }

    public String readExercisesAsString() {
        String exerciseNames = "";

        List<Exercise> exerciseList = FileHandler.readExercises();

        for (Exercise exercise : exerciseList) {
            exerciseNames += exercise + "\n";
        }

        return exerciseNames;
    }

    public void writeExercise(Exercises exercises, String name, double calories) {
        Exercise exercise = new Exercise(name, calories);
        exercises.addExercise(exercise);
    }

    public Exercises readExercises() {

        Exercises exercises = new Exercises();

        exercises.updateExerciseList(FileHandler.readExercises());
        return exercises;
    }

    public void writeExercises(List<Exercise> exercises) {
        FileHandler.writeExercises(exercises);
    }

    public List<String> readExerciseNamesAsList() {
        List<String> lExerciseNames = new ArrayList();

        List<Exercise> exerciseList = FileHandler.readExercises();

        for (Exercise exercise : exerciseList) {
            lExerciseNames.add(exercise.getName());
        }

        return lExerciseNames;
    }

    public void writeExerciseIntakeLog(Logs logs, LocalDate date, Exercise exercise, double count) {
        ExerciseLogEntry ele = new ExerciseLogEntry(date, exercise, count);
        logs.addLogEntry(ele);
    }
}
