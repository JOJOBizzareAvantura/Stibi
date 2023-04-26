package model.log;

import java.time.LocalDate;

/*
 * This interface is implemented by all the classes that are used to log data from user input.
 */
public interface LogEntry {
    public LocalDate getDate();

    public void setDate(LocalDate date);

    public String toString();
}