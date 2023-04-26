package model.log;

import java.time.LocalDate;

/*
 * This class represents a calorie limit entry in the log.
 */
public class CalorieLimit implements LogEntry {
    private double limit;
    private LocalDate date;

    public CalorieLimit(LocalDate date, double limit) {
        this.date = date;
        this.limit = limit;
    }

    public double getCalorieLimit() {
        return limit;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.date.getYear() + ",");
        sb.append(this.date.getMonthValue() + ",");
        sb.append(this.date.getDayOfMonth());
        sb.append(",c,");
        sb.append(this.limit);
        return new String(sb);
    }
}
