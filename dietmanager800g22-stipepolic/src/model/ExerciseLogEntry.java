package model;

import java.time.LocalDate;

import model.log.LogEntry;

/*
 * This class represents a food intake entry in the log.
 */
public class ExerciseLogEntry implements LogEntry {
    private Exercise exercise;
    private double duration;
    private LocalDate date;

    public ExerciseLogEntry(LocalDate date, Exercise exercise, double count) {
        this.date = date;
        this.exercise = exercise;
        this.duration = count;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public double getDuration() {
        return duration;
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
        sb.append(",e,");
        sb.append(this.exercise.getName() + ",");
        sb.append(this.duration);
        return new String(sb);
    }
}
