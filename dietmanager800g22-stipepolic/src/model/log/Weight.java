package model.log;

import java.time.LocalDate;

/*
 * This class represents a weight entry in the log.
 */
public class Weight implements LogEntry {
    private double weight;
    private LocalDate date;

    public Weight(LocalDate date, double weight) {
        this.weight = weight;
        this.date = date;
    }

    public double getWeight() {
        return weight;
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
        sb.append(",w,");
        sb.append(this.weight);
        return new String(sb);
    }
}
