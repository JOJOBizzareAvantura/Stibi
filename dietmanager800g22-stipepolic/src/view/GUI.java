package view;

import java.time.LocalDate;
import java.util.Date;

import javax.swing.plaf.RootPaneUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import model.Exercise;
import model.Exercises;
import model.food.BasicFood;
import model.food.Foods;
import model.log.Logs;
import javafx.geometry.*;
import controller.*;

public class GUI extends Application implements EventHandler<ActionEvent> {

    private Controller controller;

    Foods foods;
    Logs logs;
    Exercises exercises;

    private Stage stage;
    private Scene scene;

    TextArea foodArea;
    TextArea logArea;
    Button enterFood;
    Button enterIntake;
    Button enterWeightCalorieLimit;
    ComboBox foodBox;
    TextField newName;
    TextField newcal;
    TextField newProtein;
    TextField newFats;
    TextField newCarbs;
    TextField foodCounter;
    TextField weightField;
    TextField calLimit;
    TextField caloriesTaken;
    TextField caloriesBurned;
    TextField calorieLimit;
    TextField caloriesAvailable;
    TextField recipeName;

    ComboBox recipeBasicFoodBox;
    TextField recipeBasicFoodQuantity;
    Button recipeEnterSimpleFood;
    TextArea recipeArea;
    Button recipeEnter;

    TextField newExercise;
    TextField exerciseCalories;
    Button enterExercise;
    TextArea exerciseArea;

    ComboBox dailyExerciseBox;
    TextField dailyExerciseCounter;
    Button dailyExerciseEnter;

    FlowPane fpColumn1 = new FlowPane();
    FlowPane fpColumn2 = new FlowPane();
    FlowPane fpColumn3 = new FlowPane();

    public GUI() {
        super();
        controller = new Controller();
        foods = controller.readFoods();
        logs = controller.readLogsForDate(LocalDate.now());
        exercises = controller.readExercises();
    }

    public void start(Stage _stage) throws Exception {
        this.stage = _stage;
        this.stage.setTitle("DIET MANAGER");

        TilePane tilePane = new TilePane();
        Scene scene = new Scene(tilePane, 1024, 768);

        tilePane.getChildren().addAll(fpColumn1, fpColumn2, fpColumn3);

        UserInfoArea(fpColumn1);
        DailyIntakeArea(fpColumn1);
        DailyExercise(fpColumn1);
        InfoArea(fpColumn1);
        FoodArea(fpColumn2);
        RecipeArea(fpColumn2);
        ExerciseArea(fpColumn2);
        ListArea(fpColumn3);

        stage.setScene(scene);
        stage.show();

        refreshScreenData();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getEventType() == ActionEvent.ACTION) {
            if (event.getSource() == enterFood) {
                controller.writeBasicFood(foods, newName.getText(), Double.parseDouble(newcal.getText()),
                        Double.parseDouble(newFats.getText()), Double.parseDouble(newCarbs.getText()),
                        Double.parseDouble(newProtein.getText()));

                controller.writeFoods(foods);
                refreshScreenData();

            } else if (event.getSource() == enterIntake) {
                controller.writeFoodIntakeLog(logs, LocalDate.now(),
                        new BasicFood(foodBox.getValue().toString(), 0, 0, 0, 0),
                        Double.parseDouble(foodCounter.getText()));

                controller.writeLogs(logs);
                refreshScreenData();
            } else if (event.getSource() == enterWeightCalorieLimit) {
                controller.writeWeightLog(logs, LocalDate.now(), Double.parseDouble(weightField.getText()));

                controller.writeCaloriesLimitLog(logs, LocalDate.now(), Double.parseDouble(calLimit.getText()));

                controller.writeLogs(logs);
                refreshScreenData();
            } else if (event.getSource() == recipeEnterSimpleFood) {
                recipeArea.setText(recipeArea.getText() + recipeBasicFoodBox.getValue().toString() + ","
                        + recipeBasicFoodQuantity.getText() + "\n");

                recipeBasicFoodBox.setValue(null);
                recipeBasicFoodQuantity.setText("1");
            } else if (event.getSource() == recipeEnter) {
                controller.writeRecipe(foods, recipeName.getText(), recipeArea.getText());
                controller.writeFoods(foods);
                refreshScreenData();
                recipeArea.setText("");
                recipeName.setText("");
            } else if (event.getSource() == enterExercise) {
                controller.writeExercise(exercises, newExercise.getText(),
                        Double.parseDouble(exerciseCalories.getText()));

                controller.writeExercises(exercises.getExerciseList());
                refreshScreenData();
            } else if (event.getSource() == dailyExerciseEnter) {
                controller.writeExerciseIntakeLog(logs, LocalDate.now(),
                        new Exercise(dailyExerciseBox.getValue().toString(), 0.0),
                        Double.parseDouble(dailyExerciseCounter.getText()));

                controller.writeLogs(logs);
                refreshScreenData();
            }
        }

    }

    private void refreshScreenData() {
        if (foodArea != null)
            foodArea.setText(controller.readFoodAsString());
        if (logArea != null)
            logArea.setText(controller.readLogsForDate(LocalDate.now()).toString());
        if (foodBox != null)
            foodBox.getItems().addAll(controller.readFoodNamesAsList());
        if (recipeBasicFoodBox != null)
            recipeBasicFoodBox.getItems().addAll(controller.readBasicFoodNamesAsList());

        if (exerciseArea != null)
            exerciseArea.setText(controller.readExercisesAsString());

        if (dailyExerciseBox != null)
            dailyExerciseBox.getItems().addAll(controller.readExerciseNamesAsList());

        calorieLimit.setText(Double.toString(controller.getCalorieLimitForDate(LocalDate.now())));
        caloriesTaken.setText(Double.toString(controller.getCaloriesTakenForDate(LocalDate.now())));
        caloriesBurned.setText(Double.toString(
                controller.getCaloriesBurnedForDate(LocalDate.now(), controller.getWeightForDate(LocalDate.now()))));

        caloriesAvailable.setText(Double.parseDouble(calorieLimit.getText())
                - Double.parseDouble(caloriesTaken.getText()) + Double.parseDouble(caloriesBurned.getText()) + "");
    }

    private void UserInfoArea(FlowPane fpColumn) {
        Label label = new Label("USER INFO");

        VBox internalBox = new VBox(8);

        FlowPane fp = new FlowPane();

        weightField = new TextField("weight");
        calLimit = new TextField("calorie limit");
        enterWeightCalorieLimit = new Button("ENTER");
        enterWeightCalorieLimit.setOnAction(this);
        fp.getChildren().addAll(weightField, calLimit, enterWeightCalorieLimit);

        internalBox.getChildren().addAll(label, fp);
        internalBox.setPadding(new Insets(20));

        fpColumn.getChildren().add(internalBox);
    }

    private void InfoArea(FlowPane fpColumn) {
        Label label = new Label("DAILY INFO");

        VBox internalBox = new VBox(8);

        Label labelCT = new Label("Calories taken:");
        caloriesTaken = new TextField("0");
        caloriesTaken.setEditable(false);
        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(labelCT, caloriesTaken);

        Label labelCB = new Label("Calories burned:");
        caloriesBurned = new TextField("0");
        caloriesBurned.setEditable(false);
        FlowPane fp2 = new FlowPane();
        fp2.getChildren().addAll(labelCB, caloriesBurned);

        Label labelCL = new Label("Calorie limit:");
        calorieLimit = new TextField("0");
        calorieLimit.setEditable(false);
        FlowPane fp3 = new FlowPane();
        fp3.getChildren().addAll(labelCL, calorieLimit);

        Label labelCA = new Label("Calories available:");
        caloriesAvailable = new TextField("0");
        caloriesAvailable.setEditable(false);
        FlowPane fp4 = new FlowPane();
        fp4.getChildren().addAll(labelCA, caloriesAvailable);

        internalBox.getChildren().addAll(label, fp3, fp, fp2, fp4);
        internalBox.setPadding(new Insets(20));

        fpColumn.getChildren().add(internalBox);
    }

    private void DailyIntakeArea(FlowPane fpColumn) {
        Label label = new Label("INSERT DAILY INTAKE");

        VBox internalBox = new VBox(8);

        FlowPane fp = new FlowPane();

        foodBox = new ComboBox<>();
        foodBox.setPrefWidth(120);
        foodCounter = new TextField("count");
        foodCounter.setPrefWidth(50);
        enterIntake = new Button("ENTER");
        enterIntake.setOnAction(this);

        fp.getChildren().addAll(foodBox, foodCounter, enterIntake);

        internalBox.getChildren().addAll(label, fp);

        internalBox.setPadding(new Insets(20));

        fpColumn.getChildren().add(internalBox);
    }

    private void ListArea(FlowPane fpColumn) {
        VBox internalBox = new VBox(8);

        FlowPane fp = new FlowPane();

        double height = 80;
        double width = 300;

        Label label = new Label("AVALIABLE FOOD");
        foodArea = new TextArea();
        foodArea.setPrefHeight(height);
        foodArea.setPrefWidth(width);
        foodArea.setEditable(false);

        Label label2 = new Label("AVALIABLE EXERCISES");
        exerciseArea = new TextArea();
        exerciseArea.setPrefHeight(height);
        exerciseArea.setPrefWidth(width);
        exerciseArea.setEditable(false);

        Label label3 = new Label("DAILY LOGS");
        logArea = new TextArea();
        logArea.setPrefHeight(height);
        logArea.setPrefWidth(width);
        logArea.setEditable(false);

        internalBox.getChildren().addAll(label, foodArea, label2, exerciseArea, label3, logArea);

        internalBox.setPadding(new Insets(20));

        fpColumn.getChildren().add(internalBox);
    }

    private void FoodArea(FlowPane fpColumn) {
        Label label1 = new Label("ADD NEW FOOD");

        VBox internalBox = new VBox(8);

        double height = 80;
        double width = 300;

        // for putting in the name of new food
        newName = new TextField("food name");
        newcal = new TextField("calories");
        newProtein = new TextField("protein");
        newFats = new TextField("fats");
        newCarbs = new TextField("carbs");

        // button for entering the information into food.csv file
        enterFood = new Button("ENTER");
        enterFood.setOnAction(this);

        internalBox.getChildren().addAll(label1, newName, newcal, newProtein, newFats, newCarbs, enterFood);

        internalBox.setPadding(new Insets(20));

        fpColumn.getChildren().add(internalBox);
    }

    private void RecipeArea(FlowPane fpColumn) {
        Label label1 = new Label("ADD NEW RECIPE");
        Label label2 = new Label("AVALIABLE RECIPES");

        VBox internalBox = new VBox(8);

        recipeName = new TextField("new recipe");

        double height = 80;
        double width = 300;

        recipeBasicFoodBox = new ComboBox<>();
        recipeBasicFoodBox.setPrefWidth(120);
        // for putting in the number of consumed chosen foods
        recipeBasicFoodQuantity = new TextField("1");
        recipeBasicFoodQuantity.setPrefWidth(50);

        recipeEnterSimpleFood = new Button("ENTER");
        recipeEnterSimpleFood.setOnAction(this);

        recipeArea = new TextArea();
        recipeArea.setPrefHeight(height);
        recipeArea.setPrefWidth(width);

        recipeEnter = new Button("SAVE");
        recipeEnter.setOnAction(this);

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(recipeBasicFoodBox, recipeBasicFoodQuantity, recipeEnterSimpleFood);

        internalBox.getChildren().addAll(label1, recipeName, fp, label2, recipeArea, recipeEnter);

        internalBox.setPadding(new Insets(20));

        fpColumn.getChildren().add(internalBox);
    }

    private void ExerciseArea(FlowPane fpColumn) {
        Label label1 = new Label("ADD EXERCISE");

        VBox internalBox = new VBox(8);

        double height = 80;
        double width = 300;

        newExercise = new TextField("Exercise");
        exerciseCalories = new TextField("calories");
        enterExercise = new Button("ENTER");
        enterExercise.setOnAction(this);

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(newExercise, exerciseCalories, enterExercise);

        internalBox.getChildren().addAll(label1, fp);

        internalBox.setPadding(new Insets(20));

        fpColumn.getChildren().add(internalBox);
    }

    private void DailyExercise(FlowPane fpColumn) {
        Label label = new Label("INSERT DAILY EXERCISES");

        VBox internalBox = new VBox(8);

        FlowPane fp = new FlowPane();

        dailyExerciseBox = new ComboBox<>();
        dailyExerciseBox.setPrefWidth(120);
        dailyExerciseCounter = new TextField("count");
        dailyExerciseCounter.setPrefWidth(50);
        dailyExerciseEnter = new Button("ENTER");
        dailyExerciseEnter.setOnAction(this);

        fp.getChildren().addAll(dailyExerciseBox, dailyExerciseCounter, dailyExerciseEnter);

        internalBox.getChildren().addAll(label, fp);

        internalBox.setPadding(new Insets(20));

        fpColumn.getChildren().add(internalBox);
    }
}
