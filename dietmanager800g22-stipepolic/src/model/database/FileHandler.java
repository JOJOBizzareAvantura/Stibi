package model.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Exercise;
import model.ExerciseLogEntry;
import model.food.*;
import model.log.*;

public class FileHandler {
    public static final String REGEX = ",";
    public static final String FOOD_FILE_NAME = "files/foods.csv";
    public static final String LOG_FILE_NAME = "files/log.csv";
    public static final String EXERCISE_FILE_NAME = "files/exercise.csv";

    public static List<Food> readFood() {
        List<Food> foodList = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(FOOD_FILE_NAME))) {
            while ((line = br.readLine()) != null) {
                String[] foodData = line.split(REGEX);

                if (foodData[0].equals("b")) {
                    BasicFood basicFood = new BasicFood(foodData[1], Double.parseDouble(foodData[2]),
                            Double.parseDouble(foodData[3]), Double.parseDouble(foodData[4]),
                            Double.parseDouble(foodData[5]));
                    foodList.add(basicFood);
                } else if (foodData[0].equals("r")) {
                    String recipeName = foodData[1];
                    int numIngredients = (foodData.length - 2) / 2;
                    Map<Food, Double> ingredients = new HashMap<>();
                    for (int i = 0; i < numIngredients; i++) {
                        String ingredientName = foodData[i * 2 + 2];
                        double ingredientAmount = Double.parseDouble(foodData[i * 2 + 3]);
                        Food ingredient = null;
                        for (Food food : foodList) {
                            if (food.getName().equals(ingredientName)) {
                                ingredient = food;
                                break;
                            }
                        }
                        if (ingredient == null) {
                            throw new IllegalArgumentException("Didn't find food for recipe " + recipeName);
                        }
                        ingredients.put(ingredient, ingredientAmount);
                    }
                    Recipe recipe = new Recipe(recipeName, ingredients);
                    foodList.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foodList;
    }

    public static void writeFood(List<Food> foodList) {

        try (PrintWriter writer = new PrintWriter(new File(FOOD_FILE_NAME))) {
            for (Food food : foodList) {
                writer.println(food.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<LogEntry> readLog(LocalDate date) {
        List<LogEntry> log = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE_NAME))) {
            while ((line = br.readLine()) != null) {
                String[] logData = line.split(REGEX);

                String type = logData[3];
                int year = Integer.parseInt(logData[0]);
                System.out.println(year);
                int month = Integer.parseInt(logData[1]);
                System.out.println(month);
                int day = Integer.parseInt(logData[2]);
                System.out.println(day);
                LocalDate recordDate = LocalDate.of(year, month, day);

                if (!recordDate.isEqual(date))
                    continue;

                if (type.equals("f")) {
                    String foodName = logData[4];
                    double servings = Double.parseDouble(logData[5]);

                    List<Food> foodList = readFood();
                    for (Food food : foodList) {
                        if (food.getName().equals(foodName)) {
                            log.add(new FoodIntake(LocalDate.of(year, month, day), food, servings));
                        }
                    }
                } else if (type.equals("w")) {
                    double weight = Double.parseDouble(logData[4]);
                    log.add(new Weight(LocalDate.of(year, month, day), weight));
                } else if (type.equals("c")) {
                    double calorieLimit = Double.parseDouble(logData[4]);
                    log.add(new CalorieLimit(LocalDate.of(year, month, day), calorieLimit));
                } else if (type.equals("e")) {
                    String name = logData[4];
                    double count = Double.parseDouble(logData[5]);

                    List<Exercise> exerciseList = readExercises();
                    for (Exercise exercise : exerciseList) {
                        if (exercise.getName().equals(name)) {
                            log.add(new ExerciseLogEntry(LocalDate.of(year, month, day), exercise, count));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }

    public static void writeLog(List<LogEntry> log) {
        try (PrintWriter writer = new PrintWriter(new File(LOG_FILE_NAME))) {
            for (LogEntry entry : log) {
                writer.println(entry.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Exercise> readExercises() {
        List<Exercise> exerciseList = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(EXERCISE_FILE_NAME))) {
            while ((line = br.readLine()) != null) {
                String[] exerciseData = line.split(REGEX);

                Exercise exercise = new Exercise(exerciseData[1], Double.parseDouble(exerciseData[2]));
                exerciseList.add(exercise);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exerciseList;
    }

    public static void writeExercises(List<Exercise> exerciseList) {

        try (PrintWriter writer = new PrintWriter(new File(EXERCISE_FILE_NAME))) {
            for (Exercise exercise : exerciseList) {
                writer.println(exercise.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
