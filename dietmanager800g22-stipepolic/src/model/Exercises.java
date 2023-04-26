package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.database.FileHandler;

public class Exercises {
    List<Exercise> exerciseList;

    public Exercises() {
        this.exerciseList = new ArrayList();
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void updateExerciseList(List<Exercise> exerciseList) {
        this.exerciseList.addAll(exerciseList);
    }

    public void addExercise(Exercise exercise) {
        this.exerciseList.add(exercise);
    }

    public void saveExerciseList() {
        FileHandler.writeExercises(exerciseList);
    }

    public List<Exercise> getExerciseList() {
        return this.exerciseList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Exercise exercise : this.exerciseList) {
            sb.append(exercise.toString() + "\n");
        }
        return new String(sb);
    }
}
