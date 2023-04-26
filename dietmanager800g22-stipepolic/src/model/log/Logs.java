package model.log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.database.FileHandler;

public class Logs {
    List<LogEntry> logList;

    public Logs() {
        this.logList = new ArrayList();
    }

    public void setLogList(List<LogEntry> logList) {
        this.logList = logList;
    }

    public void updateLogList(List<LogEntry> logList) {
        this.logList.addAll(logList);
    }

    public void addLogEntry(LogEntry logEntry) {
        this.logList.add(logEntry);
    }

    public void saveLogList() {
        FileHandler.writeLog(logList);
    }

    public List<LogEntry> getLogList() {
        return this.logList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LogEntry log : this.logList) {
            sb.append(log.toString() + "\n");
        }
        return new String(sb);
    }
}
